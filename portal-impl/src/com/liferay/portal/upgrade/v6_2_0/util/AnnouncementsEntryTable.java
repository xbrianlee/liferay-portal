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
public class AnnouncementsEntryTable {

	public static final String TABLE_NAME = "AnnouncementsEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"entryId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT},
		{"title", Types.VARCHAR},
		{"content", Types.CLOB},
		{"url", Types.VARCHAR},
		{"type_", Types.VARCHAR},
		{"displayDate", Types.TIMESTAMP},
		{"expirationDate", Types.TIMESTAMP},
		{"priority", Types.INTEGER},
		{"alert", Types.BOOLEAN}
	};

	public static final String TABLE_SQL_CREATE = "create table AnnouncementsEntry (uuid_ VARCHAR(75) null,entryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,title VARCHAR(75) null,content TEXT null,url STRING null,type_ VARCHAR(75) null,displayDate DATE null,expirationDate DATE null,priority INTEGER,alert BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table AnnouncementsEntry";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_A6EF0B81 on AnnouncementsEntry (classNameId, classPK)",
		"create index IX_14F06A6B on AnnouncementsEntry (classNameId, classPK, alert)",
		"create index IX_D49C2E66 on AnnouncementsEntry (userId)",
		"create index IX_1AFBDE08 on AnnouncementsEntry (uuid_)",
		"create index IX_F2949120 on AnnouncementsEntry (uuid_, companyId)"
	};

}