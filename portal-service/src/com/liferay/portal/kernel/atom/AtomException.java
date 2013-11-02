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

package com.liferay.portal.kernel.atom;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Igor Spasic
 */
public class AtomException extends PortalException {

	public AtomException(int errorCode) {
		super(String.valueOf(errorCode));

		_errorCode = errorCode;
	}

	public AtomException(int errorCode, Throwable cause) {
		super(String.valueOf(errorCode), cause);

		_errorCode = errorCode;
	}

	public AtomException(String msg) {
		super(msg);
	}

	public int getErrorCode() {
		return _errorCode;
	}

	private int _errorCode = AtomCollectionAdapter.SC_INTERNAL_SERVER_ERROR;

}