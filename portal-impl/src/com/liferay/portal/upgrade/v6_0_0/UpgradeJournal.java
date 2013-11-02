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
import com.liferay.portal.upgrade.v6_0_0.util.JournalArticleTable;
import com.liferay.portal.upgrade.v6_0_0.util.JournalFeedTable;
import com.liferay.portal.upgrade.v6_0_0.util.JournalTemplateTable;

import java.sql.SQLException;

/**
 * @author Zsigmond Rab
 */
public class UpgradeJournal extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL(
				"alter_column_type JournalArticle smallImageURL STRING null");

			runSQL(
				"alter_column_type JournalFeed targetLayoutFriendlyUrl " +
					"VARCHAR(255) null");

			runSQL(
				"alter_column_type JournalTemplate smallImageURL STRING null");
		}
		catch (SQLException sqle) {
			upgradeTable(
				JournalArticleTable.TABLE_NAME,
				JournalArticleTable.TABLE_COLUMNS,
				JournalArticleTable.TABLE_SQL_CREATE,
				JournalArticleTable.TABLE_SQL_ADD_INDEXES);

			upgradeTable(
				JournalFeedTable.TABLE_NAME, JournalFeedTable.TABLE_COLUMNS,
				JournalFeedTable.TABLE_SQL_CREATE,
				JournalFeedTable.TABLE_SQL_ADD_INDEXES);

			upgradeTable(
				JournalTemplateTable.TABLE_NAME,
				JournalTemplateTable.TABLE_COLUMNS,
				JournalTemplateTable.TABLE_SQL_CREATE,
				JournalTemplateTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}