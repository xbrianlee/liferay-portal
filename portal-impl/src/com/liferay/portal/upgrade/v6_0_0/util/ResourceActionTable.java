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

package com.liferay.portal.upgrade.v6_0_0.util;

import java.sql.Types;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class ResourceActionTable {

	public static final String TABLE_NAME = "ResourceAction";

	public static final Object[][] TABLE_COLUMNS = {
		{"resourceActionId", Types.BIGINT},
		{"name", Types.VARCHAR},
		{"actionId", Types.VARCHAR},
		{"bitwiseValue", Types.BIGINT}
	};

	public static final String TABLE_SQL_CREATE = "create table ResourceAction (resourceActionId LONG not null primary key,name VARCHAR(255) null,actionId VARCHAR(75) null,bitwiseValue LONG)";

	public static final String TABLE_SQL_DROP = "drop table ResourceAction";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_81F2DB09 on ResourceAction (name)",
		"create unique index IX_EDB9986E on ResourceAction (name, actionId)"
	};

}