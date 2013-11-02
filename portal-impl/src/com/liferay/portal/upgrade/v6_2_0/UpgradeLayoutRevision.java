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
import com.liferay.portal.upgrade.v6_2_0.util.LayoutRevisionTable;

import java.sql.SQLException;

/**
 * @author Harrison Schueler
 */
public class UpgradeLayoutRevision extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type LayoutRevision css TEXT null");
		}
		catch (SQLException sqle) {
			upgradeTable(
				LayoutRevisionTable.TABLE_NAME,
				LayoutRevisionTable.TABLE_COLUMNS,
				LayoutRevisionTable.TABLE_SQL_CREATE,
				LayoutRevisionTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}