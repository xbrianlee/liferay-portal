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

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 * @author Raymond Augé
 */
public class WorkflowInstanceManagerUtil {

	public static void deleteWorkflowInstance(
			long companyId, long workflowInstanceId)
		throws WorkflowException {

		getWorkflowInstanceManager().deleteWorkflowInstance(
			companyId, workflowInstanceId);
	}

	public static List<String> getNextTransitionNames(
			long companyId, long userId, long workflowInstanceId)
		throws WorkflowException {

		return getWorkflowInstanceManager().getNextTransitionNames(
			companyId, userId, workflowInstanceId);
	}

	public static WorkflowInstance getWorkflowInstance(
			long companyId, long workflowInstanceId)
		throws WorkflowException {

		return getWorkflowInstanceManager().getWorkflowInstance(
			companyId, workflowInstanceId);
	}

	public static int getWorkflowInstanceCount(
			long companyId, Long userId, String assetClassName,
			Long assetClassPK, Boolean completed)
		throws WorkflowException {

		return getWorkflowInstanceManager().getWorkflowInstanceCount(
			companyId, userId, assetClassName, assetClassPK, completed);
	}

	public static int getWorkflowInstanceCount(
			long companyId, Long userId, String[] assetClassNames,
			Boolean completed)
		throws WorkflowException {

		return getWorkflowInstanceManager().getWorkflowInstanceCount(
			companyId, userId, assetClassNames, completed);
	}

	public static int getWorkflowInstanceCount(
			long companyId, String workflowDefinitionName,
			Integer workflowDefinitionVersion, Boolean completed)
		throws WorkflowException {

		return getWorkflowInstanceManager().getWorkflowInstanceCount(
			companyId, workflowDefinitionName, workflowDefinitionVersion,
			completed);
	}

	public static WorkflowInstanceManager getWorkflowInstanceManager() {
		PortalRuntimePermission.checkGetBeanProperty(
			WorkflowInstanceManagerUtil.class);

		return _workflowInstanceManager;
	}

	public static List<WorkflowInstance> getWorkflowInstances(
			long companyId, Long userId, String assetClassName,
			Long assetClassPK, Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowInstanceManager().getWorkflowInstances(
			companyId, userId, assetClassName, assetClassPK, completed, start,
			end, orderByComparator);
	}

	public static List<WorkflowInstance> getWorkflowInstances(
			long companyId, Long userId, String[] assetClassNames,
			Boolean completed, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowInstanceManager().getWorkflowInstances(
			companyId, userId, assetClassNames, completed, start, end,
			orderByComparator);
	}

	public static List<WorkflowInstance> getWorkflowInstances(
			long companyId, String workflowDefinitionName,
			Integer workflowDefinitionVersion, Boolean completed, int start,
			int end, OrderByComparator orderByComparator)
		throws WorkflowException {

		return getWorkflowInstanceManager().getWorkflowInstances(
			companyId, workflowDefinitionName, workflowDefinitionVersion,
			completed, start, end, orderByComparator);
	}

	public static WorkflowInstance signalWorkflowInstance(
			long companyId, long userId, long workflowInstanceId,
			String transitionName, Map<String, Serializable> workflowContext)
		throws WorkflowException {

		return getWorkflowInstanceManager().signalWorkflowInstance(
			companyId, userId, workflowInstanceId, transitionName,
			workflowContext);
	}

	public static WorkflowInstance startWorkflowInstance(
			long companyId, long groupId, long userId,
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			String transitionName, Map<String, Serializable> workflowContext)
		throws WorkflowException {

		return getWorkflowInstanceManager().startWorkflowInstance(
			companyId, groupId, userId, workflowDefinitionName,
			workflowDefinitionVersion, transitionName, workflowContext);
	}

	public static WorkflowInstance updateWorkflowContext(
			long companyId, long workflowInstanceId,
			Map<String, Serializable> workflowContext)
		throws WorkflowException {

		return getWorkflowInstanceManager().updateWorkflowContext(
			companyId, workflowInstanceId, workflowContext);
	}

	public void setWorkflowInstanceManager(
		WorkflowInstanceManager workflowInstanceManager) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_workflowInstanceManager = workflowInstanceManager;
	}

	private static WorkflowInstanceManager _workflowInstanceManager;

}