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

package com.liferay.portal.kernel.webdav;

/**
 * @author Brian Wing Shun Chan
 */
public class Status {

	public Status(int code) {
		this(null, code);
	}

	public Status(Object object, int code) {
		_object = object;
		_code = code;
	}

	public int getCode() {
		return _code;
	}

	public Object getObject() {
		return _object;
	}

	private int _code;
	private Object _object;

}