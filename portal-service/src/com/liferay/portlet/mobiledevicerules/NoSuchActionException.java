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

package com.liferay.portlet.mobiledevicerules;

import com.liferay.portal.NoSuchModelException;

/**
 * @author Edward C. Han
 */
public class NoSuchActionException extends NoSuchModelException {

	public NoSuchActionException() {
		super();
	}

	public NoSuchActionException(String msg) {
		super(msg);
	}

	public NoSuchActionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchActionException(Throwable cause) {
		super(cause);
	}

}