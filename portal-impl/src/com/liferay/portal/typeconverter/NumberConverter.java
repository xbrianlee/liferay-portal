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

import java.math.BigDecimal;

import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;

/**
 * @author Raymond Augé
 */
public class NumberConverter implements TypeConverter<Number> {

	@Override
	public Number convert(Object value) {
		if (value == null) {
			return null;
		}

		if (value.getClass() == Number.class) {
			return (Number)value;
		}

		try {
			String valueString = value.toString();

			valueString = valueString.trim();

			return new BigDecimal(valueString);
		}
		catch (NumberFormatException nfe) {
			throw new TypeConversionException(value, nfe);
		}
	}

}