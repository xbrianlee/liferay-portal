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

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Michael C. Han
 */
public class FutureClusterResponsesTest {

	@Test
	public void testMultipleResponseFailure() throws UnknownHostException {
		List<Address> addresses = new ArrayList<Address>();

		addresses.add(new MockAddress("1.2.3.4"));
		addresses.add(new MockAddress("1.2.3.5"));
		addresses.add(new MockAddress("1.2.3.6"));

		FutureClusterResponses clusterNodeResponses =
			new FutureClusterResponses(addresses);

		ClusterNodeResponse clusterNodeResponse1 = new ClusterNodeResponse();

		clusterNodeResponse1.setClusterNode(
			new ClusterNode("1.2.3.4", InetAddress.getLocalHost()));

		clusterNodeResponses.addClusterNodeResponse(clusterNodeResponse1);

		ClusterNodeResponse clusterNodeResponse2 = new ClusterNodeResponse();

		clusterNodeResponse2.setClusterNode(
			new ClusterNode("1.2.3.5", InetAddress.getLocalHost()));

		clusterNodeResponses.addClusterNodeResponse(clusterNodeResponse2);

		try {
			clusterNodeResponses.get(500, TimeUnit.MILLISECONDS);

			Assert.fail("Should have failed");
		}
		catch (InterruptedException ie) {
			Assert.fail("Interrupted");
		}
		catch (TimeoutException te) {
		}
	}

	@Test
	public void testMultipleResponseSuccess() throws UnknownHostException {
		List<Address> addresses = new ArrayList<Address>();

		addresses.add(new MockAddress("1.2.3.4"));
		addresses.add(new MockAddress("1.2.3.5"));
		addresses.add(new MockAddress("1.2.3.6"));

		FutureClusterResponses clusterNodeResponses =
			new FutureClusterResponses(addresses);

		ClusterNodeResponse clusterNodeResponse1 = new ClusterNodeResponse();

		clusterNodeResponse1.setClusterNode(
			new ClusterNode("1.2.3.4", InetAddress.getLocalHost()));

		clusterNodeResponses.addClusterNodeResponse(clusterNodeResponse1);

		ClusterNodeResponse clusterNodeResponse2 = new ClusterNodeResponse();

		clusterNodeResponse2.setClusterNode(
			new ClusterNode("1.2.3.5", InetAddress.getLocalHost()));

		clusterNodeResponses.addClusterNodeResponse(clusterNodeResponse2);

		ClusterNodeResponse clusterNodeResponse3 = new ClusterNodeResponse();

		clusterNodeResponse3.setClusterNode(
			new ClusterNode("1.2.3.6", InetAddress.getLocalHost()));

		clusterNodeResponses.addClusterNodeResponse(clusterNodeResponse3);

		try {
			clusterNodeResponses.get(500, TimeUnit.MILLISECONDS);
		}
		catch (InterruptedException ie) {
			Assert.fail("Interrupted");
		}
		catch (TimeoutException te) {
			Assert.fail("Timed out");
		}
	}

	@Test
	public void testSingleResponseFailure() {
		List<Address> addresses = new ArrayList<Address>();

		addresses.add(new MockAddress("1.2.3.4"));

		FutureClusterResponses futureClusterResponses =
			new FutureClusterResponses(addresses);

		try {
			futureClusterResponses.get(500, TimeUnit.MILLISECONDS);

			Assert.fail("Should have failed");
		}
		catch (InterruptedException ie) {
			Assert.fail("Interrupted");
		}
		catch (TimeoutException te) {
		}
	}

	@Test
	public void testSingleResponseSuccess() throws UnknownHostException {
		List<Address> addresses = new ArrayList<Address>();

		addresses.add(new MockAddress("1.2.3.4"));

		FutureClusterResponses futureClusterResponses =
			new FutureClusterResponses(addresses);

		ClusterNodeResponse clusterNodeResponse = new ClusterNodeResponse();

		clusterNodeResponse.setClusterNode(
			new ClusterNode("test", InetAddress.getLocalHost()));

		futureClusterResponses.addClusterNodeResponse(clusterNodeResponse);

		try {
			futureClusterResponses.get(500, TimeUnit.MILLISECONDS);
		}
		catch (InterruptedException ie) {
			Assert.fail("Interrupted");
		}
		catch (TimeoutException te) {
			Assert.fail("Timed out");
		}
	}

	private class MockAddress implements Address {

		public MockAddress(String address) {
			_address = address;
		}

		@Override
		public String getDescription() {
			return _address;
		}

		@Override
		public Object getRealAddress() {
			return _address;
		}

		private String _address;

	}

}