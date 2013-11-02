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

package com.liferay.portal.kernel.dao.db;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class DBFactoryUtil {

	public static DB getDB() {
		return getDBFactory().getDB();
	}

	public static DB getDB(Object dialect) {
		return getDBFactory().getDB(dialect);
	}

	public static DB getDB(String type) {
		return getDBFactory().getDB(type);
	}

	public static DBFactory getDBFactory() {
		PortalRuntimePermission.checkGetBeanProperty(DBFactoryUtil.class);

		return _dbFactory;
	}

	public static void reset() {
		setDBFactory(null);
	}

	public static void setDB(Object dialect) {
		getDBFactory().setDB(dialect);
	}

	public static void setDB(String type) {
		getDBFactory().setDB(type);
	}

	public static void setDBFactory(DBFactory dbFactory) {
		PortalRuntimePermission.checkSetBeanProperty(DBFactoryUtil.class);

		_dbFactory = dbFactory;
	}

	private static DBFactory _dbFactory;

}