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

package com.liferay.portal.model;

import com.liferay.portal.kernel.exception.SystemException;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public interface WorkflowedModel {

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #isApproved()}
	 */
	public boolean getApproved();

	public int getStatus();

	public long getStatusByUserId();

	public String getStatusByUserName();

	public String getStatusByUserUuid() throws SystemException;

	public Date getStatusDate();

	public boolean isApproved();

	public boolean isDenied();

	public boolean isDraft();

	public boolean isExpired();

	public boolean isInactive();

	public boolean isIncomplete();

	public boolean isPending();

	public boolean isScheduled();

	public void setStatus(int status);

	public void setStatusByUserId(long statusByUserId);

	public void setStatusByUserName(String statusByUserName);

	public void setStatusByUserUuid(String statusByUserUuid);

	public void setStatusDate(Date statusDate);

}