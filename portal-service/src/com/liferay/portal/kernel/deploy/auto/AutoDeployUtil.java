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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class AutoDeployUtil {

	public static AutoDeployDir getDir(String name) {
		return getInstance()._getDir(name);
	}

	public static AutoDeployUtil getInstance() {
		PortalRuntimePermission.checkGetBeanProperty(AutoDeployUtil.class);

		return _instance;
	}

	public static void registerDir(AutoDeployDir autoDeployDir) {
		getInstance()._registerDir(autoDeployDir);
	}

	public static void unregisterDir(String name) {
		getInstance()._unregisterDir(name);
	}

	private AutoDeployUtil() {
		_autoDeployDirs = new HashMap<String, AutoDeployDir>();
	}

	private AutoDeployDir _getDir(String name) {
		return _autoDeployDirs.get(name);
	}

	private void _registerDir(AutoDeployDir autoDeployDir) {
		_autoDeployDirs.put(autoDeployDir.getName(), autoDeployDir);

		autoDeployDir.start();
	}

	private void _unregisterDir(String name) {
		AutoDeployDir autoDeployDir = _autoDeployDirs.remove(name);

		if (autoDeployDir != null) {
			autoDeployDir.stop();
		}
	}

	private static AutoDeployUtil _instance = new AutoDeployUtil();

	private Map<String, AutoDeployDir> _autoDeployDirs;

}