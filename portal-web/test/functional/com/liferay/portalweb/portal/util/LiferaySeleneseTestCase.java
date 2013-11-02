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

package com.liferay.portalweb.portal.util;

import com.liferay.portalweb.portal.util.liferayselenium.LiferaySelenium;

import com.thoughtworks.selenium.SeleneseTestBase;
import com.thoughtworks.selenium.SeleneseTestCase;

/**
 * @author Brian Wing Shun Chan
 */
@SuppressWarnings("deprecation")
public class LiferaySeleneseTestCase extends SeleneseTestCase {

	public static void assertEquals(String expected, String actual) {
		SeleneseTestBase.assertEquals(
			RuntimeVariables.replace(expected),
			RuntimeVariables.replace(actual));
	}

	public static void assertNotEquals(String expected, String actual) {
		SeleneseTestBase.assertNotEquals(
			RuntimeVariables.replace(expected),
			RuntimeVariables.replace(actual));
	}

	public LiferaySeleneseTestCase() {
		super();
	}

	public LiferaySeleneseTestCase(String name) {
		super(name);
	}

	protected LiferaySelenium selenium;

}