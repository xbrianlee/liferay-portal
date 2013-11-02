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

package com.liferay.portlet.dynamicdatalists.util;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSetConstants;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalServiceUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Daniel Kocsis
 */
public class DDLTestUtil {

	public static DDLRecordSet addRecordSet(long groupId, long structureId)
		throws Exception {

		String name = ServiceTestUtil.randomString();
		Locale defaultLocale = LocaleUtil.getSiteDefault();

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			groupId);

		return addRecordSet(
			groupId, structureId, name, defaultLocale, serviceContext);
	}

	public static DDLRecordSet addRecordSet(
			long groupId, long structureId, String name, Locale defaultLocale,
			ServiceContext serviceContext)
		throws Exception {

		long userId = TestPropsValues.getUserId();
		String recordSetKey = null;

		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		nameMap.put(defaultLocale, name);

		Map<Locale, String> descriptionMap = null;
		int minDisplayRows = DDLRecordSetConstants.MIN_DISPLAY_ROWS_DEFAULT;
		int scope = DDLRecordSetConstants.SCOPE_DYNAMIC_DATA_LISTS;

		return DDLRecordSetLocalServiceUtil.addRecordSet(
			userId, groupId, structureId, recordSetKey, nameMap, descriptionMap,
			minDisplayRows, scope, serviceContext);
	}

}