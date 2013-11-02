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

package com.liferay.portal.kernel.workflow.permission;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Jorge Ferrer
 */
public class WorkflowPermissionUtil {

	public static WorkflowPermission getWorkflowPermission() {
		PortalRuntimePermission.checkGetBeanProperty(
			WorkflowPermissionUtil.class);

		return _workflowPermission;
	}

	public static Boolean hasPermission(
		PermissionChecker permissionChecker, long groupId, String className,
		long classPK, String actionId) {

		return getWorkflowPermission().hasPermission(
			permissionChecker, groupId, className, classPK, actionId);
	}

	public void setWorkflowPermission(WorkflowPermission WorkflowPermission) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_workflowPermission = WorkflowPermission;
	}

	private static WorkflowPermission _workflowPermission;

}