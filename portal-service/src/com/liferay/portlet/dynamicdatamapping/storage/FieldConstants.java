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

package com.liferay.portlet.dynamicdatamapping.storage;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * @author Marcellus Tavares
 * @author Eduardo Lundgren
 */
public class FieldConstants {

	public static final String BOOLEAN = "boolean";

	public static final String DATA_TYPE = "dataType";

	public static final String DATE = "date";

	public static final String DOCUMENT_LIBRARY = "document-library";

	public static final String DOUBLE = "double";

	public static final String EDITABLE = "editable";

	public static final String FLOAT = "float";

	public static final String HTML = "html";

	public static final String IMAGE = "image";

	public static final String INTEGER = "integer";

	public static final String LABEL = "label";

	public static final String LONG = "long";

	public static final String NAME = "name";

	public static final String NUMBER = "number";

	public static final String PREDEFINED_VALUE = "predefinedValue";

	public static final String PRIVATE = "private";

	public static final String REQUIRED = "required";

	public static final String SHORT = "short";

	public static final String SHOW = "showLabel";

	public static final String SORTABLE = "sortable";

	public static final String STRING = "string";

	public static final String TYPE = "type";

	public static final String VALUE = "value";

	public static final Serializable getSerializable(
		String type, List<Serializable> values) {

		if (type.equals(FieldConstants.BOOLEAN)) {
			return values.toArray(new Boolean[values.size()]);
		}
		else if (type.equals(FieldConstants.DATE)) {
			return values.toArray(new Date[values.size()]);
		}
		else if (type.equals(FieldConstants.DOUBLE)) {
			return values.toArray(new Double[values.size()]);
		}
		else if (type.equals(FieldConstants.FLOAT)) {
			return values.toArray(new Float[values.size()]);
		}
		else if (type.equals(FieldConstants.INTEGER)) {
			return values.toArray(new Integer[values.size()]);
		}
		else if (type.equals(FieldConstants.LONG)) {
			return values.toArray(new Long[values.size()]);
		}
		else if (type.equals(FieldConstants.NUMBER)) {
			return values.toArray(new Number[values.size()]);
		}
		else if (type.equals(FieldConstants.SHORT)) {
			return values.toArray(new Short[values.size()]);
		}
		else {
			return values.toArray(new String[values.size()]);
		}
	}

	public static final Serializable getSerializable(
		String type, String value) {

		if (isNumericType(type) && Validator.isNull(value)) {
			return null;
		}

		if (type.equals(BOOLEAN)) {
			return GetterUtil.getBoolean(value);
		}
		else if (type.equals(DATE) && Validator.isNotNull(value)) {
			return new Date(GetterUtil.getLong(value));
		}
		else if (type.equals(DOUBLE)) {
			return GetterUtil.getDouble(value);
		}
		else if (type.equals(FLOAT)) {
			return GetterUtil.getFloat(value);
		}
		else if (type.equals(INTEGER)) {
			return GetterUtil.getInteger(value);
		}
		else if (type.equals(LONG)) {
			return GetterUtil.getLong(value);
		}
		else if (type.equals(NUMBER)) {
			return GetterUtil.getNumber(value);
		}
		else if (type.equals(SHORT)) {
			return GetterUtil.getShort(value);
		}
		else {
			return value;
		}
	}

	public static final boolean isNumericType(String type) {
		if (type.equals(DOUBLE) || type.equals(FLOAT) || type.equals(INTEGER) ||
			type.equals(LONG) || type.equals(NUMBER) || type.equals(SHORT)) {

			return true;
		}

		return false;
	}

}