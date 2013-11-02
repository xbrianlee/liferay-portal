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

package com.liferay.portalweb.portal.util.liferayselenium;

import com.liferay.portalweb.portal.util.TestPropsValues;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author Brian Wing Shun Chan
 */
public class ChromeWebDriverImpl extends BaseWebDriverImpl {

	public ChromeWebDriverImpl(String projectDir, String browserURL) {
		super(projectDir, browserURL, new ChromeDriver(_desiredCapabilities));
	}

	private static DesiredCapabilities _desiredCapabilities = null;

	static {
		_desiredCapabilities = DesiredCapabilities.chrome();

		Map<String, Object> preferences = new HashMap<String, Object>();

		preferences.put(
			"download.default_directory", TestPropsValues.OUTPUT_DIR);
		preferences.put("download.prompt_for_download", false);

		_desiredCapabilities.setCapability("chrome.prefs", preferences);
	}

}