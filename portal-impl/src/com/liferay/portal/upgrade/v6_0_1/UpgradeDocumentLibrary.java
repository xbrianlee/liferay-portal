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

package com.liferay.portal.upgrade.v6_0_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.upgrade.v6_0_1.util.DLFileEntryTable;
import com.liferay.portal.upgrade.v6_0_1.util.DLFileVersionTable;

import java.sql.SQLException;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeDocumentLibrary extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type DLFileEntry size_ LONG");

			runSQL("alter_column_type DLFileVersion size_ LONG");
		}
		catch (SQLException sqle) {

			// DLFileEntry

			upgradeTable(
				DLFileEntryTable.TABLE_NAME, DLFileEntryTable.TABLE_COLUMNS,
				DLFileEntryTable.TABLE_SQL_CREATE,
				DLFileEntryTable.TABLE_SQL_ADD_INDEXES);

			// DLFileVersion

			upgradeTable(
				DLFileVersionTable.TABLE_NAME, DLFileVersionTable.TABLE_COLUMNS,
				DLFileVersionTable.TABLE_SQL_CREATE,
				DLFileVersionTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}