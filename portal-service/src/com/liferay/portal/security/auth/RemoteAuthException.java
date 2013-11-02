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

package com.liferay.portal.security.auth;

/**
 * @author Zsolt Berentey
 */
public class RemoteAuthException extends AuthException {

	public static final int WRONG_SHARED_SECRET = 101;

	public RemoteAuthException() {
		super();
	}

	public RemoteAuthException(String msg) {
		super(msg);
	}

	public RemoteAuthException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RemoteAuthException(Throwable cause) {
		super(cause);
	}

	public String getURL() {
		return _url;
	}

	public void setURL(String url) {
		_url = url;
	}

	private String _url;

}