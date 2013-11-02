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

import java.io.Serializable;

/**
 * @author Tina Tian
 */
public class ClusterNodeResponse implements Serializable {

	public Address getAddress() {
		return _address;
	}

	public ClusterMessageType getClusterMessageType() {
		return _clusterMessageType;
	}

	public ClusterNode getClusterNode() {
		return _clusterNode;
	}

	public Exception getException() {
		return _exception;
	}

	public Object getResult() throws Exception {
		if (_exception != null) {
			throw _exception;
		}

		return _result;
	}

	public String getUuid() {
		return _uuid;
	}

	public boolean hasException() {
		if (_exception != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isMulticast() {
		return _multicast;
	}

	public void setAddress(Address address) {
		_address = address;
	}

	public void setClusterMessageType(ClusterMessageType clusterMessageType) {
		_clusterMessageType = clusterMessageType;
	}

	public void setClusterNode(ClusterNode clusterNode) {
		_clusterNode = clusterNode;
	}

	public void setException(Exception exception) {
		_exception = exception;
	}

	public void setMulticast(boolean multicast) {
		_multicast = multicast;
	}

	public void setResult(Object result) {
		_result = result;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{clusterMessageType=");
		sb.append(_clusterMessageType);

		boolean clusterMessageTypeNotifyOrUpdate = false;

		if (_clusterMessageType.equals(ClusterMessageType.NOTIFY) ||
			_clusterMessageType.equals(ClusterMessageType.UPDATE)) {

			clusterMessageTypeNotifyOrUpdate = true;
		}

		if (clusterMessageTypeNotifyOrUpdate) {
			sb.append(", clusterNode=");
			sb.append(_clusterNode);
		}

		if (!clusterMessageTypeNotifyOrUpdate && hasException()) {
			sb.append(", exception=");
			sb.append(_exception);
		}

		sb.append(", multicast=");
		sb.append(_multicast);

		if (!clusterMessageTypeNotifyOrUpdate && !hasException()) {
			sb.append(", result=");
			sb.append(_result);
		}

		sb.append(", uuid=");
		sb.append(_uuid);
		sb.append("}");

		return sb.toString();
	}

	private Address _address;
	private ClusterMessageType _clusterMessageType;
	private ClusterNode _clusterNode;
	private Exception _exception;
	private boolean _multicast;
	private Object _result;
	private String _uuid;

}