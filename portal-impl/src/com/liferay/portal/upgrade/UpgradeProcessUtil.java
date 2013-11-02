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

package com.liferay.portal.upgrade;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.util.PropsValues;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Raymond Augé
 */
public class UpgradeProcessUtil {

	public static boolean isCreateIGImageDocumentType() {
		return _createIGImageDocumentType;
	}

	public static void setCreateIGImageDocumentType(
		boolean createIGImageDocumentType) {

		_createIGImageDocumentType = createIGImageDocumentType;
	}

	public static boolean upgradeProcess(
			int buildNumber, String[] upgradeProcessClassNames,
			ClassLoader classLoader)
		throws UpgradeException {

		return upgradeProcess(
			buildNumber, upgradeProcessClassNames, classLoader,
			PropsValues.INDEX_ON_UPGRADE);
	}

	public static boolean upgradeProcess(
			int buildNumber, String[] upgradeProcessClassNames,
			ClassLoader classLoader, boolean indexOnUpgrade)
		throws UpgradeException {

		boolean ranUpgradeProcess = false;

		boolean tempIndexReadOnly = SearchEngineUtil.isIndexReadOnly();

		if (indexOnUpgrade) {
			SearchEngineUtil.setIndexReadOnly(true);
		}

		try {
			for (String upgradeProcessClassName : upgradeProcessClassNames) {
				boolean tempRanUpgradeProcess = _upgradeProcess(
					buildNumber, upgradeProcessClassName, classLoader);

				if (tempRanUpgradeProcess) {
					ranUpgradeProcess = true;
				}
			}
		}
		finally {
			SearchEngineUtil.setIndexReadOnly(tempIndexReadOnly);
		}

		return ranUpgradeProcess;
	}

	private static boolean _upgradeProcess(
			int buildNumber, String upgradeProcessClassName,
			ClassLoader classLoader)
		throws UpgradeException {

		if (_log.isDebugEnabled()) {
			_log.debug("Initializing upgrade " + upgradeProcessClassName);
		}

		UpgradeProcess upgradeProcess = null;

		try {
			Class<?> clazz = classLoader.loadClass(upgradeProcessClassName);

			upgradeProcess = (UpgradeProcess)clazz.newInstance();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (upgradeProcess == null) {
			_log.error(upgradeProcessClassName + " cannot be found");

			return false;
		}

		if ((upgradeProcess.getThreshold() == 0) ||
			(upgradeProcess.getThreshold() > buildNumber)) {

			if (_log.isDebugEnabled()) {
				_log.debug("Running upgrade " + upgradeProcessClassName);
			}

			upgradeProcess.upgrade();

			if (_log.isDebugEnabled()) {
				_log.debug("Finished upgrade " + upgradeProcessClassName);
			}

			return true;
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Upgrade threshold " + upgradeProcess.getThreshold() +
					" will not trigger upgrade");

			_log.debug("Skipping upgrade " + upgradeProcessClassName);
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(UpgradeProcessUtil.class);

	private static boolean _createIGImageDocumentType = false;

}