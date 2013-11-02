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

package com.liferay.portal.kernel.cluster;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

/**
 * @author Shuyang Zhou
 */
public interface ClusterResponseCallback {

	public void callback(BlockingQueue<ClusterNodeResponse> blockingQueue);

	public void callback(ClusterNodeResponses clusterNodeResponses);

	public void processInterruptedException(
		InterruptedException interruptedException);

	public void processTimeoutException(TimeoutException timeoutException);

}