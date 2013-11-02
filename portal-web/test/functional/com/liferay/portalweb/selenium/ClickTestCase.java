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
public class ClickTestCase extends BaseSeleniumTestCase {

	@Test
	public void testClick() throws Exception {
		selenium.click("//html/body/p[1]/a");
		selenium.click("//html/body/p[2]/a");
		selenium.click("//html/body/p[3]/a");
		selenium.click("//html/body/p[4]/a");
		selenium.click("//html/body/p[5]/a");
		selenium.click("//html/body/p[6]/a");
		selenium.click("//html/body/p[1]/a");
	}

	@Test
	public void testFailClick() throws Exception {
		String expectedException = null;

		if (SELENIUM_LOGGER_ENABLED) {
			expectedException =
				"Command failure \"click\" with parameter " +
					"\"//Does/Not/Exists\" : null";
		}

		try {
			selenium.click("//Does/Not/Exists");
		}
		catch (Throwable t) {
			assertEquals(t.getMessage(), expectedException);
		}
	}

}