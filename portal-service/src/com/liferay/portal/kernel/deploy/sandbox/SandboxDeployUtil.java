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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Igor Spasic
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class SandboxDeployUtil {

	public static SandboxDeployDir getDir(String name) {
		return getInstance()._getDir(name);
	}

	public static SandboxDeployUtil getInstance() {
		PortalRuntimePermission.checkGetBeanProperty(SandboxDeployUtil.class);

		return _instance;
	}

	public static void registerDir(SandboxDeployDir sandboxDeployDir) {
		getInstance()._registerDir(sandboxDeployDir);
	}

	public static void unregisterDir(String name) {
		getInstance()._unregisterDir(name);
	}

	private SandboxDeployUtil() {
		_sandboxDeployDirs = new HashMap<String, SandboxDeployDir>();
	}

	private SandboxDeployDir _getDir(String name) {
		return _sandboxDeployDirs.get(name);
	}

	private void _registerDir(SandboxDeployDir sandboxDeployDir) {
		_sandboxDeployDirs.put(sandboxDeployDir.getName(), sandboxDeployDir);

		sandboxDeployDir.start();
	}

	private void _unregisterDir(String name) {
		SandboxDeployDir sandboxDeployDir = _sandboxDeployDirs.remove(name);

		if (sandboxDeployDir != null) {
			sandboxDeployDir.stop();
		}
	}

	private static SandboxDeployUtil _instance = new SandboxDeployUtil();

	private Map<String, SandboxDeployDir> _sandboxDeployDirs;

}