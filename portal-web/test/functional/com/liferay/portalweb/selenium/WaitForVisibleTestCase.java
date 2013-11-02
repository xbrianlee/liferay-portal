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

package com.liferay.portalweb.selenium;

import org.junit.Test;

/**
 * @author Kwang Lee
 */
public class WaitForVisibleTestCase extends BaseSeleniumTestCase {

	@Test
	public void testFailWaitForVisible() throws Exception {
		String expectedException =
			"Element is not present at \"//Does/Not/Exists\"";

		if (SELENIUM_LOGGER_ENABLED) {
			expectedException =
				"Command failure \"waitForVisible\" with parameter " +
					"\"//Does/Not/Exists\" : " + expectedException;
		}

		try {
			selenium.waitForVisible("//Does/Not/Exists");
		}
		catch (Throwable t) {
			assertEquals(t.getMessage(), expectedException);
		}
	}

	@Test
	public void testWaitForVisible() throws Exception {
		selenium.waitForVisible("//html/body/p[1]/a");
		selenium.clickAt("//html/body/p[1]/a", "");
		selenium.waitForVisible("//html/body/p[1]/a");
		selenium.clickAt("//html/body/p[2]/a", "");
		selenium.waitForVisible("//html/body/p[1]/a");
		selenium.clickAt("//html/body/p[3]/a", "");
		selenium.waitForVisible("//html/body/p[1]/a");
		selenium.clickAt("//html/body/p[4]/a", "");
		selenium.waitForVisible("//html/body/p[1]/a");
		selenium.clickAt("//html/body/p[5]/a", "");
		selenium.waitForVisible("//html/body/p[1]/a");
		selenium.clickAt("//html/body/p[1]/a", "");
	}

}