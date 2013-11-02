/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.dao.orm.hibernate;

import com.liferay.portal.dao.shard.ShardDataSourceTargetSource;
import com.liferay.portal.kernel.dao.jdbc.CurrentConnectionUtil;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.security.lang.DoPrivilegedUtil;
import com.liferay.portal.spring.hibernate.PortletHibernateConfiguration;
import com.liferay.portal.util.PropsValues;

import java.sql.Connection;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;

/**
 * @author Shuyang Zhou
 * @author Alexander Chow
 */
public class PortletSessionFactoryImpl extends SessionFactoryImpl {

	public void afterPropertiesSet() {
		if (_dataSource == InfrastructureUtil.getDataSource()) {

			// Register only if the current session factory is using the portal
			// data source

			portletSessionFactories.add(this);
		}
	}

	@Override
	public void closeSession(Session session) throws ORMException {
		if (session != null) {
			session.flush();

			if (!PropsValues.SPRING_HIBERNATE_SESSION_DELEGATED) {
				session.close();
			}
		}
	}

	@Override
	public void destroy() {
		portletSessionFactories.remove(this);
	}

	public DataSource getDataSource() {
		ShardDataSourceTargetSource shardDataSourceTargetSource =
			(ShardDataSourceTargetSource)
				InfrastructureUtil.getShardDataSourceTargetSource();

		if (shardDataSourceTargetSource != null) {
			return shardDataSourceTargetSource.getDataSource();
		}
		else {
			return _dataSource;
		}
	}

	@Override
	public Session openSession() throws ORMException {
		SessionFactory sessionFactory = getSessionFactory();

		org.hibernate.Session session = null;

		if (PropsValues.SPRING_HIBERNATE_SESSION_DELEGATED) {
			Connection connection = CurrentConnectionUtil.getConnection(
				getDataSource());

			if (connection == null) {
				session = sessionFactory.getCurrentSession();
			}
			else {
				session = sessionFactory.openSession(connection);
			}
		}
		else {
			session = sessionFactory.openSession();
		}

		if (_log.isDebugEnabled()) {
			org.hibernate.impl.SessionImpl sessionImpl =
				(org.hibernate.impl.SessionImpl)session;

			_log.debug(
				"Session is using connection release mode " +
					sessionImpl.getConnectionReleaseMode());
		}

		return wrapSession(session);
	}

	public void setDataSource(DataSource dataSource) {
		_dataSource = dataSource;
	}

	protected SessionFactory getSessionFactory() {
		ShardDataSourceTargetSource shardDataSourceTargetSource =
			(ShardDataSourceTargetSource)
				InfrastructureUtil.getShardDataSourceTargetSource();

		if (shardDataSourceTargetSource == null) {
			return getSessionFactoryImplementor();
		}

		DataSource dataSource = shardDataSourceTargetSource.getDataSource();

		SessionFactory sessionFactory = _sessionFactories.get(dataSource);

		if (sessionFactory != null) {
			return sessionFactory;
		}

		ClassLoader classLoader = PortletClassLoaderUtil.getClassLoader();

		try {
			PortletClassLoaderUtil.setClassLoader(
				getSessionFactoryClassLoader());

			PortletHibernateConfiguration portletHibernateConfiguration =
				new PortletHibernateConfiguration();

			portletHibernateConfiguration.setDataSource(dataSource);

			try {
				sessionFactory =
					portletHibernateConfiguration.buildSessionFactory();
			}
			catch (Exception e) {
				_log.error(e, e);

				return null;
			}

			_sessionFactories.put(dataSource, sessionFactory);

			return sessionFactory;
		}
		finally {
			PortletClassLoaderUtil.setClassLoader(classLoader);
		}
	}

	@Override
	protected Session wrapSession(org.hibernate.Session session) {
		return DoPrivilegedUtil.wrapWhenActive(super.wrapSession(session));
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortletSessionFactoryImpl.class);

	private DataSource _dataSource;
	private Map<DataSource, SessionFactory> _sessionFactories =
		new HashMap<DataSource, SessionFactory>();

}