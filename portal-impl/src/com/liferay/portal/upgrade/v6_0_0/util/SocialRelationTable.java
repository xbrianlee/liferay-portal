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
public class SocialRelationTable {

	public static final String TABLE_NAME = "SocialRelation";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"relationId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"createDate", Types.BIGINT},
		{"userId1", Types.BIGINT},
		{"userId2", Types.BIGINT},
		{"type_", Types.INTEGER}
	};

	public static final String TABLE_SQL_CREATE = "create table SocialRelation (uuid_ VARCHAR(75) null,relationId LONG not null primary key,companyId LONG,createDate LONG,userId1 LONG,userId2 LONG,type_ INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table SocialRelation";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_61171E99 on SocialRelation (companyId)",
		"create index IX_95135D1C on SocialRelation (companyId, type_)",
		"create index IX_C31A64C6 on SocialRelation (type_)",
		"create index IX_5A40CDCC on SocialRelation (userId1)",
		"create index IX_4B52BE89 on SocialRelation (userId1, type_)",
		"create unique index IX_12A92145 on SocialRelation (userId1, userId2, type_)",
		"create index IX_5A40D18D on SocialRelation (userId2)",
		"create index IX_3F9C2FA8 on SocialRelation (userId2, type_)",
		"create index IX_F0CA24A5 on SocialRelation (uuid_)"
	};

}