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
public class ClickAtTestCase extends BaseSeleniumTestCase {

	@Test
	public void testClickAt() throws Exception {
		selenium.clickAt("//html/body/p[1]/a", "");
		selenium.clickAt("//html/body/p[2]/a", "");
		selenium.clickAt("//html/body/p[3]/a", "");
		selenium.clickAt("//html/body/p[4]/a", "");
		selenium.clickAt("//html/body/p[5]/a", "");
		selenium.clickAt("//html/body/p[6]/a", "");
		selenium.clickAt("//html/body/p[1]/a", "");
	}

	@Test
	public void testFailClickAt() throws Exception {
		String expectedException = null;

		if (SELENIUM_LOGGER_ENABLED) {
			expectedException =
				"Command failure \"clickAt\" with parameters " +
					"\"//Does/Not/Exists\" \"\" : null";
		}

		try {
			selenium.clickAt("//Does/Not/Exists", "");
		}
		catch (Throwable t) {
			assertEquals(t.getMessage(), expectedException);
		}
	}

}