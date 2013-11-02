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
import com.liferay.portal.upgrade.v6_2_0.util.RepositoryTable;

import java.sql.SQLException;

/**
 * @author Alberto Montero
 */
public class UpgradeRepository extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type Repository portletId VARCHAR(200) null");
		}
		catch (SQLException sqle) {
			upgradeTable(
				RepositoryTable.TABLE_NAME, RepositoryTable.TABLE_COLUMNS,
				RepositoryTable.TABLE_SQL_CREATE,
				RepositoryTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}