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
import com.liferay.portal.upgrade.v6_0_0.util.PollsChoiceTable;
import com.liferay.portal.upgrade.v6_0_0.util.PollsQuestionTable;

import java.sql.SQLException;

/**
 * @author Julio Camarero Puras
 */
public class UpgradePolls extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type PollsChoice description STRING null");

			runSQL("alter_column_type PollsQuestion title STRING null");
		}
		catch (SQLException sqle) {

			// PollsChoice

			upgradeTable(
				PollsChoiceTable.TABLE_NAME, PollsChoiceTable.TABLE_COLUMNS,
				PollsChoiceTable.TABLE_SQL_CREATE,
				PollsChoiceTable.TABLE_SQL_ADD_INDEXES);

			// PollsQuestion

			upgradeTable(
				PollsQuestionTable.TABLE_NAME, PollsQuestionTable.TABLE_COLUMNS,
				PollsQuestionTable.TABLE_SQL_CREATE,
				PollsQuestionTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}