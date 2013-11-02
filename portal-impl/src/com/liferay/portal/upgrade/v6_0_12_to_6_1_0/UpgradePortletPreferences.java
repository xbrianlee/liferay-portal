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

package com.liferay.portal.upgrade.v6_0_12_to_6_1_0;

import com.liferay.portal.kernel.upgrade.CamelCaseUpgradePortletPreferences;
import com.liferay.portal.upgrade.v6_1_0.UpgradeCommunityProperties;

/**
 * @author Julio Camarero
 * @author Douglas Wong
 */
public class UpgradePortletPreferences
	extends CamelCaseUpgradePortletPreferences {

	@Override
	protected void doUpgrade() throws Exception {
		super.doUpgrade();

		upgrade(UpgradeCommunityProperties.class);
	}

	@Override
	protected String[] getPortletIds() {
		return _CAMEL_CASE_UPGRADE_PORTLET_IDS;
	}

	private static final String[] _CAMEL_CASE_UPGRADE_PORTLET_IDS = {
		"15", "19", "20", "33", "34", "36", "39_INSTANCE_%", "47_INSTANCE_%",
		"48_INSTANCE_%", "54_INSTANCE_%", "56_INSTANCE_%", "59_INSTANCE_%",
		"62_INSTANCE_%", "71_INSTANCE_%", "73_INSTANCE_%", "77",
		"82_INSTANCE_%", "85_INSTANCE_%", "100", "101_INSTANCE_%",
		"102_INSTANCE_%", "114", "115", "118_INSTANCE_%", "122_INSTANCE_%"
	};

}