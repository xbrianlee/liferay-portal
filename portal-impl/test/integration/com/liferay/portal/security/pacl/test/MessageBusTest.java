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

package com.liferay.portal.security.pacl.test;

import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.security.pacl.PACLExecutionTestListener;
import com.liferay.portal.security.pacl.PACLIntegrationJUnitTestRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Raymond Augé
 */
@ExecutionTestListeners(listeners = {PACLExecutionTestListener.class})
@RunWith(PACLIntegrationJUnitTestRunner.class)
public class MessageBusTest {

	@Test
	public void testListen1() throws Exception {
		try {
			Object value = MessageBusUtil.sendSynchronousMessage(
				"liferay/test_pacl_listen_failure", "Listen Failure");

			Assert.assertNull(value);
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void testListen2() throws Exception {
		try {
			Object value = MessageBusUtil.sendSynchronousMessage(
				"liferay/test_pacl_listen_success", "Listen Success");

			Assert.assertEquals("Listen Success", value);
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void testSend1() throws Exception {
		try {
			MessageBusUtil.sendMessage(
				"liferay/test_pacl_send_failure", "Send Failure");

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void testSend2() throws Exception {
		try {
			MessageBusUtil.sendMessage(
				"liferay/test_pacl_send_success", "Send Success");
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

}