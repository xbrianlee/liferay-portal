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

package com.liferay.portlet.mobiledevicerules.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.mobiledevicerules.model.MDRRule;
import com.liferay.portlet.mobiledevicerules.service.base.MDRRuleServiceBaseImpl;
import com.liferay.portlet.mobiledevicerules.service.permission.MDRRuleGroupPermissionUtil;

import java.util.Locale;
import java.util.Map;

/**
 * @author Edward C. Han
 */
public class MDRRuleServiceImpl extends MDRRuleServiceBaseImpl {

	@Override
	public MDRRule addRule(
			long ruleGroupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			String typeSettings, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MDRRuleGroupPermissionUtil.check(
			getPermissionChecker(), ruleGroupId, ActionKeys.UPDATE);

		return mdrRuleLocalService.addRule(
			ruleGroupId, nameMap, descriptionMap, type, typeSettings,
			serviceContext);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public MDRRule addRule(
			long ruleGroupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			UnicodeProperties typeSettings, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MDRRuleGroupPermissionUtil.check(
			getPermissionChecker(), ruleGroupId, ActionKeys.UPDATE);

		return mdrRuleLocalService.addRule(
			ruleGroupId, nameMap, descriptionMap, type, typeSettings,
			serviceContext);
	}

	@Override
	public void deleteRule(long ruleId)
		throws PortalException, SystemException {

		MDRRule rule = mdrRulePersistence.findByPrimaryKey(ruleId);

		MDRRuleGroupPermissionUtil.check(
			getPermissionChecker(), rule.getRuleGroupId(), ActionKeys.UPDATE);

		mdrRuleLocalService.deleteRule(rule);
	}

	@Override
	public MDRRule fetchRule(long ruleId)
		throws PortalException, SystemException {

		MDRRule rule = mdrRuleLocalService.fetchRule(ruleId);

		if (rule != null) {
			MDRRuleGroupPermissionUtil.check(
				getPermissionChecker(), rule.getRuleGroupId(), ActionKeys.VIEW);
		}

		return rule;
	}

	@Override
	public MDRRule getRule(long ruleId)
		throws PortalException, SystemException {

		MDRRule rule = mdrRulePersistence.findByPrimaryKey(ruleId);

		MDRRuleGroupPermissionUtil.check(
			getPermissionChecker(), rule.getRuleGroupId(), ActionKeys.VIEW);

		return rule;
	}

	@Override
	public MDRRule updateRule(
			long ruleId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			String typeSettings, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MDRRule rule = mdrRulePersistence.findByPrimaryKey(ruleId);

		MDRRuleGroupPermissionUtil.check(
			getPermissionChecker(), rule.getRuleGroupId(), ActionKeys.UPDATE);

		return mdrRuleLocalService.updateRule(
			ruleId, nameMap, descriptionMap, type, typeSettings,
			serviceContext);
	}

	@Override
	public MDRRule updateRule(
			long ruleId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			UnicodeProperties typeSettingsProperties,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		MDRRule rule = mdrRulePersistence.findByPrimaryKey(ruleId);

		MDRRuleGroupPermissionUtil.check(
			getPermissionChecker(), rule.getRuleGroupId(), ActionKeys.UPDATE);

		return mdrRuleLocalService.updateRule(
			ruleId, nameMap, descriptionMap, type, typeSettingsProperties,
			serviceContext);
	}

}