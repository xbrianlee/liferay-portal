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

package com.liferay.portlet.sitesadmin;

import com.liferay.portal.model.Group;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.BaseControlPanelEntry;

import java.util.LinkedHashMap;

/**
 * @author Jorge Ferrer
 * @author Sergio González
 * @author Miguel Pastor
 */
public class SitesControlPanelEntry extends BaseControlPanelEntry {

	@Override
	protected boolean hasPermissionImplicitlyGranted(
			PermissionChecker permissionChecker, Group group, Portlet portlet)
		throws Exception {

		if (PropsValues.SITES_CONTROL_PANEL_MEMBERS_VISIBLE) {
			LinkedHashMap<String, Object> groupParams =
				new LinkedHashMap<String, Object>();

			groupParams.put("site", Boolean.TRUE);
			groupParams.put("usersGroups", permissionChecker.getUserId());

			int count = GroupLocalServiceUtil.searchCount(
				permissionChecker.getCompanyId(), null, null, groupParams);

			if (count > 0) {
				return true;
			}
		}

		return false;
	}

}