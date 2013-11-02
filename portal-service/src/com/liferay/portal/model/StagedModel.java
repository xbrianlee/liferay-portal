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

import com.liferay.portal.kernel.lar.StagedModelType;

import java.util.Date;

/**
 * @author Michael C. Han
 */
public interface StagedModel extends ClassedModel {

	public Object clone();

	public long getCompanyId();

	public Date getCreateDate();

	public Date getModifiedDate();

	public StagedModelType getStagedModelType();

	public String getUuid();

	public void setCompanyId(long companyId);

	public void setCreateDate(Date date);

	public void setModifiedDate(Date date);

	public void setUuid(String uuid);

}