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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.util.ClassUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class WebDirDetector {

	public static String getLibDir(ClassLoader classLoader) {
		String libDir = ClassUtil.getParentPath(
			classLoader, "com.liferay.util.bean.PortletBeanLocatorUtil");

		if (libDir.endsWith("/WEB-INF/classes/")) {
			libDir = libDir.substring(0, libDir.length() - 8) + "lib/";
		}
		else {
			int pos = libDir.indexOf("/WEB-INF/lib/");

			if (pos != -1) {
				libDir = libDir.substring(0, pos) + "/WEB-INF/lib/";
			}
		}

		return libDir;
	}

	public static String getRootDir(ClassLoader classLoader) {
		return getRootDir(getLibDir(classLoader));
	}

	public static String getRootDir(String libDir) {
		String rootDir = libDir;

		if (rootDir.endsWith("/WEB-INF/lib/")) {
			rootDir = rootDir.substring(0, rootDir.length() - 12);
		}

		return rootDir;
	}

}