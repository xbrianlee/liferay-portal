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
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;

/**
 * @author Raymond Augé
 */
public class ExpandoColumnPermissionImpl implements ExpandoColumnPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker, ExpandoColumn column,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, column, actionId)) {
			throw new PrincipalException();
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long columnId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, columnId, actionId)) {
			throw new PrincipalException();
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long companyId,
			String className, String tableName, String columnName,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(
				permissionChecker, companyId, className, tableName, columnName,
				actionId)) {

			throw new PrincipalException();
		}
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, ExpandoColumn column,
		String actionId) {

		return permissionChecker.hasPermission(
			0, ExpandoColumn.class.getName(), column.getColumnId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long columnId, String actionId)
		throws PortalException, SystemException {

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			columnId);

		return contains(permissionChecker, column, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long companyId,
			String className, String tableName, String columnName,
			String actionId)
		throws SystemException {

		ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(
			companyId, className, tableName, columnName);

		return contains(permissionChecker, column, actionId);
	}

}