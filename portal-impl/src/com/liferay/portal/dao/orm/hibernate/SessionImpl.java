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

import com.liferay.portal.dao.orm.common.SQLTransformer;
import com.liferay.portal.kernel.dao.orm.LockMode;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.security.pacl.NotPrivileged;
import com.liferay.portal.security.lang.DoPrivilegedUtil;

import java.io.Serializable;

import java.sql.Connection;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
@DoPrivileged
public class SessionImpl implements Session {

	public SessionImpl(org.hibernate.Session session) {
		_session = session;
	}

	@NotPrivileged
	@Override
	public void clear() throws ORMException {
		try {
			_session.clear();
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	@NotPrivileged
	@Override
	public Connection close() throws ORMException {
		try {
			return _session.close();
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	@NotPrivileged
	@Override
	public boolean contains(Object object) throws ORMException {
		try {
			return _session.contains(object);
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	@Override
	public Query createQuery(String queryString) throws ORMException {
		return createQuery(queryString, true);
	}

	@Override
	public Query createQuery(String queryString, boolean strictName)
		throws ORMException {

		try {
			queryString = SQLTransformer.transformFromJpqlToHql(queryString);

			return DoPrivilegedUtil.wrapWhenActive(
				new QueryImpl(_session.createQuery(queryString), strictName)
			);
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	@Override
	public SQLQuery createSQLQuery(String queryString) throws ORMException {
		return createSQLQuery(queryString, true);
	}

	@Override
	public SQLQuery createSQLQuery(String queryString, boolean strictName)
		throws ORMException {

		try {
			queryString = SQLTransformer.transformFromJpqlToHql(queryString);

			return DoPrivilegedUtil.wrapWhenActive(
				new SQLQueryImpl(
					_session.createSQLQuery(queryString), strictName)
			);
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	@NotPrivileged
	@Override
	public void delete(Object object) throws ORMException {
		try {
			_session.delete(object);
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	@NotPrivileged
	@Override
	public void evict(Object object) throws ORMException {
		try {
			_session.evict(object);
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	@NotPrivileged
	@Override
	public void flush() throws ORMException {
		try {
			_session.flush();
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	@NotPrivileged
	@Override
	public Object get(Class<?> clazz, Serializable id) throws ORMException {
		try {
			return _session.get(clazz, id);
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	@NotPrivileged
	@Override
	public Object get(Class<?> clazz, Serializable id, LockMode lockMode)
		throws ORMException {

		try {
			return _session.get(
				clazz, id, LockModeTranslator.translate(lockMode));
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	@NotPrivileged
	@Override
	public Object getWrappedSession() {
		return _session;
	}

	@NotPrivileged
	@Override
	public Object load(Class<?> clazz, Serializable id) throws ORMException {
		try {
			return _session.load(clazz, id);
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	@NotPrivileged
	@Override
	public Object merge(Object object) throws ORMException {
		try {
			return _session.merge(object);
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	@NotPrivileged
	@Override
	public Serializable save(Object object) throws ORMException {
		try {
			return _session.save(object);
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	@NotPrivileged
	@Override
	public void saveOrUpdate(Object object) throws ORMException {
		try {
			_session.saveOrUpdate(object);
		}
		catch (Exception e) {
			throw ExceptionTranslator.translate(e);
		}
	}

	private org.hibernate.Session _session;

}