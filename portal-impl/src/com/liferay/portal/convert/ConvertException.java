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

package com.liferay.portal.convert;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Alexander Chow
 */
public class ConvertException extends PortalException {

	public ConvertException() {
		super();
	}

	public ConvertException(String msg) {
		super(msg);
	}

	public ConvertException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ConvertException(Throwable cause) {
		super(cause);
	}

}