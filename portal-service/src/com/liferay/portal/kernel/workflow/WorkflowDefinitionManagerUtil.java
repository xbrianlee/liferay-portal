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
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 * @author Eduardo Lundgren
 * @author Raymond Augé
 */
public class WorkflowDefinitionManagerUtil {

	public static WorkflowDefinition deployWorkflowDefinition(
			long companyId, long userId, String title, byte[] bytes)
		throws WorkflowException {

		return getWorkflowDefinitionManager().deployWorkflowDefinition(
			companyId, userId, title, bytes);
	}

	public static int getActiveWorkflowDefinitionCount(long companyId)
		throws WorkflowException {

		return getWorkflowDefinitionManager().getActiveWorkflowDefinitionCount(
			companyId);
	}

	public static int getActiveWorkflowDefinitionCount(
			long companyId, String name)
		throws WorkflowException {

		return getWorkflowDefinitionManager().getActiveWorkflowDefinitionCount(
			companyId, name);
	}

	public static List<WorkflowDefinition> getActiveWorkflowDefinitions(
			long companyId, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowDefinitionManager().getActiveWorkflowDefinitions(
			companyId, start, end, orderByComparator);
	}

	public static List<WorkflowDefinition> getActiveWorkflowDefinitions(
			long companyId, String name, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowDefinitionManager().getActiveWorkflowDefinitions(
			companyId, name, start, end, orderByComparator);
	}

	public static WorkflowDefinition getLatestKaleoDefinition(
			long companyId, String name)
		throws WorkflowException {

		return getWorkflowDefinitionManager().getLatestKaleoDefinition(
			companyId, name);
	}

	public static WorkflowDefinition getWorkflowDefinition(
			long companyId, String name, int version)
		throws WorkflowException {

		return getWorkflowDefinitionManager().getWorkflowDefinition(
			companyId, name, version);
	}

	public static int getWorkflowDefinitionCount(long companyId)
		throws WorkflowException {

		return getWorkflowDefinitionManager().getWorkflowDefinitionCount(
			companyId);
	}

	public static int getWorkflowDefinitionCount(long companyId, String name)
		throws WorkflowException {

		return getWorkflowDefinitionManager().getWorkflowDefinitionCount(
			companyId, name);
	}

	public static WorkflowDefinitionManager getWorkflowDefinitionManager() {
		PortalRuntimePermission.checkGetBeanProperty(
			WorkflowDefinitionManagerUtil.class);

		return _workflowDefinitionManager;
	}

	public static List<WorkflowDefinition> getWorkflowDefinitions(
			long companyId, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowDefinitionManager().getWorkflowDefinitions(
			companyId, start, end, orderByComparator);
	}

	public static List<WorkflowDefinition> getWorkflowDefinitions(
			long companyId, String name, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowDefinitionManager().getWorkflowDefinitions(
			companyId, name, start, end, orderByComparator);
	}

	public static void undeployWorkflowDefinition(
			long companyId, long userId, String name, int version)
		throws WorkflowException {

		getWorkflowDefinitionManager().undeployWorkflowDefinition(
			companyId, userId, name, version);
	}

	public static WorkflowDefinition updateActive(
			long companyId, long userId, String name, int version,
			boolean active)
		throws WorkflowException {

		return getWorkflowDefinitionManager().updateActive(
			companyId, userId, name, version, active);
	}

	public static WorkflowDefinition updateTitle(
			long companyId, long userId, String name, int version, String title)
		throws WorkflowException {

		return getWorkflowDefinitionManager().updateTitle(
			companyId, userId, name, version, title);
	}

	public static void validateWorkflowDefinition(byte[] bytes)
		throws WorkflowException {

		getWorkflowDefinitionManager().validateWorkflowDefinition(bytes);
	}

	public void setWorkflowDefinitionManager(
		WorkflowDefinitionManager workflowDefinitionManager) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_workflowDefinitionManager = workflowDefinitionManager;
	}

	private static WorkflowDefinitionManager _workflowDefinitionManager;

}