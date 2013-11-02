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

package com.liferay.portal.dao.shard;

import com.liferay.portal.kernel.util.CentralizedThreadLocal;
import com.liferay.portal.spring.hibernate.PortalHibernateConfiguration;
import com.liferay.portal.util.PropsValues;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import org.springframework.aop.TargetSource;

/**
 * @author Michael Young
 * @author Alexander Chow
 */
public class ShardSessionFactoryTargetSource implements TargetSource {

	public Map<String, SessionFactory> getSessionFactories() {
		return _sessionFactories;
	}

	public SessionFactory getSessionFactory() {
		return _sessionFactory.get();
	}

	@Override
	public Object getTarget() throws Exception {
		return getSessionFactory();
	}

	@Override
	public Class<?> getTargetClass() {
		return _sessionFactories.get(PropsValues.SHARD_DEFAULT_NAME).getClass();
	}

	@Override
	public boolean isStatic() {
		return false;
	}

	@Override
	public void releaseTarget(Object target) throws Exception {
	}

	public void setSessionFactory(String shardName) {
		_sessionFactory.set(_sessionFactories.get(shardName));
	}

	public void setShardDataSourceTargetSource(
			ShardDataSourceTargetSource shardDataSourceTargetSource)
		throws Exception {

		Map<String, DataSource> dataSources =
			shardDataSourceTargetSource.getDataSources();

		for (String shardName : dataSources.keySet()) {
			DataSource dataSource = dataSources.get(shardName);

			PortalHibernateConfiguration portalHibernateConfiguration =
				new PortalHibernateConfiguration();

			portalHibernateConfiguration.setDataSource(dataSource);

			SessionFactory sessionFactory =
				portalHibernateConfiguration.buildSessionFactory();

			_sessionFactories.put(shardName, sessionFactory);
		}
	}

	private static Map<String, SessionFactory> _sessionFactories =
		new HashMap<String, SessionFactory>();

	private static ThreadLocal<SessionFactory> _sessionFactory =
		new CentralizedThreadLocal<SessionFactory>(false) {

		@Override
		protected SessionFactory initialValue() {
			return _sessionFactories.get(PropsValues.SHARD_DEFAULT_NAME);
		}

	};

}