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

package com.liferay.portal.json;

import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Miguel Pastor
 */
public class FooBean5 {

	public double getDoubleValue() {
		return _doubleValue;
	}

	public int getIntegerValue() {
		return _integerValue;
	}

	public long getLongValue() {
		return _longValue;
	}

	public void setDoubleValue(double doubleValue) {
		_doubleValue = doubleValue;
	}

	public void setIntegerValue(int integerValue) {
		_integerValue = integerValue;
	}

	public void setLongValue(long longValue) {
		_longValue = longValue;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{doubleValue=");
		sb.append(_doubleValue);
		sb.append(", integerValue=");
		sb.append(_integerValue);
		sb.append(", longValue=");
		sb.append(_longValue);
		sb.append("}");

		return sb.toString();
	}

	private double _doubleValue;
	private int _integerValue;
	private long _longValue;

}