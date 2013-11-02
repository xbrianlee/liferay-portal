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

/**
 * @author Brian Wing Shun Chan
 */
public class FloatWrapper
	extends PrimitiveWrapper implements Comparable<FloatWrapper> {

	public static final Class<?> TYPE = Float.TYPE;

	public FloatWrapper() {
		this(0F);
	}

	public FloatWrapper(float value) {
		_value = value;
	}

	@Override
	public int compareTo(FloatWrapper floatWrapper) {
		if (floatWrapper == null) {
			return 1;
		}

		if (getValue() > floatWrapper.getValue()) {
			return 1;
		}
		else if (getValue() < floatWrapper.getValue()) {
			return -1;
		}
		else {
			return 0;
		}
	}

	public float decrement() {
		return --_value;
	}

	public float getValue() {
		return _value;
	}

	public float increment() {
		return ++_value;
	}

	public void setValue(float value) {
		_value = value;
	}

	private float _value;

}