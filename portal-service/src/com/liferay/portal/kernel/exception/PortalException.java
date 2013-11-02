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
 * The base class for all exceptions related to business logic. Examples include
 * invalid input, portlet errors, and references to non existent database
 * records.
 *
 * <p>
 * Portal exceptions are generally caused by user error, and do not indicate
 * that anything is wrong with the portal itself.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see    SystemException
 */
public class PortalException extends NestableException {

	public PortalException() {
		super();
	}

	public PortalException(String msg) {
		super(msg);
	}

	public PortalException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public PortalException(Throwable cause) {
		super(cause);
	}

}