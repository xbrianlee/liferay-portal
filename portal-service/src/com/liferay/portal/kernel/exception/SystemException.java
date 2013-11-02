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

package com.liferay.portal.kernel.exception;

/**
 * The base class for all exceptions caused by system problems. Examples include
 * database connection errors and file not found errors.
 *
 * <p>
 * System exceptions are always unexpected, and generally indicate that the
 * portal is misconfigured or that a critical service is unavailable.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see    PortalException
 */
public class SystemException extends NestableException {

	public SystemException() {
		super();
	}

	public SystemException(String msg) {
		super(msg);
	}

	public SystemException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

}