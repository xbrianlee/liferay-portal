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
public class BooleanWrapper
	extends PrimitiveWrapper implements Comparable<BooleanWrapper> {

	public static final Class<?> TYPE = Boolean.TYPE;

	public BooleanWrapper() {
		this(false);
	}

	public BooleanWrapper(boolean value) {
		_value = value;
	}

	@Override
	public int compareTo(BooleanWrapper booleanWrapper) {
		if (booleanWrapper == null) {
			return 1;
		}

		if (getValue() && !booleanWrapper.getValue()) {
			return 1;
		}
		else if (!getValue() && booleanWrapper.getValue()) {
			return -1;
		}
		else {
			return 0;
		}
	}

	public boolean getValue() {
		return _value;
	}

	public void setValue(boolean value) {
		_value = value;
	}

	private boolean _value;

}