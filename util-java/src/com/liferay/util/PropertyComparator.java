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

package com.liferay.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.InvocationTargetException;

import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author Patrick Brady
 * @author Raymond Augé
 */
public class PropertyComparator implements Comparator<Object> {

	public PropertyComparator(String propertyName) {
		this(new String[] {propertyName}, true, false);
	}

	public PropertyComparator(
		String propertyName, boolean ascending, boolean caseSensitive) {

		this(new String[] {propertyName}, ascending, caseSensitive);
	}

	public PropertyComparator(String[] propertyNames) {
		this(propertyNames, true, false);
	}

	public PropertyComparator(
		String[] propertyNames, boolean ascending, boolean caseSensitive) {

		_propertyNames = propertyNames;
		_ascending = ascending;
		_caseSensitive = caseSensitive;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		try {
			for (String propertyName : _propertyNames) {
				Object property1 = PropertyUtils.getProperty(
					obj1, propertyName);
				Object property2 = PropertyUtils.getProperty(
					obj2, propertyName);

				if (!_ascending) {
					Object temp = property1;

					property1 = property2;
					property2 = temp;
				}

				if (property1 instanceof String) {
					int value = 0;

					if (_caseSensitive) {
						value = property1.toString().compareTo(
							property2.toString());
					}
					else {
						value = property1.toString().compareToIgnoreCase(
							property2.toString());
					}

					if (value != 0) {
						return value;
					}
				}

				if (property1 instanceof Comparable<?>) {
					int value = ((Comparable<Object>)property1).compareTo(
						property2);

					if (value != 0) {
						return value;
					}
				}
			}
		}
		catch (IllegalAccessException iae) {
			_log.error(iae.getMessage());
		}
		catch (InvocationTargetException ite) {
			_log.error(ite.getMessage());
		}
		catch (NoSuchMethodException nsme) {
			_log.error(nsme.getMessage());
		}

		return -1;
	}

	private static Log _log = LogFactoryUtil.getLog(PropertyComparator.class);

	private boolean _ascending;
	private boolean _caseSensitive;
	private String[] _propertyNames;

}