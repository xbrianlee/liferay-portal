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

package com.liferay.portal.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Set;

/**
 * @author Ryan Park
 * @author Brian Wing Shun Chan
 */
public class CustomJspRegistryUtil {

	public static String getCustomJspFileName(
		String servletContextName, String fileName) {

		return getCustomJspRegistry().getCustomJspFileName(
			servletContextName, fileName);
	}

	public static CustomJspRegistry getCustomJspRegistry() {
		PortalRuntimePermission.checkGetBeanProperty(
			CustomJspRegistryUtil.class);

		return _customJspRegistry;
	}

	public static String getDisplayName(String servletContextName) {
		return getCustomJspRegistry().getDisplayName(servletContextName);
	}

	public static Set<String> getServletContextNames() {
		return getCustomJspRegistry().getServletContextNames();
	}

	public static void registerServletContextName(
		String servletContextName, String displayName) {

		getCustomJspRegistry().registerServletContextName(
			servletContextName, displayName);
	}

	public static void unregisterServletContextName(String servletContextName) {
		getCustomJspRegistry().unregisterServletContextName(servletContextName);
	}

	public void setCustomJspRegistry(CustomJspRegistry customJspRegistry) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_customJspRegistry = customJspRegistry;
	}

	private static CustomJspRegistry _customJspRegistry;

}