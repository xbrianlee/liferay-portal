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

package com.liferay.portlet.mobiledevicerules.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance;

/**
 * @author Michael C. Han
 */
public class MDRRuleGroupInstancePermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long ruleGroupInstanceId,
			String actionId)
		throws PortalException, SystemException {

		getMDRRuleGroupInstancePermission().check(
			permissionChecker, ruleGroupInstanceId, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker,
			MDRRuleGroupInstance ruleGroupInstance, String actionId)
		throws PortalException {

		getMDRRuleGroupInstancePermission().check(
			permissionChecker, ruleGroupInstance, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long ruleGroupInstanceId,
			String actionId)
		throws PortalException, SystemException {

		return getMDRRuleGroupInstancePermission().contains(
			permissionChecker, ruleGroupInstanceId, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker,
		MDRRuleGroupInstance ruleGroupInstance, String actionId) {

		return getMDRRuleGroupInstancePermission().contains(
			permissionChecker, ruleGroupInstance, actionId);
	}

	public static MDRRuleGroupInstancePermission
		getMDRRuleGroupInstancePermission() {

		return _mdrRuleGroupInstancePermission;
	}

	public void setMDRRuleGroupInstancePermission(
		MDRRuleGroupInstancePermission mdrRuleGroupInstancePermission) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_mdrRuleGroupInstancePermission = mdrRuleGroupInstancePermission;
	}

	private static MDRRuleGroupInstancePermission
		_mdrRuleGroupInstancePermission;

}