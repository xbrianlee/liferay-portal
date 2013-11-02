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
public class JournalFeedTable {

	public static final String TABLE_NAME = "JournalFeed";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"id_", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"feedId", Types.VARCHAR},
		{"name", Types.VARCHAR},
		{"description", Types.VARCHAR},
		{"type_", Types.VARCHAR},
		{"structureId", Types.VARCHAR},
		{"templateId", Types.VARCHAR},
		{"rendererTemplateId", Types.VARCHAR},
		{"delta", Types.INTEGER},
		{"orderByCol", Types.VARCHAR},
		{"orderByType", Types.VARCHAR},
		{"targetLayoutFriendlyUrl", Types.VARCHAR},
		{"targetPortletId", Types.VARCHAR},
		{"contentField", Types.VARCHAR},
		{"feedType", Types.VARCHAR},
		{"feedVersion", Types.DOUBLE}
	};

	public static final String TABLE_SQL_CREATE = "create table JournalFeed (uuid_ VARCHAR(75) null,id_ LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,feedId VARCHAR(75) null,name VARCHAR(75) null,description STRING null,type_ VARCHAR(75) null,structureId VARCHAR(75) null,templateId VARCHAR(75) null,rendererTemplateId VARCHAR(75) null,delta INTEGER,orderByCol VARCHAR(75) null,orderByType VARCHAR(75) null,targetLayoutFriendlyUrl VARCHAR(255) null,targetPortletId VARCHAR(75) null,contentField VARCHAR(75) null,feedType VARCHAR(75) null,feedVersion DOUBLE)";

	public static final String TABLE_SQL_DROP = "drop table JournalFeed";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_35A2DB2F on JournalFeed (groupId)",
		"create unique index IX_65576CBC on JournalFeed (groupId, feedId)",
		"create index IX_50C36D79 on JournalFeed (uuid_)",
		"create unique index IX_39031F51 on JournalFeed (uuid_, groupId)"
	};

}