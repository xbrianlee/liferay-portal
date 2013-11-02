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

package com.liferay.portal.upgrade.v6_0_3;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Julio Camarero
 */
public class UpgradeLookAndFeel extends BaseUpgradePortletPreferences {

	@Override
	protected String getUpdatePortletPreferencesWhereClause() {
		return "preferences like '%portlet-setup-link-to-%'";
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		long linkToLayoutId = GetterUtil.getLong(
			portletPreferences.getValue(
				"portlet-setup-link-to-layout-id", null));

		if (linkToLayoutId <= 0) {
			linkToLayoutId = GetterUtil.getLong(
				portletPreferences.getValue(
					"portlet-setup-link-to-plid", null));
		}

		if (linkToLayoutId > 0) {
			String uuid = getLayoutUuid(plid, linkToLayoutId);

			if (uuid != null) {
				portletPreferences.setValue(
					"portlet-setup-link-to-layout-uuid", uuid);
			}

			portletPreferences.reset("portlet-setup-link-to-layout-id");
			portletPreferences.reset("portlet-setup-link-to-plid");
		}

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

}