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

package com.liferay.portal.kernel.dao.orm;

import java.io.Serializable;

import java.sql.Connection;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public interface Session {

	public void clear() throws ORMException;

	public Connection close() throws ORMException;

	public boolean contains(Object object) throws ORMException;

	public Query createQuery(String queryString) throws ORMException;

	public Query createQuery(String queryString, boolean strictName)
		throws ORMException;

	public SQLQuery createSQLQuery(String queryString) throws ORMException;

	public SQLQuery createSQLQuery(String queryString, boolean strictName)
		throws ORMException;

	public void delete(Object object) throws ORMException;

	public void evict(Object object) throws ORMException;

	public void flush() throws ORMException;

	public Object get(Class<?> clazz, Serializable id) throws ORMException;

	public Object get(Class<?> clazz, Serializable id, LockMode lockMode)
		throws ORMException;

	public Object getWrappedSession() throws ORMException;

	public Object load(Class<?> clazz, Serializable id) throws ORMException;

	public Object merge(Object object) throws ORMException;

	public Serializable save(Object object) throws ORMException;

	public void saveOrUpdate(Object object) throws ORMException;

}