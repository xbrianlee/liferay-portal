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

package com.liferay.portal.javadoc;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.ClassLoaderUtil;

import java.util.Arrays;

/**
 * @author Igor Spasic
 */
public class JavadocUtil {

	public static Class<?> loadClass(ClassLoader classLoader, String className)
		throws ClassNotFoundException {

		className = _getLoadableClassName(className);

		if ((className.indexOf('.') == -1) || (className.indexOf('[') == -1)) {
			int primitiveIndex = _getPrimitiveIndex(className);

			if (primitiveIndex >= 0) {
				return _PRIMITIVE_TYPES[primitiveIndex];
			}
		}

		if (classLoader != null) {
			try {
				return classLoader.loadClass(className);
			}
			catch (ClassNotFoundException cnfe) {
			}
		}

		ClassLoader contextClassLoader =
			ClassLoaderUtil.getContextClassLoader();

		if (classLoader != contextClassLoader) {
			try {
				return contextClassLoader.loadClass(className);
			}
			catch (ClassNotFoundException cnfe) {
			}
		}

		return Class.forName(className);
	}

	private static String _getLoadableClassName(String className) {
		int bracketCount = StringUtil.count(className, StringPool.OPEN_BRACKET);

		if (bracketCount == 0) {
			return className;
		}

		StringBuilder sb = new StringBuilder(bracketCount);

		for (int i = 0; i < bracketCount; i++) {
			sb.append('[');
		}

		int bracketIndex = className.indexOf('[');

		className = className.substring(0, bracketIndex);

		int primitiveIndex = _getPrimitiveIndex(className);

		if (primitiveIndex >= 0) {
			className = String.valueOf(
				_PRIMITIVE_BYTECODE_NAME[primitiveIndex]);

			return sb.toString() + className;
		}
		else {
			return sb.toString() + 'L' + className + ';';
		}
	}

	private static int _getPrimitiveIndex(String className) {
		if (className.indexOf('.') != -1) {
			return -1;
		}

		return Arrays.binarySearch(_PRIMITIVE_TYPE_NAMES, className);
	}

	private static final char[] _PRIMITIVE_BYTECODE_NAME = {
		'Z', 'B', 'C', 'D', 'F', 'I', 'J', 'S'
	};

	private static final String[] _PRIMITIVE_TYPE_NAMES = {
		"boolean", "byte", "char", "double", "float", "int", "long", "short",
	};

	private static final Class<?>[] _PRIMITIVE_TYPES = new Class[] {
		boolean.class, byte.class, char.class, double.class, float.class,
		int.class, long.class, short.class,
	};

}