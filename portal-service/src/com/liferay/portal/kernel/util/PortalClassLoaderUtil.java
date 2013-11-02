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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalClassLoaderUtil {

	public static ClassLoader getClassLoader() {
		PortalRuntimePermission.checkGetClassLoader("portal");

		return _classLoader;
	}

	public static void setClassLoader(ClassLoader classLoader) {
		PortalRuntimePermission.checkSetBeanProperty(
			PortalClassLoaderUtil.class);

		if (ServerDetector.isJOnAS() && JavaDetector.isJDK6()) {
			_classLoader = new URLClassLoader(new URL[0], classLoader);
		}
		else {
			_classLoader = classLoader;
		}
	}

	private static ClassLoader _classLoader;

}