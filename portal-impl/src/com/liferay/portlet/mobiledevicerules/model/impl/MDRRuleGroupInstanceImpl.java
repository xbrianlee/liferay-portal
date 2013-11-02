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

package com.liferay.portlet.mobiledevicerules.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.mobiledevicerules.model.MDRAction;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;
import com.liferay.portlet.mobiledevicerules.service.MDRActionLocalServiceUtil;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupLocalServiceUtil;

import java.util.List;

/**
 * @author Edward C. Han
 */
public class MDRRuleGroupInstanceImpl extends MDRRuleGroupInstanceBaseImpl {

	public MDRRuleGroupInstanceImpl() {
	}

	@Override
	public List<MDRAction> getActions() throws SystemException {
		return MDRActionLocalServiceUtil.getActions(getRuleGroupInstanceId());
	}

	@Override
	public MDRRuleGroup getRuleGroup() throws PortalException, SystemException {
		return MDRRuleGroupLocalServiceUtil.getRuleGroup(getRuleGroupId());
	}

}