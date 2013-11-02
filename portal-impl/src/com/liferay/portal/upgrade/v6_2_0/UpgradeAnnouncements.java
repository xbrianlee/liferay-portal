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

package com.liferay.portal.upgrade.v6_2_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.upgrade.v6_2_0.util.AnnouncementsEntryTable;

import java.sql.SQLException;

/**
 * @author Hai Yu
 */
public class UpgradeAnnouncements extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type AnnouncementsEntry content TEXT null");
		}
		catch (SQLException sqle) {
			upgradeTable(
				AnnouncementsEntryTable.TABLE_NAME,
				AnnouncementsEntryTable.TABLE_COLUMNS,
				AnnouncementsEntryTable.TABLE_SQL_CREATE,
				AnnouncementsEntryTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}