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

package com.liferay.portal.kernel.deploy.sandbox;

/**
 * @author Igor Spasic
 * @author Brian Wing Shun Chan
 */
public class SandboxDeployScanner extends Thread {

	public SandboxDeployScanner(
		ThreadGroup threadGroup, String name,
		SandboxDeployDir sandboxDeployDir) {

		super(threadGroup, name);

		_sandboxDeployDir = sandboxDeployDir;

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
				sleep(_sandboxDeployDir.getInterval());
			}
			catch (InterruptedException ie) {
			}

			_sandboxDeployDir.scanDirectory();
		}
	}

	private SandboxDeployDir _sandboxDeployDir;
	private boolean _started = true;

}