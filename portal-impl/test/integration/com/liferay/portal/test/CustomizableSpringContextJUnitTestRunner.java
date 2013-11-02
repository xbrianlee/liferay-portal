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

package com.liferay.portal.test;

import com.liferay.portal.util.InitUtil;

import java.util.List;

import org.junit.runners.model.InitializationError;

/**
 * @author Miguel Pastor
 */
public abstract class CustomizableSpringContextJUnitTestRunner
	extends LiferayIntegrationJUnitTestRunner {

	public CustomizableSpringContextJUnitTestRunner(Class<?> clazz)
		throws InitializationError {

		super(clazz);
	}

	public abstract void afterApplicationContextInit();

	public abstract List<String> getExtraConfigLocations();

	@Override
	public void initApplicationContext() {
		System.setProperty("catalina.base", ".");

		InitUtil.initWithSpring(getExtraConfigLocations());
	}

}