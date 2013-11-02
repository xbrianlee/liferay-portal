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

package com.liferay.portal.upgrade.v5_2_5_to_6_0_0.util;

import java.sql.Types;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class WikiPageResourceTable {

	public static final String TABLE_NAME = "WikiPageResource";

	public static final Object[][] TABLE_COLUMNS = {
		{"resourcePrimKey", Types.BIGINT},
		{"nodeId", Types.BIGINT},
		{"title", Types.VARCHAR}
	};

	public static final String TABLE_SQL_CREATE = "create table WikiPageResource (resourcePrimKey LONG not null primary key,nodeId LONG,title VARCHAR(255) null)";

	public static final String TABLE_SQL_DROP = "drop table WikiPageResource";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create unique index IX_21277664 on WikiPageResource (nodeId, title)"
	};

}