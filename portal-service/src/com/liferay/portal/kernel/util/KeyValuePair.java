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

package com.liferay.portal.kernel.util;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class KeyValuePair implements Comparable<KeyValuePair>, Serializable {

	public KeyValuePair() {
		this(null, null);
	}

	public KeyValuePair(String key, String value) {
		_key = key;
		_value = value;
	}

	@Override
	public int compareTo(KeyValuePair kvp) {
		return _key.compareTo(kvp.getKey());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KeyValuePair)) {
			return false;
		}

		KeyValuePair kvp = (KeyValuePair)obj;

		if (Validator.equals(_key, kvp._key)) {
			return true;
		}

		return false;
	}

	public String getKey() {
		return _key;
	}

	public String getValue() {
		return _value;
	}

	@Override
	public int hashCode() {
		if (_key != null) {
			return _key.hashCode();
		}
		else {
			return 0;
		}
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setValue(String value) {
		_value = value;
	}

	private String _key;
	private String _value;

}