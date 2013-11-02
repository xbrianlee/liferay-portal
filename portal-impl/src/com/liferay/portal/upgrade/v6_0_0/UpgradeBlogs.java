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
import com.liferay.portal.kernel.upgrade.util.TempUpgradeColumnImpl;
import com.liferay.portal.kernel.upgrade.util.UpgradeColumn;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.upgrade.util.BlogsEntryUrlTitleUpgradeColumnImpl;
import com.liferay.portal.upgrade.v6_0_0.util.BlogsEntryTable;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeBlogs extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("drop index IX_E0D90212 on BlogsEntry");
			runSQL("drop index IX_DA53AFD4 on BlogsEntry");
			runSQL("drop index IX_B88E740E on BlogsEntry");

			runSQL("alter table BlogsEntry drop column draft");
		}
		catch (Exception e) {
		}

		UpgradeColumn entryIdColumn = new TempUpgradeColumnImpl("entryId");

		UpgradeColumn titleColumn = new TempUpgradeColumnImpl("title");

		UpgradeColumn urlTitleColumn = new BlogsEntryUrlTitleUpgradeColumnImpl(
			entryIdColumn, titleColumn);

		UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			BlogsEntryTable.TABLE_NAME, BlogsEntryTable.TABLE_COLUMNS,
			entryIdColumn, titleColumn, urlTitleColumn);

		upgradeTable.updateTable();
	}

}