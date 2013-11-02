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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.upgrade.v6_0_0.util.ShoppingItemTable;

import java.sql.SQLException;

/**
 * @author Alexander Chow
 */
public class UpgradeShopping extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append("update ShoppingItem set groupId = (select groupId from ");
		sb.append("ShoppingCategory where ShoppingCategory.categoryId = ");
		sb.append("ShoppingItem.categoryId)");

		runSQL(sb.toString());

		try {
			runSQL("alter_column_type ShoppingItem smallImageURL STRING null");
			runSQL("alter_column_type ShoppingItem mediumImageURL STRING null");
			runSQL("alter_column_type ShoppingItem largeImageURL STRING null");
		}
		catch (SQLException sqle) {
			upgradeTable(
				ShoppingItemTable.TABLE_NAME, ShoppingItemTable.TABLE_COLUMNS,
				ShoppingItemTable.TABLE_SQL_CREATE,
				ShoppingItemTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}