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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.net.InetAddress;

/**
 * @author Tina Tian
 */
public class ClusterNode implements Comparable<ClusterNode>, Serializable {

	public ClusterNode(String clusterNodeId, InetAddress inetAddress) {
		if (clusterNodeId == null) {
			throw new IllegalArgumentException("Cluster node ID is null");
		}

		if (inetAddress == null) {
			throw new IllegalArgumentException("Inet address is null");
		}

		_clusterNodeId = clusterNodeId;
		_inetAddress = inetAddress;
	}

	@Override
	public int compareTo(ClusterNode clusterNode) {
		InetAddress inetAddress = clusterNode._inetAddress;

		String hostAddress = _inetAddress.getHostAddress();

		int value = hostAddress.compareTo(inetAddress.getHostAddress());

		if (value != 0) {
			return value;
		}

		if (_port > clusterNode._port) {
			value = 1;
		}
		else if (_port < clusterNode._port) {
			value = -1;
		}

		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ClusterNode)) {
			return false;
		}

		ClusterNode clusterNode = (ClusterNode)obj;

		if (Validator.equals(_clusterNodeId, clusterNode._clusterNodeId)) {
			return true;
		}

		return false;
	}

	public String getClusterNodeId() {
		return _clusterNodeId;
	}

	public InetAddress getInetAddress() {
		return _inetAddress;
	}

	public int getPort() {
		return _port;
	}

	@Override
	public int hashCode() {
		return _clusterNodeId.hashCode();
	}

	public void setPort(int port) {
		_port = port;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{clusterNodeId=");
		sb.append(_clusterNodeId);
		sb.append(", inetAddress=");
		sb.append(_inetAddress);
		sb.append(", port=");
		sb.append(_port);
		sb.append("}");

		return sb.toString();
	}

	private String _clusterNodeId;
	private InetAddress _inetAddress;
	private int _port;

}