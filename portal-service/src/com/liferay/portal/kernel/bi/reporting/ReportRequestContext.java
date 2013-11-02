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

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class ReportRequestContext implements Serializable {

	public static final String DATA_SOURCE_BYTE_ARRAY = "dataSource.byteArray";

	public static final String DATA_SOURCE_CHARSET = "dataSource.charset";

	public static final String DATA_SOURCE_COLUMN_NAMES =
		"dataSource.columnNames";

	public static final String JDBC_DRIVER_CLASS = "jdbc.driverClassName";

	public static final String JDBC_PASSWORD = "jdbc.password";

	public static final String JDBC_URL = "jdbc.url";

	public static final String JDBC_USER_NAME = "jdbc.userName";

	public ReportRequestContext(ReportDataSourceType reportDataSourceType) {
		_reportDataSourceType = reportDataSourceType;
	}

	public Serializable getAttribute(String key) {
		return _attributes.get(key);
	}

	public ReportDataSourceType getReportDataSourceType() {
		return _reportDataSourceType;
	}

	public void setAttribute(String key, Serializable value) {
		_attributes.put(key, value);
	}

	private Map<String, Serializable> _attributes =
		new HashMap<String, Serializable>();
	private ReportDataSourceType _reportDataSourceType;

}