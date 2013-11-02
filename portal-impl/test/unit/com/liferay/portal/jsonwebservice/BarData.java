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

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.kernel.json.JSON;

/**
 * @author Igor Spasic
 */
public class BarData {

	@JSON
	public int[] getArray() {
		return _array;
	}

	@JSON(include = false)
	public String getSecret() {
		return _secret;
	}

	public String getValue() {
		return _value;
	}

	public void setArray(int[] array) {
		_array = array;
	}

	public void setSecret(String secret) {
		_secret = secret;
	}

	public void setValue(String value) {
		_value = value;
	}

	private int[] _array = new int[] {1, 2, 3};
	private String _secret = "secret";
	private String _value = "value";

}