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

package com.liferay.portal.service.persistence;

import com.liferay.portal.kernel.template.TemplateException;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.test.AbstractExecutionTestListener;
import com.liferay.portal.kernel.test.TestContext;
import com.liferay.portal.util.PropsValues;

import org.junit.Assert;

/**
 * @author Miguel Pastor
 */
public class PersistenceExecutionTestListener
	extends AbstractExecutionTestListener {

	@Override
	public void runAfterClass(TestContext testContext) {
		PropsValues.SPRING_HIBERNATE_SESSION_DELEGATED = true;
	}

	@Override
	public void runBeforeClass(TestContext testContext) {
		PropsValues.SPRING_HIBERNATE_SESSION_DELEGATED = false;

		try {
			TemplateManagerUtil.init();
		}
		catch (TemplateException te) {
			Assert.fail("The template manager can not been initialized");
		}
	}

}