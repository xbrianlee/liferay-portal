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

package com.liferay.portlet.usersadmin.action;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.struts.AJAXAction;
import com.liferay.portal.util.PortalUtil;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Gavin Wan
 */
public class GetUsersCountAction extends AJAXAction {

	@Override
	public String getText(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		long companyId = PortalUtil.getCompanyId(request);

		String className = ParamUtil.getString(request, "className");
		long[] ids = StringUtil.split(ParamUtil.getString(request, "ids"), 0L);
		int status = ParamUtil.getInteger(request, "status");

		int count = 0;

		if (className.equals(Organization.class.getName())) {
			count = getOrganizationUsersCount(companyId, ids, status);
		}
		else if (className.equals(UserGroup.class.getName())) {
			count = getUserGroupUsersCount(companyId, ids, status);
		}

		return String.valueOf(count);
	}

	protected int getOrganizationUsersCount(
			long companyId, long[] organizationIds, int status)
		throws Exception {

		int count = 0;

		for (long organizationId : organizationIds) {
			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();

			params.put("usersOrgs", organizationId);

			count+= UserLocalServiceUtil.searchCount(
				companyId, null, status, params);
		}

		return count;
	}

	protected int getUserGroupUsersCount(
			long companyId, long[] userGroupIds, int status)
		throws Exception {

		int count = 0;

		for (long userGroupId : userGroupIds) {
			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();

			params.put("usersUserGroups", userGroupId);

			count+= UserLocalServiceUtil.searchCount(
				companyId, null, status, params);
		}

		return count;
	}

}