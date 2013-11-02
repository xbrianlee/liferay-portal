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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan McCann
 */
public class UserFieldException extends PortalException {

	public UserFieldException() {
		super();
	}

	public UserFieldException(String msg) {
		super(msg);
	}

	public UserFieldException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public UserFieldException(Throwable cause) {
		super(cause);
	}

	public void addField(String field) {
		_fields.add(field);
	}

	public List<String> getFields() {
		return _fields;
	}

	public boolean hasFields() {
		return !_fields.isEmpty();
	}

	private List<String> _fields = new ArrayList<String>();

}