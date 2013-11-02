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

package com.liferay.portal.kernel.upgrade.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeTableFactoryUtil {

	public static UpgradeTable getUpgradeTable(
		String tableName, Object[][] columns, UpgradeColumn... upgradeColumns) {

		return getUpgradeTableFactory().getUpgradeTable(
			tableName, columns, upgradeColumns);
	}

	public static UpgradeTableFactory getUpgradeTableFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			UpgradeTableFactoryUtil.class);

		return _upgradeTableFactory;
	}

	public void setUpgradeTableFactory(
		UpgradeTableFactory upgradeTableFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_upgradeTableFactory = upgradeTableFactory;
	}

	private static UpgradeTableFactory _upgradeTableFactory;

}