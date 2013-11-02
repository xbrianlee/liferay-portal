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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portalweb.portal.util.TestPropsValues;

import java.io.File;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * @author Brian Wing Shun Chan
 */
public class FirefoxWebDriverImpl extends BaseWebDriverImpl {

	public FirefoxWebDriverImpl(String projectDir, String browserURL) {
		super(projectDir, browserURL, new FirefoxDriver(_firefoxProfile));
	}

	private static FirefoxProfile _firefoxProfile = new FirefoxProfile();

	static {
		try {
			File file = new File(StringPool.PERIOD);

			String absolutePath = file.getAbsolutePath();

			if (absolutePath.endsWith(StringPool.PERIOD)) {
				absolutePath = absolutePath.substring(
					0, absolutePath.length() - 1);

				absolutePath = StringUtil.replace(
					absolutePath, StringPool.BACK_SLASH,
					StringPool.FORWARD_SLASH);
			}

			_firefoxProfile.addExtension(
				new File(
					absolutePath +
						"tools/selenium/addons/jserrorcollector.xpi"));
		}
		catch (Exception e) {
		}

		_firefoxProfile.setPreference(
			"browser.download.dir", TestPropsValues.OUTPUT_DIR);
		_firefoxProfile.setPreference("browser.download.folderList", 2);
		_firefoxProfile.setPreference(
			"browser.download.manager.showWhenStarting", false);
		_firefoxProfile.setPreference("browser.download.useDownloadDir", true);
		_firefoxProfile.setPreference(
			"browser.helperApps.alwaysAsk.force", false);
		_firefoxProfile.setPreference(
			"browser.helperApps.neverAsk.saveToDisk",
			"application/excel,application/msword,application/pdf," +
				"application/zip,audio/mpeg3,image/jpeg,image/png,text/plain");
		_firefoxProfile.setPreference("dom.max_chrome_script_run_time", 300);
		_firefoxProfile.setPreference("dom.max_script_run_time", 300);
	}

}