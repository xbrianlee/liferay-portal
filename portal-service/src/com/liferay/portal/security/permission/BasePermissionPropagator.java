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

package com.liferay.portal.security.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;

/**
 * @author Hugo Huijser
 */
public abstract class BasePermissionPropagator implements PermissionPropagator {

	protected Set<String> getActionIds(String className) {
		List<String> actionIds = ResourceActionsUtil.getModelResourceActions(
			className);

		return SetUtil.fromCollection(actionIds);
	}

	protected Set<String> getAvailableActionIds(
			long companyId, String className, long primKey, long roleId,
			Set<String> actionIds)
		throws PortalException, SystemException {

		List<String> availableActionIds =
			ResourcePermissionLocalServiceUtil.
				getAvailableResourcePermissionActionIds(
					companyId, className, ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(primKey), roleId, actionIds);

		return SetUtil.fromCollection(availableActionIds);
	}

	protected void propagateRolePermissions(
			ActionRequest actionRequest, long roleId, String parentClassName,
			long parentPrimKey, String childClassName, long childPrimKey)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Set<String> parentActionIds = getActionIds(parentClassName);
		Set<String> childActionIds = getActionIds(childClassName);

		Set<String> parentAndChildCommonActionIds = new HashSet<String>();

		for (String actionId : childActionIds) {
			if (parentActionIds.contains(actionId)) {
				parentAndChildCommonActionIds.add(actionId);
			}
		}

		Set<String> parentAvailableActionIds = getAvailableActionIds(
			themeDisplay.getCompanyId(), parentClassName, parentPrimKey, roleId,
			parentActionIds);
		Set<String> childAvailableActionIds = getAvailableActionIds(
			themeDisplay.getCompanyId(), childClassName, childPrimKey, roleId,
			childActionIds);

		List<String> actionIds = new ArrayList<String>();

		for (String actionId : parentAndChildCommonActionIds) {
			if (parentAvailableActionIds.contains(actionId)) {
				actionIds.add(actionId);
			}
		}

		for (String actionId : childAvailableActionIds) {
			if (!parentAndChildCommonActionIds.contains(actionId)) {
				actionIds.add(actionId);
			}
		}

		ResourcePermissionServiceUtil.setIndividualResourcePermissions(
			themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(),
			childClassName, String.valueOf(childPrimKey), roleId,
			actionIds.toArray(new String[actionIds.size()]));
	}

}