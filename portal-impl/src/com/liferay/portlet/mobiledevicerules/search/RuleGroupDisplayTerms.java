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

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Edward Han
 */
public class RuleGroupDisplayTerms extends DisplayTerms {

	public static final String GROUP_ID = "searchGroupId";

	public static final String NAME = "searchName";

	public RuleGroupDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		_groupId = ParamUtil.getLong(portletRequest, GROUP_ID);
		_name = ParamUtil.getString(portletRequest, NAME);
	}

	public long getGroupId() {
		return _groupId;
	}

	public String getName() {
		return _name;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _groupId;
	private String _name;

}