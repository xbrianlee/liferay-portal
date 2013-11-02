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

package com.liferay.portlet.dynamicdatamapping.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.List;

/**
 * @author Eduardo Garcia
 */
public class DDMDisplayRegistryUtil {

	public static DDMDisplay getDDMDisplay(String portletId) {
		return getDDMDisplayRegistry().getDDMDisplay(portletId);
	}

	public static DDMDisplayRegistry getDDMDisplayRegistry() {
		PortalRuntimePermission.checkGetBeanProperty(
			DDMDisplayRegistryUtil.class);

		return _ddmDisplayRegistry;
	}

	public static List<DDMDisplay> getDDMDisplays() {
		return getDDMDisplayRegistry().getDDMDisplays();
	}

	public static String[] getPortletIds() {
		return getDDMDisplayRegistry().getPortletIds();
	}

	public static void register(DDMDisplay ddmDisplay) {
		getDDMDisplayRegistry().register(ddmDisplay);
	}

	public static void unregister(DDMDisplay ddmDisplay) {
		getDDMDisplayRegistry().unregister(ddmDisplay);
	}

	public void setDDMDisplayRegistry(DDMDisplayRegistry ddmDisplayRegistry) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_ddmDisplayRegistry = ddmDisplayRegistry;
	}

	private static DDMDisplayRegistry _ddmDisplayRegistry;

}