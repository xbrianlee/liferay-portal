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
public class RemoteExportException extends PortalException {

	public static final int BAD_CONNECTION = 1;

	public static final int NO_GROUP = 2;

	public static final int NO_LAYOUTS = 3;

	public static final int NO_PERMISSIONS = 4;

	public RemoteExportException(int type) {
		_type = type;
	}

	public RemoteExportException(int type, String msg) {
		super(msg);

		_type = type;
	}

	public long getGroupId() {
		return _groupId;
	}

	public int getType() {
		return _type;
	}

	public String getURL() {
		return _url;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setURL(String url) {
		_url = url;
	}

	private long _groupId;
	private int _type;
	private String _url;

}