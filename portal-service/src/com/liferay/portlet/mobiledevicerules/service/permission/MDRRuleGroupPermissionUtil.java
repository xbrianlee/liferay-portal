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
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;

/**
 * @author Michael C. Han
 */
public class MDRRuleGroupPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long ruleGroupId,
			String actionId)
		throws PortalException, SystemException {

		getMDRRuleGroupPermission().check(
			permissionChecker, ruleGroupId, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, MDRRuleGroup ruleGroup,
			String actionId)
		throws PortalException {

		getMDRRuleGroupPermission().check(
			permissionChecker, ruleGroup, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long ruleGroupId,
			String actionId)
		throws PortalException, SystemException {

		return getMDRRuleGroupPermission().contains(
			permissionChecker, ruleGroupId, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, MDRRuleGroup ruleGroup,
		String actionId) {

		return getMDRRuleGroupPermission().contains(
			permissionChecker, ruleGroup, actionId);
	}

	public static MDRRuleGroupPermission getMDRRuleGroupPermission() {
		return _mdrRuleGroupPermission;
	}

	public void setMDRRuleGroupPermission(
		MDRRuleGroupPermission mdrRuleGroupPermission) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_mdrRuleGroupPermission = mdrRuleGroupPermission;
	}

	private static MDRRuleGroupPermission _mdrRuleGroupPermission;

}