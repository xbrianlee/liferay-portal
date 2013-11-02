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

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * @author Brian Wing Shun Chan
 */
public class OrderedProperties extends Properties {

	public OrderedProperties() {
		super();

		_names = new Vector<String>();
	}

	@Override
	public Enumeration<String> propertyNames() {
		return _names.elements();
	}

	@Override
	public Object put(Object key, Object value) {
		if (_names.contains(key)) {
			_names.remove(key);
		}

		_names.add((String)key);

		return super.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		_names.remove(key);

		return super.remove(key);
	}

	private Vector<String> _names;

}