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
public class MouseOverTestCase extends BaseSeleniumTestCase {

	@Test
	public void testFailMouseOver() throws Exception {
		String expectedException = null;

		if (SELENIUM_LOGGER_ENABLED) {
			expectedException =
				"Command failure \"mouseOver\" with parameter " +
					"\"//Does/Not/Exists\" : null";
		}

		try {
			selenium.mouseOver("//Does/Not/Exists");
		}
		catch (Throwable t) {
			assertEquals(t.getMessage(), expectedException);
		}
	}

	@Test
	public void testMouseOver() throws Exception {
		selenium.mouseOver("//html/body/a/img");
		selenium.waitForVisible("//html/body/a/img[@src='Cat.jpg']");
	}

}