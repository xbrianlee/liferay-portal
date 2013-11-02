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

package com.liferay.portal.upgrade.v6_0_12;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Brett Swaim
 */
public class UpgradeScopes extends BaseUpgradePortletPreferences {

	@Override
	protected void doUpgrade() throws Exception {
		updatePortletPreferences();
	}

	@Override
	protected String getUpdatePortletPreferencesWhereClause() {
		return "preferences like '%lfr-scope-layout-uuid%'";
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		boolean hasScopeType = GetterUtil.getBoolean(
			portletPreferences.getValue("lfr-scope-type", "false"));

		if (!hasScopeType) {
			portletPreferences.setValue("lfr-scope-type", "layout");
		}

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

}