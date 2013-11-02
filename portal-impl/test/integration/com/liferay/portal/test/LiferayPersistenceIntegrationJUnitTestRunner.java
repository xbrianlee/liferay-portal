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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.tools.DBUpgrader;

import java.util.Arrays;
import java.util.List;

import org.junit.runners.model.InitializationError;

/**
 * @author Miguel Pastor
 */
public class LiferayPersistenceIntegrationJUnitTestRunner
	extends CustomizableSpringContextJUnitTestRunner {

	public LiferayPersistenceIntegrationJUnitTestRunner(Class<?> clazz)
		throws InitializationError {

		super(clazz);
	}

	@Override
	public void afterApplicationContextInit() {
		try {
			DBUpgrader.upgrade();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public List<String> getExtraConfigLocations() {
		return Arrays.asList("META-INF/test-persistence-spring.xml");
	}

	private static Log _log = LogFactoryUtil.getLog(
		LiferayPersistenceIntegrationJUnitTestRunner.class);

}