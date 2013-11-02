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
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class EscapableObject<T> implements Serializable {

	public EscapableObject(T originalValue) {
		this(originalValue, true);
	}

	public EscapableObject(T originalValue, boolean escape) {
		_originalValue = originalValue;
		_escape = escape;
	}

	public String getEscapedValue() {
		if (_escapedValue == null) {
			if (_escape) {
				_escapedValue = escape(_originalValue);
			}
			else {
				_escapedValue = String.valueOf(_originalValue);
			}
		}

		return _escapedValue;
	}

	public T getOriginalValue() {
		return _originalValue;
	}

	@Override
	public String toString() {
		return _originalValue.toString();
	}

	protected String escape(T t) {
		return String.valueOf(t);
	}

	private boolean _escape;
	private String _escapedValue;
	private T _originalValue;

}