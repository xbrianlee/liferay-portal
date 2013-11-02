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

/**
 * @author Michael C. Han
 */
public class WorkflowTaskAssignee implements Serializable {

	public WorkflowTaskAssignee(
		String assigneeClassName, long assigneeClassPK) {

		_assigneeClassName = assigneeClassName;
		_assigneeClassPK = assigneeClassPK;
	}

	public String getAssigneeClassName() {
		return _assigneeClassName;
	}

	public long getAssigneeClassPK() {
		return _assigneeClassPK;
	}

	private String _assigneeClassName;
	private long _assigneeClassPK;

}