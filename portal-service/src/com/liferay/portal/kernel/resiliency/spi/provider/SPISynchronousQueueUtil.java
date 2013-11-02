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

import com.liferay.portal.kernel.resiliency.spi.SPI;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

/**
 * @author Shuyang Zhou
 */
public class SPISynchronousQueueUtil {

	public static SynchronousQueue<SPI> createSynchronousQueue(String spiUUID) {
		SynchronousQueue<SPI> synchronousQueue = new SynchronousQueue<SPI>();

		_synchronousQueues.put(spiUUID, synchronousQueue);

		return synchronousQueue;
	}

	public static void destroySynchronousQueue(String spiUUID) {
		_synchronousQueues.remove(spiUUID);
	}

	public static void notifySynchronousQueue(String spiUUID, SPI spi)
		throws InterruptedException {

		SynchronousQueue<SPI> synchronousQueue = _synchronousQueues.remove(
			spiUUID);

		if (synchronousQueue == null) {
			throw new IllegalStateException(
				"No SPI synchronous queue with uuid " + spiUUID);
		}

		synchronousQueue.put(spi);
	}

	private static Map<String, SynchronousQueue<SPI>> _synchronousQueues =
		new ConcurrentHashMap<String, SynchronousQueue<SPI>>();

}