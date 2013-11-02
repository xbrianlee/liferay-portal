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

package com.liferay.portal.kernel.dao.orm;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.BaseLocalService;

/**
 * @author Brian Wing Shun Chan
 */
public interface ActionableDynamicQuery {

	public void performActions() throws PortalException, SystemException;

	public long performCount() throws PortalException, SystemException;

	public void setBaseLocalService(BaseLocalService baseLocalService)
		throws SystemException;

	public void setClass(Class<?> clazz);

	public void setClassLoader(ClassLoader classLoader);

	public void setCompanyId(long companyId);

	public void setGroupId(long groupId);

	public void setGroupIdPropertyName(String groupIdPropertyName);

	public void setInterval(int interval);

	public void setPrimaryKeyPropertyName(String primaryKeyPropertyName);

	public void setSearchEngineId(String searchEngineId);

}