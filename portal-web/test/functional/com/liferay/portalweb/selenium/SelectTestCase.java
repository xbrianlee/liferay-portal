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
public class SelectTestCase extends BaseSeleniumTestCase {

	@Test
	public void testFailSelect() throws Exception {
		String expectedException = null;

		if (SELENIUM_LOGGER_ENABLED) {
			expectedException =
				"Command failure \"select\" with parameters " +
					"\"//Does/Not/Exists\" \"value=Failure\" : null";
		}

		try {
			selenium.select("//Does/Not/Exists", "value=Failure");
		}
		catch (Throwable t) {
			assertEquals(t.getMessage(), expectedException);
		}
	}

	@Test
	public void testSelect() throws Exception {
		String locator = "//html/body/div[1]/select";

		selenium.select(locator, "index=3");

		selenium.assertSelectedLabel(locator, "Test 3");

		selenium.select(locator, "Test 1");

		selenium.assertSelectedLabel(locator, "Test 1");

		selenium.select(locator, "label=regexp:.*2.*");

		selenium.assertSelectedLabel(locator, "Test 2");

		selenium.select(locator, "label=Test 3");

		selenium.assertSelectedLabel(locator, "Test 3");

		selenium.select(locator, "value=regexp:.*1.*");

		selenium.assertSelectedLabel(locator, "Test 1");

		selenium.select(locator, "value=Test 2");

		selenium.assertSelectedLabel(locator, "Test 2");
	}

}