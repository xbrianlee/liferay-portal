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

package com.liferay.mail.model;

import java.io.Serializable;

import java.sql.Types;

/**
 * @author Brian Wing Shun Chan
 */
public class CyrusUser implements Serializable {

	public static final Object[][] TABLE_COLUMNS = {
		{"userId", new Integer(Types.VARCHAR)},
		{"password_", new Integer(Types.VARCHAR)}
	};

	public static final String TABLE_NAME = "CyrusUser";

	public static final String TABLE_SQL_CREATE =
		"create table CyrusUser (userId VARCHAR(75) not null primary key, " +
			"password_ VARCHAR(75) not null)";

	public CyrusUser() {
	}

	public CyrusUser(long userId, String password) {
		this(String.valueOf(userId), password);
	}

	public CyrusUser(String userId, String password) {
		_userId = userId;
		_password = password;
	}

	public String getPassword() {
		return _password;
	}

	public String getUserId() {
		return _userId;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	private String _password;
	private String _userId;

}