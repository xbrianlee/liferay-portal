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

package com.liferay.portlet.mobiledevicerules.search;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Edward Han
 */
public class RuleGroupSearchTerms extends RuleGroupDisplayTerms {

	public RuleGroupSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		setGroupId(DAOParamUtil.getLong(portletRequest, GROUP_ID));
		setName(DAOParamUtil.getString(portletRequest, NAME));
	}

}