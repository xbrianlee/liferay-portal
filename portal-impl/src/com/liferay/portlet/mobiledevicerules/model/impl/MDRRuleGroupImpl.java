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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.mobiledevicerules.model.MDRRule;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleLocalServiceUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author Edward C. Han
 */
public class MDRRuleGroupImpl extends MDRRuleGroupBaseImpl {

	public MDRRuleGroupImpl() {
	}

	@Override
	public List<MDRRule> getRules() throws SystemException {
		if (getRuleGroupId() > 0) {
			return MDRRuleLocalServiceUtil.getRules(getRuleGroupId());
		}
		else {
			return Collections.emptyList();
		}
	}

}