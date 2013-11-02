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

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 * @author Zsolt Berentey
 */
public class AuthException extends PortalException {

	public static final int INTERNAL_SERVER_ERROR = 1;

	public static final int INVALID_SHARED_SECRET = 2;

	public static final int NO_SHARED_SECRET = 3;

	public AuthException() {
		super();
	}

	public AuthException(String msg) {
		super(msg);
	}

	public AuthException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AuthException(Throwable cause) {
		super(cause);
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private int _type;

}