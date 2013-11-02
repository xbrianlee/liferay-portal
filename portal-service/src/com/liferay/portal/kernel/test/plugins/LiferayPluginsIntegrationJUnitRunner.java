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

package com.liferay.portal.kernel.test.plugins;

import com.liferay.portal.kernel.test.AbstractIntegrationJUnitTestRunner;

import org.junit.runners.model.InitializationError;

/**
 * @author Manuel de la Peña
 */
public class LiferayPluginsIntegrationJUnitRunner
	extends AbstractIntegrationJUnitTestRunner {

	public LiferayPluginsIntegrationJUnitRunner(Class<?> clazz)
		throws InitializationError {

		super(clazz);
	}

	@Override
	public void initApplicationContext() {

		// It is not necessary to initialize the application context because a
		// portal instance must be up and running

	}

}