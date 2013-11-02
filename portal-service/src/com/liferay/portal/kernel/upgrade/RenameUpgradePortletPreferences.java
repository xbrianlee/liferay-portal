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

import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Marcellus Tavares
 */
public abstract class RenameUpgradePortletPreferences
	extends BaseUpgradePortletPreferences {

	protected abstract Map<String, String> getPreferenceNamesMap();

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences preferences = PortletPreferencesFactoryUtil.fromXML(
			companyId, ownerId, ownerType, plid, portletId, xml);

		Map<String, String[]> preferencesMap = preferences.getMap();

		Map<String, String> preferenceNamesMap = getPreferenceNamesMap();

		for (String name : preferenceNamesMap.keySet()) {
			String[] values = preferencesMap.get(name);

			if (values == null) {
				continue;
			}

			preferences.reset(name);

			preferences.setValues(preferenceNamesMap.get(name), values);
		}

		return PortletPreferencesFactoryUtil.toXML(preferences);
	}

}