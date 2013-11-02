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

package com.liferay.portal.typeconverter;

import java.util.Date;

import jodd.typeconverter.ConvertBean;
import jodd.typeconverter.TypeConverter;

import jodd.util.CsvUtil;

/**
 * @author Raymond Augé
 */
public class DateArrayConverter implements TypeConverter<Date[]> {

	public DateArrayConverter(ConvertBean convertBean) {
		_convertBean = convertBean;
	}

	@Override
	public Date[] convert(Object value) {
		if (value == null) {
			return null;
		}

		Class<?> type = value.getClass();

		if (type.isArray() == false) {
			if (type == String.class) {
				String[] values = CsvUtil.toStringArray(value.toString());

				return convertArray(values);
			}

			return new Date[] {_convertBean.toDate(value)};
		}

		Class<?> componentType = type.getComponentType();

		if (componentType.isPrimitive()) {
			if (type == long[].class) {
				long[] values = (long[])value;

				Date[] results = new Date[values.length];

				for (int i = 0; i < values.length; i++) {
					results[i] = _convertBean.toDate(values[i]);
				}

				return results;
			}
		}

		return convertArray((Object[])value);
	}

	protected Date[] convertArray(Object[] values) {
		Date[] results = new Date[values.length];

		for (int i = 0; i < values.length; i++) {
			results[i] = _convertBean.toDate(values[i]);
		}

		return results;
	}

	private ConvertBean _convertBean;

}