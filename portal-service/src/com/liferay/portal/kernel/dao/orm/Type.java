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

package com.liferay.portal.kernel.dao.orm;

/**
 * @author Brian Wing Shun Chan
 */
public enum Type {

	BIG_DECIMAL, BIG_INTEGER, BINARY, BLOB, BOOLEAN, BYTE, CALENDAR,
	CALENDAR_DATE, CHAR_ARRAY, CHARACTER, CHARACTER_ARRAY, CLASS, CLOB,
	CURRENCY, DATE, DOUBLE, FLOAT, IMAGE, INTEGER, LOCALE, LONG,
	MATERIALIZED_BLOB, MATERIALIZED_CLOB, NUMERIC_BOOLEAN, SERIALIZABLE, SHORT,
	STRING, TEXT, TIME, TIMESTAMP, TIMEZONE, TRUE_FALSE, URL, UUID_BINARY,
	UUID_CHAR, WRAPPER_BINARY, YES_NO;

}