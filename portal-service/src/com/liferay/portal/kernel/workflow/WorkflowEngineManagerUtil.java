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

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class WorkflowEngineManagerUtil {

	public static String getKey() {
		return getWorkflowEngineManager().getKey();
	}

	public static String getName() {
		return getWorkflowEngineManager().getName();
	}

	public static Map<String, Object> getOptionalAttributes() {
		return getWorkflowEngineManager().getOptionalAttributes();
	}

	public static String getVersion() {
		return getWorkflowEngineManager().getVersion();
	}

	public static WorkflowEngineManager getWorkflowEngineManager() {
		PortalRuntimePermission.checkGetBeanProperty(
			WorkflowEngineManagerUtil.class);

		return _workflowEngineManager;
	}

	public static boolean isDeployed() {
		return getWorkflowEngineManager().isDeployed();
	}

	public void setWorkflowEngineManager(
		WorkflowEngineManager workflowEngineManager) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_workflowEngineManager = workflowEngineManager;
	}

	private static WorkflowEngineManager _workflowEngineManager;

}