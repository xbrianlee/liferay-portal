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

package com.liferay.portal.upgrade.v6_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.upgrade.v6_0_0.util.LayoutTable;

import java.sql.SQLException;

/**
 * @author Jorge Ferrer
 */
public class UpgradeLayout extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type Layout friendlyURL VARCHAR(255) null");
		}
		catch (SQLException sqle) {

			// Layout

			upgradeTable(
				LayoutTable.TABLE_NAME, LayoutTable.TABLE_COLUMNS,
				LayoutTable.TABLE_SQL_CREATE,
				LayoutTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}