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

package com.liferay.portal.upgrade.v6_2_0;

import com.liferay.portal.kernel.upgrade.RenameUpgradePortletPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class UpgradeDynamicDataListDisplay
	extends RenameUpgradePortletPreferences {

	public UpgradeDynamicDataListDisplay() {
		_preferenceNamesMap.put("detailDDMTemplateId", "formDDMTemplateId");
		_preferenceNamesMap.put("listDDMTemplateId", "displayDDMTemplateId");
	}

	@Override
	protected String[] getPortletIds() {
		return new String[] {"169_INSTANCE_%"};
	}

	@Override
	protected Map<String, String> getPreferenceNamesMap() {
		return _preferenceNamesMap;
	}

	private Map<String, String> _preferenceNamesMap =
		new HashMap<String, String>();

}