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
 * @see    com.liferay.portal.kernel.servlet.NonSerializableObjectHandler
 */
public class TransientValue<V> implements Serializable {

	public TransientValue() {
	}

	public TransientValue(V value) {
		_value = value;
	}

	public V getValue() {
		return _value;
	}

	public boolean isNotNull() {
		return !isNull();
	}

	public boolean isNull() {
		if (_value == null) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setValue(V value) {
		_value = value;
	}

	private transient V _value;

}