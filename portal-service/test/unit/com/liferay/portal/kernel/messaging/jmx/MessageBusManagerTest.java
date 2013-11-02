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

package com.liferay.portal.kernel.messaging.jmx;

import com.liferay.portal.kernel.messaging.MessageBus;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Michael C. Han
 * @author Miguel Pastor
 */
@RunWith(PowerMockRunner.class)
public class MessageBusManagerTest {

	@Before
	public void setUp() throws Exception {
		_mBeanServer = ManagementFactory.getPlatformMBeanServer();
	}

	@Test
	public void testRegisterMBean() throws Exception {
		_mBeanServer.registerMBean(
			new MessageBusManager(_messageBus),
			MessageBusManager.createObjectName());

		Assert.assertTrue(
			_mBeanServer.isRegistered(MessageBusManager.createObjectName()));
	}

	private MBeanServer _mBeanServer;

	@Mock
	private MessageBus _messageBus;

}