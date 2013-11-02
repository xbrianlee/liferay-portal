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

package com.liferay.portal.kernel.workflow;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Bruno Farache
 * @author Raymond Augé
 */
public class WorkflowStatusManagerUtil {

	public static WorkflowStatusManager getWorkflowStatusManager() {
		PortalRuntimePermission.checkGetBeanProperty(
			WorkflowStatusManagerUtil.class);

		return _workflowStatusManager;
	}

	public static void updateStatus(
			int status, Map<String, Serializable> workflowContext)
		throws WorkflowException {

		getWorkflowStatusManager().updateStatus(status, workflowContext);
	}

	public void setWorkflowStatusManager(
		WorkflowStatusManager workflowStatusManager) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_workflowStatusManager = workflowStatusManager;
	}

	private static WorkflowStatusManager _workflowStatusManager;

}