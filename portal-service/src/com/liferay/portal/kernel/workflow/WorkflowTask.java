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
 * @author Marcellus Tavares
 */
public interface WorkflowTask {

	public long getAssigneeUserId();

	public Date getCompletionDate();

	public Date getCreateDate();

	public String getDescription();

	public Date getDueDate();

	public String getName();

	public Map<String, Serializable> getOptionalAttributes();

	public long getWorkflowDefinitionId();

	public String getWorkflowDefinitionName();

	public int getWorkflowDefinitionVersion();

	public long getWorkflowInstanceId();

	public List<WorkflowTaskAssignee> getWorkflowTaskAssignees();

	public long getWorkflowTaskId();

	public boolean isAssignedToSingleUser();

	public boolean isAsynchronous();

	public boolean isCompleted();

}