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
public class DoubleWrapper
	extends PrimitiveWrapper implements Comparable<DoubleWrapper> {

	public static final Class<?> TYPE = Double.TYPE;

	public DoubleWrapper() {
		this(0D);
	}

	public DoubleWrapper(double value) {
		_value = value;
	}

	@Override
	public int compareTo(DoubleWrapper doubleWrapper) {
		if (doubleWrapper == null) {
			return 1;
		}

		if (getValue() > doubleWrapper.getValue()) {
			return 1;
		}
		else if (getValue() < doubleWrapper.getValue()) {
			return -1;
		}
		else {
			return 0;
		}
	}

	public double decrement() {
		return --_value;
	}

	public double getValue() {
		return _value;
	}

	public double increment() {
		return ++_value;
	}

	public void setValue(double value) {
		_value = value;
	}

	private double _value;

}