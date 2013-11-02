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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Tina Tian
 */
public class FutureClusterResponses implements Future<ClusterNodeResponses> {

	public FutureClusterResponses(List<Address> addresses) {
		_clusterNodeResponses = new ClusterNodeResponses();
		_countDownLatch = new CountDownLatch(addresses.size());
		_expectedReplyAddress = new HashSet<Address>(addresses);
	}

	public void addClusterNodeResponse(
		ClusterNodeResponse clusterNodeResponse) {

		_clusterNodeResponses.addClusterResponse(clusterNodeResponse);

		_countDownLatch.countDown();
	}

	public void addExpectedReplyAddress(Address address) {
		_expectedReplyAddress.add(address);
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		if (_cancelled || isDone()) {
			return false;
		}

		_cancelled = true;

		return true;
	}

	public boolean expectsReply(Address address) {
		return _expectedReplyAddress.contains(address);
	}

	@Override
	public ClusterNodeResponses get() throws InterruptedException {
		if (_cancelled) {
			throw new CancellationException();
		}

		_countDownLatch.await();

		return _clusterNodeResponses;
	}

	@Override
	public ClusterNodeResponses get(long timeout, TimeUnit timeUnit)
		throws InterruptedException, TimeoutException {

		if (_cancelled) {
			throw new CancellationException();
		}

		if (_countDownLatch.await(timeout, timeUnit)) {
			return _clusterNodeResponses;
		}
		else {
			throw new TimeoutException();
		}
	}

	public BlockingQueue<ClusterNodeResponse> getPartialResults() {
		return _clusterNodeResponses.getClusterResponses();
	}

	@Override
	public boolean isCancelled() {
		return _cancelled;
	}

	@Override
	public boolean isDone() {
		if ((_countDownLatch.getCount() == 0) || _cancelled) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean _cancelled;
	private ClusterNodeResponses _clusterNodeResponses;
	private CountDownLatch _countDownLatch;
	private Set<Address> _expectedReplyAddress;

}