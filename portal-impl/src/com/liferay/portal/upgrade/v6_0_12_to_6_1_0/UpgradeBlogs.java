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

package com.liferay.portal.upgrade.v6_0_12_to_6_1_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.upgrade.v6_1_0.util.BlogsEntryTable;

import java.sql.SQLException;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeBlogs extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type BlogsEntry smallImageURL STRING null");
		}
		catch (SQLException sqle) {
			upgradeTable(
				BlogsEntryTable.TABLE_NAME, BlogsEntryTable.TABLE_COLUMNS,
				BlogsEntryTable.TABLE_SQL_CREATE,
				BlogsEntryTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}