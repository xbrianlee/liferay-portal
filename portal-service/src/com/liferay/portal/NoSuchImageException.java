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

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchImageException extends NoSuchModelException {

	public NoSuchImageException() {
		super();
	}

	public NoSuchImageException(String msg) {
		super(msg);
	}

	public NoSuchImageException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchImageException(Throwable cause) {
		super(cause);
	}

}