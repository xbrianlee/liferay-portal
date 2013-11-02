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
public class DLFileRankTable {

	public static final String TABLE_NAME = "DLFileRank";

	public static final Object[][] TABLE_COLUMNS = {
		{"fileRankId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP},
		{"folderId", Types.BIGINT},
		{"name", Types.VARCHAR}
	};

	public static final String TABLE_SQL_CREATE = "create table DLFileRank (fileRankId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,createDate DATE null,folderId LONG,name VARCHAR(255) null)";

	public static final String TABLE_SQL_DROP = "drop table DLFileRank";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create unique index IX_CE705D48 on DLFileRank (companyId, userId, folderId, name)",
		"create index IX_40B56512 on DLFileRank (folderId, name)",
		"create index IX_BAFB116E on DLFileRank (groupId, userId)",
		"create index IX_EED06670 on DLFileRank (userId)"
	};

}