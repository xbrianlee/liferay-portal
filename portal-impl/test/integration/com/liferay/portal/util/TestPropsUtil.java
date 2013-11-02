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

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.ListUtil;

import java.io.InputStream;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public class TestPropsUtil {

	public static String get(String key) {
		return _instance._get(key);
	}

	public static Properties getProperties() {
		return _instance._props;
	}

	public static void printProperties() {
		_instance._printProperties(true);
	}

	public static void set(String key, String value) {
		_instance._set(key, value);
	}

	private TestPropsUtil() {
		try {
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();

			InputStream is = classLoader.getResourceAsStream(
				"test-portal-impl.properties");

			_props.load(is);

			is = classLoader.getResourceAsStream(
				"test-portal-impl-ext.properties");

			if (is != null) {
				_props.load(is);
			}

			_printProperties(false);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String _get(String key) {
		return _props.getProperty(key);
	}

	private void _printProperties(boolean update) {
		List<String> keys = Collections.list(
			(Enumeration<String>)_props.propertyNames());

		keys = ListUtil.sort(keys);

		if (update) {
			System.out.println("-- updated properties --");
		}
		else {
			System.out.println("-- listing properties --");
		}

		for (String key : keys) {
			System.out.println(key + "=" + _props.getProperty(key));
		}

		System.out.println("");
	}

	private void _set(String key, String value) {
		_props.setProperty(key, value);
	}

	private static TestPropsUtil _instance = new TestPropsUtil();

	private Properties _props = new Properties();

}