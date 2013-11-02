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
import com.liferay.portal.upgrade.v6_2_0.util.LayoutSetBranchTable;

import java.sql.SQLException;

/**
 * @author Harrison Schueler
 */
public class UpgradeLayoutSetBranch extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type LayoutSetBranch css TEXT null");
			runSQL("alter_column_type LayoutSetBranch settings_ TEXT null");
		}
		catch (SQLException sqle) {
			upgradeTable(
				LayoutSetBranchTable.TABLE_NAME,
				LayoutSetBranchTable.TABLE_COLUMNS,
				LayoutSetBranchTable.TABLE_SQL_CREATE,
				LayoutSetBranchTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}