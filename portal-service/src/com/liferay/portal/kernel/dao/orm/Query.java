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

import java.sql.Timestamp;

import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public interface Query {

	public int executeUpdate() throws ORMException;

	@SuppressWarnings("rawtypes")
	public Iterator iterate() throws ORMException;

	@SuppressWarnings("rawtypes")
	public Iterator iterate(boolean modifiable) throws ORMException;

	public Object iterateNext() throws ORMException;

	@SuppressWarnings("rawtypes")
	public List list() throws ORMException;

	@SuppressWarnings("rawtypes")
	public List list(boolean unmodifiable) throws ORMException;

	@SuppressWarnings("rawtypes")
	public List list(boolean copy, boolean unmodifiable) throws ORMException;

	public ScrollableResults scroll() throws ORMException;

	public Query setBoolean(int pos, boolean value);

	public Query setBoolean(String name, boolean value);

	public Query setCacheable(boolean cacheable);

	public Query setCacheMode(CacheMode cacheMode);

	public Query setCacheRegion(String cacheRegion);

	public Query setDouble(int pos, double value);

	public Query setDouble(String name, double value);

	public Query setFirstResult(int firstResult);

	public Query setFloat(int pos, float value);

	public Query setFloat(String name, float value);

	public Query setInteger(int pos, int value);

	public Query setInteger(String name, int value);

	public Query setLockMode(String alias, LockMode lockMode);

	public Query setLong(int pos, long value);

	public Query setLong(String name, long value);

	public Query setMaxResults(int maxResults);

	public Query setSerializable(int pos, Serializable value);

	public Query setSerializable(String name, Serializable value);

	public Query setShort(int pos, short value);

	public Query setShort(String name, short value);

	public Query setString(int pos, String value);

	public Query setString(String name, String value);

	public Query setTimestamp(int pos, Timestamp value);

	public Query setTimestamp(String name, Timestamp value);

	public Object uniqueResult() throws ORMException;

}