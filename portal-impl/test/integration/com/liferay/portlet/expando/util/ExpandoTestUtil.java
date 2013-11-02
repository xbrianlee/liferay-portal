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

package com.liferay.portlet.expando.util;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.Locale;
import java.util.Map;

/**
 * @author Manuel de la Peña
 */
public class ExpandoTestUtil {

	public static ExpandoColumn addColumn(
			ExpandoTable table, String columnName, int type)
		throws Exception {

		return ExpandoColumnLocalServiceUtil.addColumn(
			table.getTableId(), columnName, type);
	}

	public static ExpandoColumn addColumn(
			ExpandoTable table, String columnName, int type,
			Map<Locale, String> defaultData)
		throws Exception {

		return ExpandoColumnLocalServiceUtil.addColumn(
			table.getTableId(), columnName, type, defaultData);
	}

	public static ExpandoTable addTable(long classNameId, String tableName)
		throws Exception {

		return ExpandoTableLocalServiceUtil.addTable(
			TestPropsValues.getCompanyId(), classNameId, tableName);
	}

	public static ExpandoValue addValue(
			ExpandoTable table, ExpandoColumn column, long classPK, Object data)
		throws Exception {

		return ExpandoValueLocalServiceUtil.addValue(
			TestPropsValues.getCompanyId(),
			PortalUtil.getClassName(table.getClassNameId()), table.getName(),
			column.getName(), classPK, data);
	}

	public static ExpandoValue addValue(
			ExpandoTable table, ExpandoColumn column, Map<Locale, String> data,
			Locale defaultLocale)
		throws Exception {

		return ExpandoValueLocalServiceUtil.addValue(
			TestPropsValues.getCompanyId(),
			PortalUtil.getClassName(table.getClassNameId()), table.getName(),
			column.getName(), CounterLocalServiceUtil.increment(), data,
			defaultLocale);
	}

	public static ExpandoValue addValue(
			ExpandoTable table, ExpandoColumn column, Object data)
		throws Exception {

		return addValue(
			table, column, CounterLocalServiceUtil.increment(), data);
	}

	public static ExpandoValue addValue(
			long classNameId, long classPK, Object data)
		throws Exception {

		ExpandoTable table = addTable(
			classNameId, ServiceTestUtil.randomString());
		ExpandoColumn column = addColumn(
			table, ServiceTestUtil.randomString(),
			ExpandoColumnConstants.STRING);

		return addValue(table, column, classPK, data);
	}

}