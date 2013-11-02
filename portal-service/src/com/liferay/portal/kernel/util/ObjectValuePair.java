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
public class ObjectValuePair<K, V> implements Serializable {

	public ObjectValuePair() {
	}

	public ObjectValuePair(K key, V value) {
		_key = key;
		_value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ObjectValuePair<?, ?>)) {
			return false;
		}

		ObjectValuePair<K, V> kvp = (ObjectValuePair<K, V>)obj;

		if (Validator.equals(_key, kvp._key)) {
			return true;
		}

		return false;
	}

	public K getKey() {
		return _key;
	}

	public V getValue() {
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

	public void setKey(K key) {
		_key = key;
	}

	public void setValue(V value) {
		_value = value;
	}

	private static final long serialVersionUID = 6341296770402285296L;

	private K _key;
	private V _value;

}