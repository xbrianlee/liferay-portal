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

package com.liferay.portal.dao.db;

import com.liferay.portal.kernel.dao.db.DB;

/**
 * @author Alexander Chow
 */
public class InterBaseDB extends FirebirdDB {

	public static DB getInstance() {
		return _instance;
	}

	protected InterBaseDB() {
		super(TYPE_INTERBASE);
	}

	@Override
	protected String getServerName() {
		return "interbase";
	}

	private static InterBaseDB _instance = new InterBaseDB();

}