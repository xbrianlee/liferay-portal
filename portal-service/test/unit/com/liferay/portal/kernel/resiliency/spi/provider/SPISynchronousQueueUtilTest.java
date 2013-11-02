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

package com.liferay.portal.kernel.resiliency.spi.provider;

import com.liferay.portal.kernel.resiliency.spi.MockSPI;
import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.test.CodeCoverageAssertor;
import com.liferay.portal.kernel.util.ReflectionUtil;

import java.lang.reflect.Field;

import java.util.Map;
import java.util.concurrent.SynchronousQueue;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class SPISynchronousQueueUtilTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Test
	public void testSPISynchronousQueueUtil() throws Exception {

		// Create

		final String spiUUID = "spiUUID";

		SynchronousQueue<SPI> synchronousQueue =
			SPISynchronousQueueUtil.createSynchronousQueue(spiUUID);

		Map<String, SynchronousQueue<SPI>> synchronizerRegistry =
			_getSynchronousQueues();

		Assert.assertSame(synchronousQueue, synchronizerRegistry.get(spiUUID));

		// Notify nonexistent

		try {
			SPISynchronousQueueUtil.notifySynchronousQueue("nonexistent", null);

			Assert.fail();
		}
		catch (IllegalStateException ise) {
			Assert.assertEquals(
				"No SPI synchronous queue with uuid nonexistent",
				ise.getMessage());
		}

		// Notify existent

		final MockSPI mockSPI = new MockSPI();

		Thread notifyThread = new Thread() {

			@Override
			public void run() {
				try {
					SPISynchronousQueueUtil.notifySynchronousQueue(
						spiUUID, mockSPI);
				}
				catch (InterruptedException ie) {
					Assert.fail(ie.getMessage());
				}
			}

		};

		notifyThread.start();

		Assert.assertSame(mockSPI, synchronousQueue.take());

		// Destroy

		synchronousQueue = SPISynchronousQueueUtil.createSynchronousQueue(
			spiUUID);

		Assert.assertSame(synchronousQueue, synchronizerRegistry.get(spiUUID));

		SPISynchronousQueueUtil.destroySynchronousQueue(spiUUID);

		Assert.assertTrue(synchronizerRegistry.isEmpty());
	}

	private static Map<String, SynchronousQueue<SPI>> _getSynchronousQueues()
		throws Exception {

		Field field = ReflectionUtil.getDeclaredField(
			SPISynchronousQueueUtil.class, "_synchronousQueues");

		return (Map<String, SynchronousQueue<SPI>>)field.get(null);
	}

}