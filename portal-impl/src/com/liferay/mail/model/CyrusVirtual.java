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
public class CyrusVirtual implements Serializable {

	public static final Object[][] TABLE_COLUMNS = {
		{"emailAddress", new Integer(Types.VARCHAR)},
		{"userId", new Integer(Types.VARCHAR)}
	};

	public static final String TABLE_NAME = "CyrusVirtual";

	public static final String TABLE_SQL_CREATE =
		"create table CyrusVirtual (emailAddress VARCHAR(75) not null " +
			"primary key, userId VARCHAR(75) not null)";

	public CyrusVirtual() {
	}

	public CyrusVirtual(String emailAddress, long userId) {
		this(emailAddress, String.valueOf(userId));
	}

	public CyrusVirtual(String emailAddress, String userId) {
		_emailAddress = emailAddress;
		_userId = userId;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public String getUserId() {
		return _userId;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	private String _emailAddress;
	private String _userId;

}