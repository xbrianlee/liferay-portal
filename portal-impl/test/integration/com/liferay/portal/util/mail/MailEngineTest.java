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

package com.liferay.portal.util.mail;

import com.dumbster.smtp.SmtpMessage;

import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.MailServiceTestUtil;
import com.liferay.util.mail.MailEngine;

import java.util.List;

import javax.mail.internet.InternetAddress;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Manuel de la Peña
 */
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class MailEngineTest {

	@Before
	public void setUp() {
		MailServiceTestUtil.start();
	}

	@After
	public void tearDown() {
		MailServiceTestUtil.stop();
	}

	@Test
	public void testSendMail() throws Exception {
		MailMessage mailMessage = new MailMessage(
			new InternetAddress("from@test.com"),
			new InternetAddress("to@test.com"), "Hello",
			"My name is Inigo Montoya.", true);

		MailEngine.send(mailMessage);

		Assert.assertEquals(1, MailServiceTestUtil.getInboxSize());

		List<SmtpMessage> messages = MailServiceTestUtil.getMessages(
			"Body", "My name is Inigo Montoya.");

		Assert.assertEquals(1, messages.size());

		messages = MailServiceTestUtil.getMessages("Subject", "Hello");

		Assert.assertEquals(1, messages.size());

		messages = MailServiceTestUtil.getMessages("To", "to@test.com");

		Assert.assertEquals(1, messages.size());
	}

}