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

import com.liferay.portalweb.selenium.AssertEmailContentTestCase;
import com.liferay.portalweb.selenium.AssertEmailSubjectTestCase;
import com.liferay.portalweb.selenium.ConnectEmailTestCase;
import com.liferay.portalweb.selenium.DeleteEmailsTestCase;
import com.liferay.portalweb.selenium.ReplyEmailTestCase;
import com.liferay.portalweb.selenium.SendEmailTestCase;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Kwang Lee
 */
public class SeleniumEmailTestSuite extends BaseTestSuite {

	public static Test suite() {
		TestSuite testSuite = new TestSuite();

		testSuite.addTestSuite(AssertEmailContentTestCase.class);
		testSuite.addTestSuite(AssertEmailSubjectTestCase.class);
		testSuite.addTestSuite(ConnectEmailTestCase.class);
		testSuite.addTestSuite(DeleteEmailsTestCase.class);
		testSuite.addTestSuite(ReplyEmailTestCase.class);
		testSuite.addTestSuite(SendEmailTestCase.class);

		testSuite.addTestSuite(StopSeleniumTest.class);

		return testSuite;
	}

}