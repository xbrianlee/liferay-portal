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
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;
import com.liferay.portlet.mobiledevicerules.service.base.MDRRuleGroupServiceBaseImpl;
import com.liferay.portlet.mobiledevicerules.service.permission.MDRPermissionUtil;
import com.liferay.portlet.mobiledevicerules.service.permission.MDRRuleGroupPermissionUtil;

import java.util.Locale;
import java.util.Map;

/**
 * @author Edward C. Han
 */
public class MDRRuleGroupServiceImpl extends MDRRuleGroupServiceBaseImpl {

	@Override
	public MDRRuleGroup addRuleGroup(
			long groupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MDRPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_RULE_GROUP);

		return mdrRuleGroupLocalService.addRuleGroup(
			groupId, nameMap, descriptionMap, serviceContext);
	}

	@Override
	public MDRRuleGroup copyRuleGroup(
			long ruleGroupId, long groupId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		PermissionChecker permissionChecker = getPermissionChecker();

		MDRRuleGroup ruleGroup = getRuleGroup(ruleGroupId);

		MDRRuleGroupPermissionUtil.check(
			permissionChecker, ruleGroup, ActionKeys.VIEW);

		MDRPermissionUtil.check(
			permissionChecker, groupId, ActionKeys.ADD_RULE_GROUP);

		return mdrRuleGroupLocalService.copyRuleGroup(
			ruleGroup, groupId, serviceContext);
	}

	@Override
	public void deleteRuleGroup(long ruleGroupId)
		throws PortalException, SystemException {

		MDRRuleGroup ruleGroup = mdrRuleGroupPersistence.findByPrimaryKey(
			ruleGroupId);

		MDRRuleGroupPermissionUtil.check(
			getPermissionChecker(), ruleGroup, ActionKeys.DELETE);

		mdrRuleGroupLocalService.deleteRuleGroup(ruleGroup);
	}

	@Override
	public MDRRuleGroup fetchRuleGroup(long ruleGroupId)
		throws PortalException, SystemException {

		MDRRuleGroup ruleGroup = mdrRuleGroupPersistence.fetchByPrimaryKey(
			ruleGroupId);

		if (ruleGroup != null) {
			MDRRuleGroupPermissionUtil.check(
				getPermissionChecker(), ruleGroup, ActionKeys.VIEW);
		}

		return ruleGroup;
	}

	@Override
	public MDRRuleGroup getRuleGroup(long ruleGroupId)
		throws PortalException, SystemException {

		MDRRuleGroup ruleGroup = mdrRuleGroupPersistence.findByPrimaryKey(
			ruleGroupId);

		MDRRuleGroupPermissionUtil.check(
			getPermissionChecker(), ruleGroup, ActionKeys.VIEW);

		return ruleGroup;
	}

	@Override
	public MDRRuleGroup updateRuleGroup(
			long ruleGroupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MDRRuleGroup ruleGroup = mdrRuleGroupPersistence.findByPrimaryKey(
			ruleGroupId);

		MDRRuleGroupPermissionUtil.check(
			getPermissionChecker(), ruleGroup, ActionKeys.UPDATE);

		return mdrRuleGroupLocalService.updateRuleGroup(
			ruleGroupId, nameMap, descriptionMap, serviceContext);
	}

}