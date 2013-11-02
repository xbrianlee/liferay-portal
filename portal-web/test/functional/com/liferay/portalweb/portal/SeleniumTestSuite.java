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

package com.liferay.portalweb.portal;

import com.liferay.portalweb.selenium.AssertPartialTextTestCase;
import com.liferay.portalweb.selenium.AssertTextTestCase;
import com.liferay.portalweb.selenium.ClickAtTestCase;
import com.liferay.portalweb.selenium.ClickTestCase;
import com.liferay.portalweb.selenium.MouseOverTestCase;
import com.liferay.portalweb.selenium.SelectTestCase;
import com.liferay.portalweb.selenium.WaitForVisibleTestCase;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Brian Wing Shun Chan
 */
public class SeleniumTestSuite extends BaseTestSuite {

	public static Test suite() {
		TestSuite testSuite = new TestSuite();

		testSuite.addTestSuite(AssertPartialTextTestCase.class);
		testSuite.addTestSuite(AssertTextTestCase.class);
		testSuite.addTestSuite(ClickAtTestCase.class);
		testSuite.addTestSuite(ClickTestCase.class);
		testSuite.addTestSuite(MouseOverTestCase.class);
		testSuite.addTestSuite(SelectTestCase.class);
		testSuite.addTestSuite(WaitForVisibleTestCase.class);

		testSuite.addTestSuite(StopSeleniumTest.class);

		return testSuite;
	}

}