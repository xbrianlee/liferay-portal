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

import java.lang.reflect.Field;

/**
 * @author Shuyang Zhou
 */
public class ReferenceEntry {

	public ReferenceEntry(Field field) {
		this(null, field);
	}

	public ReferenceEntry(Object object, Field field) {
		_object = object;
		_field = field;

		if (!_field.isAccessible()) {
			_field.setAccessible(true);
		}
	}

	public Field getField() {
		return _field;
	}

	public Object getObject() {
		return _object;
	}

	public void setValue(Object value)
		throws IllegalAccessException, IllegalArgumentException {

		_field.set(_object, value);
	}

	@Override
	public String toString() {
		return _object.toString().concat(StringPool.POUND).concat(
			_field.toString());
	}

	private Field _field;
	private Object _object;

}