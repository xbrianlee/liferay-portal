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
public class ShortWrapper
	extends PrimitiveWrapper implements Comparable<ShortWrapper> {

	public static final Class<?> TYPE = Short.TYPE;

	public ShortWrapper() {
		this((short)0);
	}

	public ShortWrapper(short value) {
		_value = value;
	}

	@Override
	public int compareTo(ShortWrapper shortWrapper) {
		if (shortWrapper == null) {
			return 1;
		}

		if (getValue() > shortWrapper.getValue()) {
			return 1;
		}
		else if (getValue() < shortWrapper.getValue()) {
			return -1;
		}
		else {
			return 0;
		}
	}

	public short decrement() {
		return --_value;
	}

	public short getValue() {
		return _value;
	}

	public short increment() {
		return ++_value;
	}

	public void setValue(short value) {
		_value = value;
	}

	private short _value;

}