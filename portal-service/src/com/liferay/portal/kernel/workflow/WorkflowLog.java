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

import java.util.Date;

/**
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public interface WorkflowLog {

	public static final int TASK_ASSIGN = 1;

	public static final int TASK_COMPLETION = 3;

	public static final int TASK_UPDATE = 2;

	public static final int TRANSITION = 0;

	public long getAuditUserId();

	public String getComment();

	public Date getCreateDate();

	public long getPreviousRoleId();

	public String getPreviousState();

	public long getPreviousUserId();

	public long getRoleId();

	public String getState();

	public int getType();

	public long getUserId();

	public long getWorkflowLogId();

	public long getWorkflowTaskId();

}