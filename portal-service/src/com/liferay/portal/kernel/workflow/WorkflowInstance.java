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

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public interface WorkflowInstance {

	public void addChildWorkflowInstance(
		WorkflowInstance childWorkflowInstance);

	public int getChildrenWorkflowInstanceCount();

	public List<WorkflowInstance> getChildrenWorkflowInstances();

	public Date getEndDate();

	public WorkflowInstance getParentWorkflowInstance();

	public long getParentWorkflowInstanceId();

	public Date getStartDate();

	public String getState();

	public Map<String, Serializable> getWorkflowContext();

	public String getWorkflowDefinitionName();

	public int getWorkflowDefinitionVersion();

	public long getWorkflowInstanceId();

	public boolean isComplete();

	public void setParentWorkflowInstance(
		WorkflowInstance parentWorkflowInstance);

}