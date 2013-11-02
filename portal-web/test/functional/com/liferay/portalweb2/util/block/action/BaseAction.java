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

package com.liferay.portalweb2.util.block.action;

import com.liferay.portalweb.portal.util.liferayselenium.LiferaySelenium;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Michael Hashimoto
 */
public class BaseAction {

	public BaseAction(LiferaySelenium liferaySelenium) {
		this.liferaySelenium = liferaySelenium;
	}

	protected String getLocator(
		String locator, String locatorKey, Map<String, String> variables)
			throws Exception {

		if (locator != null) {
			return locator;
		}

		if (paths.containsKey(locatorKey)) {
			String locatorValue = paths.get(locatorKey);

			if (locatorValue.contains("${") && locatorValue.contains("}")) {
				String regex = "\\$\\{[^}]*?\\}";

				Pattern pattern = Pattern.compile(regex);

				Matcher matcher = pattern.matcher(locatorValue);

				while (matcher.find()) {
					String variable = matcher.group();

					int x = variable.indexOf("${");
					int y = variable.indexOf("}");

					String variableKey = variable.substring(x + 2, y);

					if (variables.containsKey(variableKey)) {
						locatorValue = locatorValue.replaceFirst(
							regex, variables.get(variableKey));
					}
					else {
						throw new Exception(
							"Variable \"" + variableKey + "\" found in \"" +
								paths.get(locatorKey) + "\" is not set");
					}
				}
			}

			return locatorValue;
		}

		return locatorKey;
	}

	protected LiferaySelenium liferaySelenium;
	protected Map<String, String> paths = new HashMap<String, String>();

}