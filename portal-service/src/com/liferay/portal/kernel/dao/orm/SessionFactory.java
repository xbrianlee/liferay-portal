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

import java.sql.Connection;

/**
 * @author Brian Wing Shun Chan
 */
public interface SessionFactory {

	public void closeSession(Session session) throws ORMException;

	public Session getCurrentSession() throws ORMException;

	public Dialect getDialect() throws ORMException;

	public Session openNewSession(Connection connection) throws ORMException;

	public Session openSession() throws ORMException;

}