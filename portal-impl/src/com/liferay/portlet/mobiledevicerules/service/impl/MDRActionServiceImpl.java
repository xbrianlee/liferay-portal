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
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.mobiledevicerules.model.MDRAction;
import com.liferay.portlet.mobiledevicerules.service.base.MDRActionServiceBaseImpl;
import com.liferay.portlet.mobiledevicerules.service.permission.MDRRuleGroupInstancePermissionUtil;

import java.util.Locale;
import java.util.Map;

/**
 * @author Edward C. Han
 */
public class MDRActionServiceImpl extends MDRActionServiceBaseImpl {

	@Override
	public MDRAction addAction(
			long ruleGroupInstanceId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			String typeSettings, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MDRRuleGroupInstancePermissionUtil.check(
			getPermissionChecker(), ruleGroupInstanceId, ActionKeys.UPDATE);

		return mdrActionLocalService.addAction(
			ruleGroupInstanceId, nameMap, descriptionMap, type, typeSettings,
			serviceContext);
	}

	@Override
	public MDRAction addAction(
			long ruleGroupInstanceId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			UnicodeProperties typeSettingsProperties,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		MDRRuleGroupInstancePermissionUtil.check(
			getPermissionChecker(), ruleGroupInstanceId, ActionKeys.UPDATE);

		return mdrActionLocalService.addAction(
			ruleGroupInstanceId, nameMap, descriptionMap, type,
			typeSettingsProperties, serviceContext);
	}

	@Override
	public void deleteAction(long actionId)
		throws PortalException, SystemException {

		MDRAction action = mdrActionPersistence.findByPrimaryKey(actionId);

		MDRRuleGroupInstancePermissionUtil.check(
			getPermissionChecker(), action.getRuleGroupInstanceId(),
			ActionKeys.UPDATE);

		mdrActionLocalService.deleteAction(action);
	}

	@Override
	public MDRAction fetchAction(long actionId)
		throws PortalException, SystemException {

		MDRAction action = mdrActionLocalService.fetchAction(actionId);

		if (action != null) {
			MDRRuleGroupInstancePermissionUtil.check(
				getPermissionChecker(), action.getRuleGroupInstanceId(),
				ActionKeys.VIEW);
		}

		return action;
	}

	@Override
	public MDRAction getAction(long actionId)
		throws PortalException, SystemException {

		MDRAction action = mdrActionPersistence.findByPrimaryKey(actionId);

		MDRRuleGroupInstancePermissionUtil.check(
			getPermissionChecker(), action.getRuleGroupInstanceId(),
			ActionKeys.VIEW);

		return action;
	}

	@Override
	public MDRAction updateAction(
			long actionId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			String typeSettings, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MDRAction action = mdrActionPersistence.findByPrimaryKey(actionId);

		MDRRuleGroupInstancePermissionUtil.check(
			getPermissionChecker(), action.getRuleGroupInstanceId(),
			ActionKeys.UPDATE);

		return mdrActionLocalService.updateAction(
			actionId, nameMap, descriptionMap, type, typeSettings,
			serviceContext);
	}

	@Override
	public MDRAction updateAction(
			long actionId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			UnicodeProperties typeSettingsProperties,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		MDRAction action = mdrActionPersistence.findByPrimaryKey(actionId);

		MDRRuleGroupInstancePermissionUtil.check(
			getPermissionChecker(), action.getRuleGroupInstanceId(),
			ActionKeys.UPDATE);

		return mdrActionLocalService.updateAction(
			actionId, nameMap, descriptionMap, type, typeSettingsProperties,
			serviceContext);
	}

}