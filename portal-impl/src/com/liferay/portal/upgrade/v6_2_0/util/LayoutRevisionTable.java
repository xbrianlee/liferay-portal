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

package com.liferay.portal.upgrade.v6_2_0.util;

import java.sql.Types;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class LayoutRevisionTable {

	public static final String TABLE_NAME = "LayoutRevision";

	public static final Object[][] TABLE_COLUMNS = {
		{"layoutRevisionId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"layoutSetBranchId", Types.BIGINT},
		{"layoutBranchId", Types.BIGINT},
		{"parentLayoutRevisionId", Types.BIGINT},
		{"head", Types.BOOLEAN},
		{"major", Types.BOOLEAN},
		{"plid", Types.BIGINT},
		{"privateLayout", Types.BOOLEAN},
		{"name", Types.VARCHAR},
		{"title", Types.VARCHAR},
		{"description", Types.VARCHAR},
		{"keywords", Types.VARCHAR},
		{"robots", Types.VARCHAR},
		{"typeSettings", Types.CLOB},
		{"iconImage", Types.BOOLEAN},
		{"iconImageId", Types.BIGINT},
		{"themeId", Types.VARCHAR},
		{"colorSchemeId", Types.VARCHAR},
		{"wapThemeId", Types.VARCHAR},
		{"wapColorSchemeId", Types.VARCHAR},
		{"css", Types.CLOB},
		{"status", Types.INTEGER},
		{"statusByUserId", Types.BIGINT},
		{"statusByUserName", Types.VARCHAR},
		{"statusDate", Types.TIMESTAMP}
	};

	public static final String TABLE_SQL_CREATE = "create table LayoutRevision (layoutRevisionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,layoutSetBranchId LONG,layoutBranchId LONG,parentLayoutRevisionId LONG,head BOOLEAN,major BOOLEAN,plid LONG,privateLayout BOOLEAN,name STRING null,title STRING null,description STRING null,keywords STRING null,robots STRING null,typeSettings TEXT null,iconImage BOOLEAN,iconImageId LONG,themeId VARCHAR(75) null,colorSchemeId VARCHAR(75) null,wapThemeId VARCHAR(75) null,wapColorSchemeId VARCHAR(75) null,css TEXT null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table LayoutRevision";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_43E8286A on LayoutRevision (head, plid)",
		"create index IX_314B621A on LayoutRevision (layoutSetBranchId)",
		"create index IX_A9AC086E on LayoutRevision (layoutSetBranchId, head)",
		"create index IX_E10AC39 on LayoutRevision (layoutSetBranchId, head, plid)",
		"create index IX_13984800 on LayoutRevision (layoutSetBranchId, layoutBranchId, plid)",
		"create index IX_4A84AF43 on LayoutRevision (layoutSetBranchId, parentLayoutRevisionId, plid)",
		"create index IX_B7B914E5 on LayoutRevision (layoutSetBranchId, plid)",
		"create index IX_70DA9ECB on LayoutRevision (layoutSetBranchId, plid, status)",
		"create index IX_7FFAE700 on LayoutRevision (layoutSetBranchId, status)",
		"create index IX_9329C9D6 on LayoutRevision (plid)",
		"create index IX_8EC3D2BC on LayoutRevision (plid, status)"
	};

}