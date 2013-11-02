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

package com.liferay.portal.kernel.bi.reporting;

/**
 * @author Gavin Wan
 */
public enum ReportDataSourceType {

	CSV("csv"), EMPTY("empty"), JDBC("jdbc"), PORTAL("portal"), XLS("xls"),
	XML("xml");

	public static ReportDataSourceType parse(String value) {
		if (CSV.getValue().equals(value)) {
			return CSV;
		}
		else if (EMPTY.getValue().equals(value)) {
			return EMPTY;
		}
		else if (JDBC.getValue().equals(value)) {
			return JDBC;
		}
		else if (PORTAL.getValue().equals(value)) {
			return PORTAL;
		}
		else if (XLS.getValue().equals(value)) {
			return XLS;
		}
		else if (XML.getValue().equals(value)) {
			return XML;
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private ReportDataSourceType(String value) {
		_value = value;
	}

	private String _value;

}