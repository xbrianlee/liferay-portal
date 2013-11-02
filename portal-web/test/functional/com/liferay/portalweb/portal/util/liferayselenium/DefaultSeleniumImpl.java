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

import com.liferay.portalweb.portal.util.TestPropsValues;

import com.thoughtworks.selenium.DefaultSelenium;

/**
 * @author Brian Wing Shun Chan
 */
public class DefaultSeleniumImpl extends BaseSeleniumImpl {

	public DefaultSeleniumImpl(String projectDir, String browserURL) {
		super(
			projectDir,
			new DefaultSelenium(
				TestPropsValues.SELENIUM_HOST, TestPropsValues.SELENIUM_PORT,
				TestPropsValues.BROWSER_TYPE, browserURL));
	}

}