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

package com.liferay.portal.workflow;

import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Miguel Pastor
 */
public class MyWorkflowTasksControlPanelEntry
	extends WorkflowControlPanelEntry {

	@Override
	protected boolean hasPermissionImplicitlyGranted(
			PermissionChecker permissionChecker, Group group, Portlet portlet)
		throws Exception {

		if (WorkflowTaskManagerUtil.getWorkflowTaskCountByUser(
				permissionChecker.getCompanyId(), permissionChecker.getUserId(),
				null) > 0) {

			return true;
		}

		if (WorkflowTaskManagerUtil.getWorkflowTaskCountByUserRoles(
				permissionChecker.getCompanyId(), permissionChecker.getUserId(),
				null) > 0) {

			return true;
		}

		return false;
	}

}