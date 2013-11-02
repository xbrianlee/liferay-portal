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

import jodd.typeconverter.ConvertBean;
import jodd.typeconverter.TypeConverter;

import jodd.util.CsvUtil;

/**
 * @author Raymond Augé
 */
public class NumberArrayConverter implements TypeConverter<Number[]> {

	public NumberArrayConverter(ConvertBean convertBean) {
		_convertBean = convertBean;
	}

	@Override
	public Number[] convert(Object value) {
		if (value == null) {
			return null;
		}

		Class<?> type = value.getClass();

		if (type.isArray() == false) {
			if (type == String.class) {
				String[] values = CsvUtil.toStringArray(value.toString());

				return convertArray(values);
			}

			return new Number[] {_convertBean.toBigDecimal(value)};
		}

		Class<?> componentType = type.getComponentType();

		if (componentType.isPrimitive()) {
			if (type == boolean[].class) {
				boolean[] values = (boolean[])value;
				Number[] results = new Number[values.length];

				for (int i = 0; i < values.length; i++) {
					results[i] = (values[i] == true ? 1 : 0);
				}

				return results;
			}
			else if (type == byte[].class) {
				byte[] values = (byte[])value;
				Number[] results = new Number[values.length];

				for (int i = 0; i < values.length; i++) {
					results[i] = values[i];
				}

				return results;
			}
			else if (type == double[].class) {
				double[] values = (double[])value;
				Number[] results = new Number[values.length];

				for (int i = 0; i < values.length; i++) {
					results[i] = values[i];
				}

				return results;
			}
			else if (type == float[].class) {
				float[] values = (float[])value;
				Number[] results = new Number[values.length];

				for (int i = 0; i < values.length; i++) {
					results[i] = values[i];
				}

				return results;
			}
			else if (type == int[].class) {
				int[] values = (int[])value;
				Number[] results = new Number[values.length];

				for (int i = 0; i < values.length; i++) {
					results[i] = values[i];
				}

				return results;
			}
			else if (type == long[].class) {
				long[] values = (long[])value;
				Number[] results = new Number[values.length];

				for (int i = 0; i < values.length; i++) {
					results[i] = values[i];
				}

				return results;
			}
			else if (type == short[].class) {
				short[] values = (short[])value;
				Number[] results = new Number[values.length];

				for (int i = 0; i < values.length; i++) {
					results[i] = values[i];
				}

				return results;
			}
		}

		return convertArray((Object[])value);
	}

	protected Number[] convertArray(Object[] values) {
		Number[] results = new Number[values.length];

		for (int i = 0; i < values.length; i++) {
			results[i] = _convertBean.toBigDecimal(values[i]);
		}

		return results;
	}

	protected ConvertBean _convertBean;

}