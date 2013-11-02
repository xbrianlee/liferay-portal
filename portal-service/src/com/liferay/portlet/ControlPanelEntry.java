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

package com.liferay.portlet;

import com.liferay.portal.model.Group;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;

/**
 * @author Jorge Ferrer
 */
public interface ControlPanelEntry {

	public boolean hasAccessPermission(
			PermissionChecker permissionChecker, Group group, Portlet portlet)
		throws Exception;

	/**
	 * @deprecated As of 6.2.0, with no direct replacement.<p>This method was
	 *             originally defined to determine if a portlet should be
	 *             displayed in the Control Panel. In this version, this method
	 *             should always return <code>false</code> and remains only to
	 *             preserve binary compatibility. This method will be
	 *             permanently removed in a future version.</p><p>In lieu of
	 *             this method, the Control Panel now uses {@link
	 *             #hasAccessPermission} to determine if a portlet should be
	 *             displayed in the Control Panel.</p>
	 */
	public boolean isVisible(
			PermissionChecker permissionChecker, Portlet portlet)
		throws Exception;

	/**
	 * @deprecated As of 6.2.0, with no direct replacement.<p>This method was
	 *             originally defined to determine if a portlet should be
	 *             displayed in the Control Panel. In this version, this method
	 *             should always return <code>false</code> and remains only to
	 *             preserve binary compatibility. This method will be
	 *             permanently removed in a future version.</p><p>In lieu of
	 *             this method, the Control Panel now uses {@link
	 *             #hasAccessPermission} to determine if a portlet should be
	 *             displayed in the Control Panel.</p>
	 */
	public boolean isVisible(
			Portlet portlet, String category, ThemeDisplay themeDisplay)
		throws Exception;

}