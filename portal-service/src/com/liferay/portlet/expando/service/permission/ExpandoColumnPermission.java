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

package com.liferay.portlet.expando.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.expando.model.ExpandoColumn;

/**
 * @author Michael C. Han
 */
public interface ExpandoColumnPermission {

	public void check(
			PermissionChecker permissionChecker, ExpandoColumn column,
			String actionId)
		throws PortalException;

	public void check(
			PermissionChecker permissionChecker, long columnId, String actionId)
		throws PortalException, SystemException;

	public void check(
			PermissionChecker permissionChecker, long companyId,
			String className, String tableName, String columnName,
			String actionId)
		throws PortalException, SystemException;

	public boolean contains(
		PermissionChecker permissionChecker, ExpandoColumn column,
		String actionId);

	public boolean contains(
			PermissionChecker permissionChecker, long columnId, String actionId)
		throws PortalException, SystemException;

	public boolean contains(
			PermissionChecker permissionChecker, long companyId,
			String className, String tableName, String columnName,
			String actionId)
		throws SystemException;

}