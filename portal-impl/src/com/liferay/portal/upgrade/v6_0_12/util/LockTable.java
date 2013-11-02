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

package com.liferay.portal.upgrade.v6_0_12.util;

import java.sql.Types;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class LockTable {

	public static final String TABLE_NAME = "Lock_";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"lockId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"className", Types.VARCHAR},
		{"key_", Types.VARCHAR},
		{"owner", Types.VARCHAR},
		{"inheritable", Types.BOOLEAN},
		{"expirationDate", Types.TIMESTAMP}
	};

	public static final String TABLE_SQL_CREATE = "create table Lock_ (uuid_ VARCHAR(75) null,lockId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,className VARCHAR(75) null,key_ VARCHAR(200) null,owner VARCHAR(300) null,inheritable BOOLEAN,expirationDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table Lock_";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_228562AD on Lock_ (className, key_)",
		"create index IX_E3F1286B on Lock_ (expirationDate)",
		"create index IX_13C5CD3A on Lock_ (uuid_)"
	};

}