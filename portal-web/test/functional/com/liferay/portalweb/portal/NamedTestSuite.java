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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.InitUtil;
import com.liferay.portalweb.portal.util.SeleniumUtil;
import com.liferay.portalweb.portal.util.liferayselenium.LiferaySelenium;

import junit.framework.TestSuite;

/**
 * @author Brian Wing Shun Chan
 */
public class NamedTestSuite extends TestSuite {

	public NamedTestSuite() {
		InitUtil.initWithSpring();

		LiferaySelenium liferaySelenium = SeleniumUtil.getSelenium();

		if (Validator.isNotNull(liferaySelenium.getPrimaryTestSuiteName())) {
			return;
		}

		Thread currentThread = Thread.currentThread();

		StackTraceElement stackTraceElement = currentThread.getStackTrace()[2];

		liferaySelenium.setPrimaryTestSuiteName(
			stackTraceElement.getClassName());
	}

}