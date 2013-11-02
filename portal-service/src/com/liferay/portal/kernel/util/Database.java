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

package com.liferay.portal.kernel.util;

import java.io.IOException;

import java.sql.SQLException;

import javax.naming.NamingException;

/**
 * @author     Ganesh Ram
 * @author     Brian Wing Shun Chan
 * @deprecated As of 6.1.0, replaced by {@link
 *             com.liferay.portal.kernel.dao.db.DB}
 */
public interface Database {

	public String getType();

	public void runSQLTemplate(String path)
		throws IOException, NamingException, SQLException;

	public void runSQLTemplate(String path, boolean failOnError)
		throws IOException, NamingException, SQLException;

}