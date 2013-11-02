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

package com.liferay.portal.kernel.upgrade;

import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Julio Camarero
 */
public class CamelCaseUpgradePortletPreferences
	extends BaseUpgradePortletPreferences {

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		Map<String, String[]> preferencesMap = portletPreferences.getMap();

		for (String oldName : preferencesMap.keySet()) {
			String[] values = preferencesMap.get(oldName);

			String newName = TextFormatter.format(oldName, TextFormatter.M);

			portletPreferences.reset(oldName);

			portletPreferences.setValues(newName, values);
		}

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

}