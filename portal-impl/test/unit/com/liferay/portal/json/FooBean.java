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

import java.util.Collection;
import java.util.HashSet;

/**
 * @author Igor Spasic
 */
public class FooBean {

	public FooBean() {
		_collection = new HashSet<Object>();
		_collection.add("element");
	}

	public Collection<Object> getCollection() {
		return _collection;
	}

	public String getName() {
		return _name;
	}

	public int getValue() {
		return _value;
	}

	public void setCollection(Collection<Object> collection) {
		_collection = collection;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setValue(int value) {
		_value = value;
	}

	private Collection<Object> _collection;
	private String _name = "bar";
	private int _value = 173;

}