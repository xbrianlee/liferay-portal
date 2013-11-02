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

import java.io.PrintStream;
import java.io.PrintWriter;

import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Brian Wing Shun Chan
 */
public class SortedProperties extends Properties {

	public SortedProperties() {
		this(null, null);
	}

	public SortedProperties(Comparator<String> comparator) {
		this(comparator, null);
	}

	public SortedProperties(
		Comparator<String> comparator, Properties properties) {

		_comparator = comparator;

		if (comparator != null) {
			_names = new TreeSet<String>(comparator);
		}
		else {
			_names = new TreeSet<String>();
		}

		if (properties != null) {
			for (Map.Entry<Object, Object> entry : properties.entrySet()) {
				String key = (String)entry.getKey();
				String value = (String)entry.getValue();

				setProperty(key, value);
			}
		}
	}

	public SortedProperties(Properties properties) {
		this(null, properties);
	}

	@Override
	public void clear() {
		super.clear();

		_names.clear();
	}

	@Override
	public Set<Map.Entry<Object, Object>> entrySet() {
		Set<Map.Entry<Object, Object>> set =
			new TreeSet<Map.Entry<Object, Object>>(
				new Comparator<Map.Entry<Object, Object>>() {

					@Override
					public int compare(
						Map.Entry<Object, Object> object1,
						Map.Entry<Object, Object> object2) {

						String key1 = String.valueOf(object1.getKey());
						String key2 = String.valueOf(object2.getKey());

						if (_comparator == null) {
							return key1.compareTo(key2);
						}

						return _comparator.compare(key1, key2);
					}

				});

		set.addAll(super.entrySet());

		return set;
	}

	@Override
	public void list(PrintStream out) {
		System.out.println("-- listing properties --");

		Enumeration<String> enu = propertyNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			out.println(name + StringPool.EQUAL + getProperty(name));
		}
	}

	@Override
	public void list(PrintWriter out) {
		System.out.println("-- listing properties --");

		Enumeration<String> enu = propertyNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			out.println(name + StringPool.EQUAL + getProperty(name));
		}
	}

	@Override
	public Enumeration<String> propertyNames() {
		return Collections.enumeration(_names);
	}

	public Object put(String key, String value) {
		if (_names.contains(key)) {
			_names.remove(key);
		}

		_names.add(key);

		return super.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		_names.remove(key);

		return super.remove(key);
	}

	@Override
	public Object setProperty(String key, String value) {
		return put(key, value);
	}

	private Comparator<String> _comparator;
	private Set<String> _names;

}