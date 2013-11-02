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

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;

import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eduardo Lundgren
 * @author Marcellus Tavares
 */
public class DDLUtil {

	public static DDL getDDL() {
		PortalRuntimePermission.checkGetBeanProperty(DDLUtil.class);

		return _ddl;
	}

	public static JSONObject getRecordJSONObject(DDLRecord record)
		throws Exception {

		return getDDL().getRecordJSONObject(record);
	}

	public static JSONObject getRecordJSONObject(
			DDLRecord record, boolean latestRecordVersion)
		throws Exception {

		return getDDL().getRecordJSONObject(record, latestRecordVersion);
	}

	public static List<DDLRecord> getRecords(Hits hits) throws Exception {
		return getDDL().getRecords(hits);
	}

	public static JSONArray getRecordSetJSONArray(DDLRecordSet recordSet)
		throws Exception {

		return getDDL().getRecordSetJSONArray(recordSet);
	}

	public static JSONArray getRecordsJSONArray(List<DDLRecord> records)
		throws Exception {

		return getDDL().getRecordsJSONArray(records);
	}

	public static JSONArray getRecordsJSONArray(
			List<DDLRecord> records, boolean latestRecordVersion)
		throws Exception {

		return getDDL().getRecordsJSONArray(records, latestRecordVersion);
	}

	public static String getTemplateContent(
			long ddmTemplateId, DDLRecordSet recordSet,
			ThemeDisplay themeDisplay, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		return getDDL().getTemplateContent(
			ddmTemplateId, recordSet, themeDisplay, renderRequest,
			renderResponse);
	}

	public static boolean isEditable(
			HttpServletRequest request, String portletId, long groupId)
		throws Exception {

		return getDDL().isEditable(request, portletId, groupId);
	}

	public static boolean isEditable(
			PortletPreferences preferences, String portletId, long groupId)
		throws Exception {

		return getDDL().isEditable(preferences, portletId, groupId);
	}

	public static DDLRecord updateRecord(
			long recordId, long recordSetId, boolean mergeFields,
			boolean checkPermission, ServiceContext serviceContext)
		throws Exception {

		return getDDL().updateRecord(
			recordId, recordSetId, mergeFields, checkPermission,
			serviceContext);
	}

	public static DDLRecord updateRecord(
			long recordId, long recordSetId, boolean mergeFields,
			ServiceContext serviceContext)
		throws Exception {

		return getDDL().updateRecord(
			recordId, recordSetId, mergeFields, serviceContext);
	}

	public void setDDL(DDL ddl) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_ddl = ddl;
	}

	private static DDL _ddl;

}