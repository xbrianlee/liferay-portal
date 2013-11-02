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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.model.User;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.TestPropsValues;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Riccardo Ferrari
 * @author Daniel Reuther
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class DefaultScreenNameGeneratorTest {

	@Test
	public void testGenerate() throws Exception {
		String generatedScreenName = _screenNameGenerator.generate(
			TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
			"user123@liferay.com");

		Assert.assertEquals("user123", generatedScreenName);
	}

	@Test
	public void testGenerateDuplicateScreenName() throws Exception {
		User user = TestPropsValues.getUser();

		String screenName = _screenNameGenerator.generate(
			TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
			user.getScreenName() + "@liferay.com");

		Assert.assertNotSame(user.getScreenName(), screenName);
		Assert.assertEquals(user.getScreenName() + ".1", screenName);
	}

	@Test
	public void testGenerateNoEmailAddress() throws Exception {
		String screenName = _screenNameGenerator.generate(
			TestPropsValues.getCompanyId(), TestPropsValues.getUserId(), null);

		if (PropsValues.USERS_SCREEN_NAME_ALLOW_NUMERIC) {
			Assert.assertEquals(
				String.valueOf(TestPropsValues.getUserId()), screenName);
		}
		else {
			Assert.assertEquals(
				"user." + TestPropsValues.getUserId(), screenName);
		}
	}

	@Test
	public void testGenerateNumericEmailAddress() throws Exception {
		String screenName = _screenNameGenerator.generate(
			TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
			"123@liferay.com");

		if (PropsValues.USERS_SCREEN_NAME_ALLOW_NUMERIC) {
			Assert.assertNotSame("user.123", screenName);
			Assert.assertEquals("123", screenName);
		}
		else {
			Assert.assertNotSame("123", screenName);
			Assert.assertEquals("user.123", screenName);
		}
	}

	@Test
	public void testGeneratePostfixEmailAddress() throws Exception {
		String screenName = _screenNameGenerator.generate(
			TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
			"postfix@liferay.com");

		Assert.assertEquals(
			"postfix." + TestPropsValues.getUserId(), screenName);
	}

	private ScreenNameGenerator _screenNameGenerator =
		new DefaultScreenNameGenerator();

}