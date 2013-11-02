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

import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowLogManager;

import java.util.List;

/**
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowLogManagerProxyBean
	extends BaseProxyBean implements WorkflowLogManager {

	@Override
	public int getWorkflowLogCountByWorkflowInstance(
		long companyId, long workflowInstanceId, List<Integer> logTypes) {

		throw new UnsupportedOperationException();
	}

	@Override
	public int getWorkflowLogCountByWorkflowTask(
		long companyId, long workflowTaskId, List<Integer> logTypes) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<WorkflowLog> getWorkflowLogsByWorkflowInstance(
		long companyId, long workflowInstanceId, List<Integer> logTypes,
		int start, int end, OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<WorkflowLog> getWorkflowLogsByWorkflowTask(
		long companyId, long workflowTaskId, List<Integer> logTypes, int start,
		int end, OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

}