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
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance;

import java.util.Collection;

/**
 * @author Edward Han
 */
public class RuleGroupProcessorUtil {

	public static MDRRuleGroupInstance evaluateRuleGroups(
			ThemeDisplay themeDisplay)
		throws SystemException {

		return getRuleGroupProcessor().evaluateRuleGroups(themeDisplay);
	}

	public static RuleGroupProcessor getRuleGroupProcessor() {
		PortalRuntimePermission.checkGetBeanProperty(
			RuleGroupProcessorUtil.class);

		return _ruleGroupProcessor;
	}

	public static RuleHandler getRuleHandler(String ruleType) {
		return getRuleGroupProcessor().getRuleHandler(ruleType);
	}

	public static Collection<RuleHandler> getRuleHandlers() {
		return getRuleGroupProcessor().getRuleHandlers();
	}

	public static Collection<String> getRuleHandlerTypes() {
		return getRuleGroupProcessor().getRuleHandlerTypes();
	}

	public static void registerRuleHandler(RuleHandler ruleHandler) {
		getRuleGroupProcessor().registerRuleHandler(ruleHandler);
	}

	public static RuleHandler unregisterRuleHandler(String ruleType) {
		return getRuleGroupProcessor().unregisterRuleHandler(ruleType);
	}

	public void setRuleGroupProcessor(RuleGroupProcessor ruleGroupProcessor) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_ruleGroupProcessor = ruleGroupProcessor;
	}

	private static RuleGroupProcessor _ruleGroupProcessor;

}