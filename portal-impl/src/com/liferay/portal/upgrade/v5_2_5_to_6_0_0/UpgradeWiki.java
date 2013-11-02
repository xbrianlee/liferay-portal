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

package com.liferay.portal.upgrade.v5_2_5_to_6_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.upgrade.v5_2_5_to_6_0_0.util.WikiPageResourceTable;
import com.liferay.portal.upgrade.v5_2_5_to_6_0_0.util.WikiPageTable;

import java.sql.SQLException;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeWiki extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type WikiPage parentTitle varchar(255) null");
			runSQL(
				"alter_column_type WikiPage redirectTitle varchar(255) null");

			runSQL(
				"alter_column_type WikiPageResource title varchar(255) null");
		}
		catch (SQLException sqle) {

			// WikiPage

			upgradeTable(
				WikiPageTable.TABLE_NAME, WikiPageTable.TABLE_COLUMNS,
				WikiPageTable.TABLE_SQL_CREATE,
				WikiPageTable.TABLE_SQL_ADD_INDEXES);

			// WikiPageResource

			upgradeTable(
				WikiPageResourceTable.TABLE_NAME,
				WikiPageResourceTable.TABLE_COLUMNS,
				WikiPageResourceTable.TABLE_SQL_CREATE,
				WikiPageResourceTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}