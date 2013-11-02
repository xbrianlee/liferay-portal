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
 * @author Alexander Chow
 */
public class Tuple implements Serializable {

	public Tuple(Object... array) {
		_array = array;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Tuple)) {
			return false;
		}

		Tuple tuple = (Tuple)obj;

		if (tuple._array.length != _array.length) {
			return false;
		}

		for (int i = 0; i < _array.length; i++) {
			if ((tuple._array != null) && (_array[i] != null) &&
				!_array[i].equals(tuple._array[i])) {

				return false;
			}
			else if ((tuple._array[i] == null) || (_array[i] == null)) {
				return false;
			}
		}

		return true;
	}

	public Object getObject(int i) {
		return _array[i];
	}

	public int getSize() {
		return _array.length;
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		for (int i = 0; i < _array.length; i++) {
			hashCode = hashCode ^ _array[i].hashCode();
		}

		return hashCode;
	}

	private Object[] _array;

}