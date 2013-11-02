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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.LayoutSetBranch;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.LayoutSetBranchServiceBaseImpl;
import com.liferay.portal.service.permission.GroupPermissionUtil;
import com.liferay.portal.service.permission.LayoutSetBranchPermissionUtil;

import java.util.List;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public class LayoutSetBranchServiceImpl extends LayoutSetBranchServiceBaseImpl {

	@Override
	public LayoutSetBranch addLayoutSetBranch(
			long groupId, boolean privateLayout, String name,
			String description, boolean master, long copyLayoutSetBranchId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_LAYOUT_SET_BRANCH);

		return layoutSetBranchLocalService.addLayoutSetBranch(
			getUserId(), groupId, privateLayout, name, description, master,
			copyLayoutSetBranchId, serviceContext);
	}

	@Override
	public void deleteLayoutSetBranch(long layoutSetBranchId)
		throws PortalException, SystemException {

		LayoutSetBranchPermissionUtil.check(
			getPermissionChecker(), layoutSetBranchId, ActionKeys.DELETE);

		layoutSetBranchLocalService.deleteLayoutSetBranch(layoutSetBranchId);
	}

	@Override
	public List<LayoutSetBranch> getLayoutSetBranches(
			long groupId, boolean privateLayout)
		throws SystemException {

		return layoutSetBranchLocalService.getLayoutSetBranches(
			groupId, privateLayout);
	}

	@Override
	public LayoutSetBranch mergeLayoutSetBranch(
			long layoutSetBranchId, long mergeLayoutSetBranchId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		LayoutSetBranchPermissionUtil.check(
			getPermissionChecker(), layoutSetBranchId, ActionKeys.UPDATE);

		return layoutSetBranchLocalService.mergeLayoutSetBranch(
			layoutSetBranchId, mergeLayoutSetBranchId, serviceContext);
	}

	@Override
	public LayoutSetBranch updateLayoutSetBranch(
			long groupId, long layoutSetBranchId, String name,
			String description, ServiceContext serviceContext)
		throws PortalException, SystemException {

		LayoutSetBranchPermissionUtil.check(
			getPermissionChecker(), layoutSetBranchId, ActionKeys.UPDATE);

		return layoutSetBranchLocalService.updateLayoutSetBranch(
			layoutSetBranchId, name, description, serviceContext);
	}

}