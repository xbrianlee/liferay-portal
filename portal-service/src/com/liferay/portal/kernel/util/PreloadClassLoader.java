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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class PreloadClassLoader extends ClassLoader {

	public PreloadClassLoader(
		ClassLoader classLoader, Map<String, Class<?>> classes) {

		super(classLoader);

		_classes.putAll(classes);
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Class<?> clazz = _classes.get(name);

		if (clazz != null) {
			return clazz;
		}

		return super.loadClass(name);
	}

	private Map<String, Class<?>> _classes = new HashMap<String, Class<?>>();

}