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

import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletCategory;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.permission.PortletPermissionUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Eudaldo Alonso
 */
public class PortletCategoryUtil {

	public static PortletCategory getRelevantPortletCategory(
			PermissionChecker permissionChecker, long companyId, Layout layout,
			PortletCategory portletCategory,
			LayoutTypePortlet layoutTypePortlet)
		throws Exception {

		UnicodeProperties typeSettingsProperties =
			layout.getTypeSettingsProperties();

		Set<String> panelSelectedPortletIds = SetUtil.fromArray(
			StringUtil.split(
				typeSettingsProperties.getProperty("panelSelectedPortlets")));

		return getRelevantPortletCategory(
			permissionChecker, companyId, layout, portletCategory,
			panelSelectedPortletIds, layoutTypePortlet);
	}

	protected static PortletCategory getRelevantPortletCategory(
			PermissionChecker permissionChecker, long companyId, Layout layout,
			PortletCategory portletCategory,
			Set<String> panelSelectedPortletIds,
			LayoutTypePortlet layoutTypePortlet)
		throws Exception {

		PortletCategory relevantPortletCategory = new PortletCategory(
			portletCategory.getName(), portletCategory.getPortletIds());

		for (PortletCategory curPortletCategory :
				portletCategory.getCategories()) {

			Set<String> portletIds = new HashSet<String>();

			if (curPortletCategory.isHidden()) {
				continue;
			}

			for (String portletId : curPortletCategory.getPortletIds()) {
				Portlet portlet = PortletLocalServiceUtil.getPortletById(
					companyId, portletId);

				if (portlet != null) {
					if (portlet.isSystem()) {
					}
					else if (!portlet.isActive() ||
							 portlet.isUndeployedPortlet()) {
					}
					else if (layout.isTypePanel() &&
							 panelSelectedPortletIds.contains(
									portlet.getRootPortletId())) {

						portletIds.add(portlet.getPortletId());
					}
					else if (layout.isTypePanel() &&
							 !panelSelectedPortletIds.contains(
									portlet.getRootPortletId())) {
					}
					else if (!PortletPermissionUtil.contains(
								permissionChecker, layout, portlet,
								ActionKeys.ADD_TO_PAGE)) {
					}
					else if (!portlet.isInstanceable() &&
							 layoutTypePortlet.hasPortletId(
									portlet.getPortletId())) {

						portletIds.add(portlet.getPortletId());
					}
					else {
						portletIds.add(portlet.getPortletId());
					}
				}
			}

			PortletCategory curRelevantPortletCategory =
				getRelevantPortletCategory(
					permissionChecker, companyId, layout, curPortletCategory,
					panelSelectedPortletIds, layoutTypePortlet);

			curRelevantPortletCategory.setPortletIds(portletIds);

			if (!curRelevantPortletCategory.getCategories().isEmpty() ||
				!portletIds.isEmpty()) {

				relevantPortletCategory.addCategory(curRelevantPortletCategory);
			}
		}

		return relevantPortletCategory;
	}

}