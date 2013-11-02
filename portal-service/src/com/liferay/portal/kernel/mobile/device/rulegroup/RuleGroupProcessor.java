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

package com.liferay.portal.kernel.mobile.device.rulegroup;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.mobile.device.rulegroup.rule.RuleHandler;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance;

import java.util.Collection;

/**
 * @author Edward Han
 */
public interface RuleGroupProcessor {

	public MDRRuleGroupInstance evaluateRuleGroups(ThemeDisplay themeDisplay)
		throws SystemException;

	public RuleHandler getRuleHandler(String ruleType);

	public Collection<RuleHandler> getRuleHandlers();

	public Collection<String> getRuleHandlerTypes();

	public void registerRuleHandler(RuleHandler ruleHandler);

	public RuleHandler unregisterRuleHandler(String ruleType);

}