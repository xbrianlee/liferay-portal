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
public class IntegerWrapper
	extends PrimitiveWrapper implements Comparable<IntegerWrapper> {

	public static final Class<?> TYPE = Integer.TYPE;

	public IntegerWrapper() {
		this(0);
	}

	public IntegerWrapper(int value) {
		_value = value;
	}

	@Override
	public int compareTo(IntegerWrapper integerWrapper) {
		if (integerWrapper == null) {
			return 1;
		}

		if (getValue() > integerWrapper.getValue()) {
			return 1;
		}
		else if (getValue() < integerWrapper.getValue()) {
			return -1;
		}
		else {
			return 0;
		}
	}

	public int decrement() {
		return --_value;
	}

	public int getValue() {
		return _value;
	}

	public int increment() {
		return ++_value;
	}

	public void setValue(int value) {
		_value = value;
	}

	private int _value;

}