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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.NotificationThreadLocal;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.workflow.WorkflowThreadLocal;
import com.liferay.portal.staging.StagingAdvicesThreadLocal;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Raymond Augé
 */
public class VerifyProcessUtil {

	public static boolean verifyProcess(
			boolean ranUpgradeProcess, boolean newBuildNumber, boolean verified)
		throws VerifyException {

		int verifyFrequency = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VERIFY_FREQUENCY));

		if ((verifyFrequency == VerifyProcess.ALWAYS) ||
			((verifyFrequency == VerifyProcess.ONCE) && !verified) ||
			ranUpgradeProcess || newBuildNumber) {

			return _verifyProcess(ranUpgradeProcess);
		}

		return false;
	}

	private static boolean _verifyProcess(boolean ranUpgradeProcess)
		throws VerifyException {

		boolean ranVerifyProcess = false;

		if (ranUpgradeProcess && PropsValues.INDEX_ON_UPGRADE) {
			PropsUtil.set(PropsKeys.INDEX_ON_STARTUP, Boolean.TRUE.toString());

			PropsValues.INDEX_ON_STARTUP = true;
		}

		boolean tempIndexReadOnly = SearchEngineUtil.isIndexReadOnly();

		SearchEngineUtil.setIndexReadOnly(true);

		NotificationThreadLocal.setEnabled(false);
		StagingAdvicesThreadLocal.setEnabled(false);
		WorkflowThreadLocal.setEnabled(false);

		try {
			String[] verifyProcessClassNames = PropsUtil.getArray(
				PropsKeys.VERIFY_PROCESSES);

			for (String verifyProcessClassName : verifyProcessClassNames) {
				boolean tempRanVerifyProcess = _verifyProcess(
					verifyProcessClassName);

				if (tempRanVerifyProcess) {
					ranVerifyProcess = true;
				}
			}
		}
		finally {
			SearchEngineUtil.setIndexReadOnly(tempIndexReadOnly);

			NotificationThreadLocal.setEnabled(true);
			StagingAdvicesThreadLocal.setEnabled(true);
			WorkflowThreadLocal.setEnabled(true);
		}

		return ranVerifyProcess;
	}

	private static boolean _verifyProcess(String verifyProcessClassName)
		throws VerifyException {

		if (_log.isDebugEnabled()) {
			_log.debug("Initializing verification " + verifyProcessClassName);
		}

		try {
			Class<?> clazz = Class.forName(verifyProcessClassName);

			VerifyProcess verifyProcess = (VerifyProcess)clazz.newInstance();

			if (_log.isDebugEnabled()) {
				_log.debug("Running verification " + verifyProcessClassName);
			}

			verifyProcess.verify();

			if (_log.isDebugEnabled()) {
				_log.debug("Finished verification " + verifyProcessClassName);
			}

			return true;
		}
		catch (ClassNotFoundException cnfe) {
			_log.error(verifyProcessClassName + " cannot be found");
		}
		catch (IllegalAccessException iae) {
			_log.error(verifyProcessClassName + " cannot be accessed");
		}
		catch (InstantiationException ie) {
			_log.error(verifyProcessClassName + " cannot be initiated");
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(VerifyProcessUtil.class);

}