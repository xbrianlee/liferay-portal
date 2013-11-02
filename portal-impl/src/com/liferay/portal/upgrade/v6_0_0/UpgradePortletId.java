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

package com.liferay.portal.upgrade.v6_0_0;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradePortletId
	extends com.liferay.portal.upgrade.util.UpgradePortletId {

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			new String[] {
				"7", "1_WAR_biblegatewayportlet"
			},
			new String[] {
				"21", "1_WAR_randombibleverseportlet"
			},
			new String[] {
				"46", "1_WAR_gospelforasiaportlet"
			},
			new String[] {
				"1_WAR_wolportlet", "1_WAR_socialnetworkingportlet"
			},
			new String[] {
				"2_WAR_wolportlet", "1_WAR_socialcodingportlet"
			},
			new String[] {
				"3_WAR_wolportlet", "2_WAR_socialcodingportlet"
			},
			new String[] {
				"4_WAR_wolportlet", "2_WAR_socialnetworkingportlet"
			},
			new String[] {
				"5_WAR_wolportlet", "3_WAR_socialnetworkingportlet"
			},
			new String[] {
				"6_WAR_wolportlet", "4_WAR_socialnetworkingportlet"
			},
			new String[] {
				"7_WAR_wolportlet", "5_WAR_socialnetworkingportlet"
			},
			new String[] {
				"8_WAR_wolportlet", "6_WAR_socialnetworkingportlet"
			},
			new String[] {
				"9_WAR_wolportlet", "7_WAR_socialnetworkingportlet"
			},
			new String[] {
				"10_WAR_wolportlet", "8_WAR_socialnetworkingportlet"
			}
		};
	}

}