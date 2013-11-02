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

package com.liferay.portal.upgrade.v6_1_0;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Julio Camarero
 */
public class UpgradeNavigation extends BaseUpgradePortletPreferences {

	@Override
	protected String[] getPortletIds() {
		return new String[] {"71_INSTANCE_%"};
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		String displayStyle = portletPreferences.getValue(
			"display-style", null);

		if (Validator.isNumber(displayStyle)) {
			int index = GetterUtil.getInteger(displayStyle);

			if ((index < 0) || (index > 6)) {
				index = 0;
			}

			portletPreferences.setValue(
				"display-style", _DISPLAY_STYLES[index]);
		}

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

	private static final String[] _DISPLAY_STYLES = {
		"", "relative-with-breadcrumb", "from-level-2-with-title",
		"from-level-1-with-title", "from-level-1",
		"from-level-1-to-all-sublevels", "from-level-0"
	};

}