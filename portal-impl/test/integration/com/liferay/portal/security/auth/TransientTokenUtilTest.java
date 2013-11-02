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
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Shuyang Zhou
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class TransientTokenUtilTest {

	@Test
	public void testCheckTokenExpired() throws Exception {
		String tokenString = TransientTokenUtil.createToken(10);

		Thread.sleep(20);

		Assert.assertFalse(TransientTokenUtil.checkToken(tokenString));
	}

	@Test
	public void testCheckTokenNotExist() {
		Assert.assertFalse(TransientTokenUtil.checkToken("test1"));
		Assert.assertFalse(TransientTokenUtil.checkToken("test2"));
	}

	@Test
	public void testCheckTokenValid() {
		String tokenString = TransientTokenUtil.createToken(100);

		Assert.assertTrue(TransientTokenUtil.checkToken(tokenString));
	}

	@Test
	public void testClearAll() {
		String tokenString = TransientTokenUtil.createToken(100);

		Assert.assertTrue(TransientTokenUtil.checkToken(tokenString));

		TransientTokenUtil.clearAll();

		Assert.assertFalse(TransientTokenUtil.checkToken(tokenString));
	}

}