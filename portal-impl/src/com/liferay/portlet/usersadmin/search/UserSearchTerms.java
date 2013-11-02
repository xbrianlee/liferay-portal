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

package com.liferay.portlet.usersadmin.search;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class UserSearchTerms extends UserDisplayTerms {

	public UserSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		emailAddress = DAOParamUtil.getString(portletRequest, EMAIL_ADDRESS);
		firstName = DAOParamUtil.getString(portletRequest, FIRST_NAME);
		lastName = DAOParamUtil.getString(portletRequest, LAST_NAME);
		middleName = DAOParamUtil.getString(portletRequest, MIDDLE_NAME);
		organizationId = ParamUtil.getLong(portletRequest, ORGANIZATION_ID);
		roleId = ParamUtil.getLong(portletRequest, ROLE_ID);
		screenName = DAOParamUtil.getString(portletRequest, SCREEN_NAME);
		status = ParamUtil.getInteger(
			portletRequest, STATUS, WorkflowConstants.STATUS_APPROVED);
		userGroupId = ParamUtil.getLong(portletRequest, USER_GROUP_ID);
	}

}