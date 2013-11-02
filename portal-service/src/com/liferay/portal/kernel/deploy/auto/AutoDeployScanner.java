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

package com.liferay.portal.kernel.deploy.auto;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 */
public class AutoDeployScanner extends Thread {

	public AutoDeployScanner(
		ThreadGroup threadGroup, String name, AutoDeployDir autoDeployDir) {

		super(threadGroup, name);

		_autoDeployDir = autoDeployDir;

		setContextClassLoader(getClass().getClassLoader());
		setDaemon(true);
		setPriority(MIN_PRIORITY);
	}

	public void pause() {
		_started = false;
	}

	@Override
	public void run() {
		try {
			sleep(1000 * 10);
		}
		catch (InterruptedException ie) {
		}

		while (_started) {
			try {
				sleep(_autoDeployDir.getInterval());
			}
			catch (InterruptedException ie) {
			}

			try {
				_autoDeployDir.scanDirectory();
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to scan the auto deploy directory", e);
				}
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AutoDeployScanner.class);

	private AutoDeployDir _autoDeployDir;
	private boolean _started = true;

}