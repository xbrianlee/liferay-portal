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

package com.liferay.portal.events;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.verify.VerifyException;

import java.sql.Connection;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Raymond Augé
 */
public class StartupHelperUtil {

	public static StartupHelper getStartupHelper() {
		return _startupHelper;
	}

	public static boolean isUpgraded() {
		return getStartupHelper().isUpgraded();
	}

	public static boolean isVerified() {
		return getStartupHelper().isVerified();
	}

	public static void setDropIndexes(boolean dropIndexes) {
		getStartupHelper().setDropIndexes(dropIndexes);
	}

	public static void updateIndexes() {
		getStartupHelper().updateIndexes();
	}

	public static void updateIndexes(boolean dropIndexes) {
		getStartupHelper().updateIndexes(dropIndexes);
	}

	public static void updateIndexes(
		DB db, Connection connection, boolean dropIndexes) {

		getStartupHelper().updateIndexes(db, connection, dropIndexes);
	}

	public static void upgradeProcess(int buildNumber) throws UpgradeException {
		getStartupHelper().upgradeProcess(buildNumber);
	}

	public static void verifyProcess(boolean newBuildNumber, boolean verified)
		throws VerifyException {

		getStartupHelper().verifyProcess(newBuildNumber, verified);
	}

	public void setStartupHelper(StartupHelper startupHelper) {
		_startupHelper = startupHelper;
	}

	private static StartupHelper _startupHelper;

}