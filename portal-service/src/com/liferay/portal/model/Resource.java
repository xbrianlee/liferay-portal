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

/**
 * @author Brian Wing Shun Chan
 * @see    com.liferay.portal.model.impl.ResourceImpl
 */
public interface Resource {

	public long getCodeId();

	public long getCompanyId();

	public String getName();

	public String getPrimKey();

	public long getResourceId();

	public int getScope();

	public void setCodeId(long codeId);

	public void setCompanyId(long companyId);

	public void setName(String name);

	public void setPrimKey(String primKey);

	public void setResourceId(long resourceId);

	public void setScope(int scope);

}