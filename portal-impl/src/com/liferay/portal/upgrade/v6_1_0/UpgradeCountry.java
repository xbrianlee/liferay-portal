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

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Kenneth Chang
 */
public class UpgradeCountry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		for (String name : _NAMES) {
			runSQL(
				"update Country set zipRequired = FALSE where name = '" + name +
					"'");
		}
	}

	private static final String[] _NAMES = {
		"Angola", "Antigua", "Aruba", "Bahamas", "Belize", "Benin", "Botswana",
		"Burkina Faso", "Burundi", "Central African Republic", "Comoros",
		"Republic of Congo", "Democratic Republic of Congo", "Cook Islands",
		"Djibouti", "Dominica", "Equatorial Guinea", "Eritrea", "Fiji Islands",
		"Gambia", "Ghana", "Grenada", "Guinea", "Guyana", "Ireland", "Kiribati",
		"North Korea", "Macau", "Malawi", "Mali", "Mauritania", "Mauritius",
		"Montserrat", "Nauru", "Niue", "Qatar", "Rwanda", "St. Kitts",
		"St. Lucia", "Sao Tome & Principe", "Seychelles", "Sierra Leone",
		"Solomon Islands", "Somalia", "Suriname", "Syria", "Tanzania", "Tonga",
		"Trinidad & Tobago", "Tuvalu", "Uganda", "United Arab Emirates",
		"Vanuatu", "Yemen", "Zimbabwe"
	};

}