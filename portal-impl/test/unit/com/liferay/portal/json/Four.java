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

package com.liferay.portal.json;

import com.liferay.portal.kernel.json.JSON;

/**
 * @author Igor Spasic
 */
@JSON(strict = true)
public class Four {

	@JSON
	public int getNumber() {
		return _number;
	}

	public long getPrivate() {
		return _private;
	}

	public String getValue() {
		return _value;
	}

	public void setNumber(int number) {
		_number = number;
	}

	public void setPrivate(long aPrivate) {
		_private = aPrivate;
	}

	public void setValue(String value) {
		_value = value;
	}

	private int _number = 173;
	private long _private = 0xCAFEBABE;

	@JSON
	private String _value = "something";

}