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

package com.liferay.portal;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Raymond Augé
 */
public class RemoteOptionsException extends PortalException {

	public static final int REMOTE_ADDRESS = 1;

	public static final int REMOTE_GROUP_ID = 3;

	public static final int REMOTE_PATH_CONTEXT = 4;

	public static final int REMOTE_PORT = 2;

	public RemoteOptionsException(int type) {
		_type = type;
	}

	public String getRemoteAddress() {
		return _remoteAddress;
	}

	public long getRemoteGroupId() {
		return _remoteGroupId;
	}

	public String getRemotePathContext() {
		return _remotePathContext;
	}

	public int getRemotePort() {
		return _remotePort;
	}

	public int getType() {
		return _type;
	}

	public void setRemoteAddress(String remoteAddress) {
		_remoteAddress = remoteAddress;
	}

	public void setRemoteGroupId(long remoteGroupId) {
		_remoteGroupId = remoteGroupId;
	}

	public void setRemotePathContext(String remotePathContext) {
		_remotePathContext = remotePathContext;
	}

	public void setRemotePort(int remotePort) {
		_remotePort = remotePort;
	}

	private String _remoteAddress;
	private long _remoteGroupId;
	private String _remotePathContext;
	private int _remotePort;
	private int _type;

}