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

package com.liferay.portal.service;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.PortletPreferencesIds;

import javax.portlet.PortletPreferences;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class ServiceContextUtil {

	public static Object deserialize(JSONObject jsonObject) {
		ServiceContext serviceContext = new ServiceContext();

		// Theme display

		serviceContext.setCompanyId(jsonObject.getLong("companyId"));
		serviceContext.setLayoutFullURL(jsonObject.getString("layoutFullURL"));
		serviceContext.setLayoutURL(jsonObject.getString("layoutURL"));
		serviceContext.setPathMain(jsonObject.getString("pathMain"));
		serviceContext.setPlid(jsonObject.getLong("plid"));
		serviceContext.setPortalURL(jsonObject.getString("portalURL"));
		serviceContext.setScopeGroupId(jsonObject.getLong("scopeGroupId"));
		serviceContext.setUserDisplayURL(
			jsonObject.getString("userDisplayURL"));
		serviceContext.setUserId(jsonObject.getLong("userId"));

		// Permissions

		String[] groupPermissions = StringUtil.split(
			jsonObject.getString("groupPermissions"));
		String[] guestPermissions = StringUtil.split(
			jsonObject.getString("guestPermissions"));

		serviceContext.setAddGroupPermissions(
			jsonObject.getBoolean("addGroupPermissions"));
		serviceContext.setAddGuestPermissions(
			jsonObject.getBoolean("addGuestPermissions"));
		serviceContext.setGroupPermissions(groupPermissions);
		serviceContext.setGuestPermissions(guestPermissions);

		// Asset

		long[] assetCategoryIds = StringUtil.split(
			jsonObject.getString("assetCategoryIds"), 0L);
		String[] assetTagNames = StringUtil.split(
			jsonObject.getString("assetTagNames"));

		serviceContext.setAssetCategoryIds(assetCategoryIds);
		serviceContext.setAssetTagNames(assetTagNames);

		// Workflow

		serviceContext.setWorkflowAction(jsonObject.getInt("workflowAction"));

		return serviceContext;
	}

	public static PortletPreferences getPortletPreferences(
			ServiceContext serviceContext)
		throws SystemException {

		if (serviceContext == null) {
			return null;
		}

		PortletPreferencesIds portletPreferencesIds =
			serviceContext.getPortletPreferencesIds();

		if (portletPreferencesIds == null) {
			return null;
		}
		else {
			return PortletPreferencesLocalServiceUtil.getPreferences(
				portletPreferencesIds.getCompanyId(),
				portletPreferencesIds.getOwnerId(),
				portletPreferencesIds.getOwnerType(),
				portletPreferencesIds.getPlid(),
				portletPreferencesIds.getPortletId());
		}
	}

}