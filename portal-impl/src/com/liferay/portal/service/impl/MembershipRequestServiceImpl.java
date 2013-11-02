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
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.MembershipRequestServiceBaseImpl;
import com.liferay.portal.service.permission.GroupPermissionUtil;

/**
 * @author Jorge Ferrer
 */
public class MembershipRequestServiceImpl
	extends MembershipRequestServiceBaseImpl {

	@Override
	public MembershipRequest addMembershipRequest(
			long groupId, String comments, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return membershipRequestLocalService.addMembershipRequest(
			getUserId(), groupId, comments, serviceContext);
	}

	@Override
	public void deleteMembershipRequests(long groupId, int statusId)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.ASSIGN_MEMBERS);

		membershipRequestLocalService.deleteMembershipRequests(
			groupId, statusId);
	}

	@Override
	public MembershipRequest getMembershipRequest(long membershipRequestId)
		throws PortalException, SystemException {

		return membershipRequestLocalService.getMembershipRequest(
			membershipRequestId);
	}

	@Override
	public void updateStatus(
			long membershipRequestId, String reviewComments, int statusId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		MembershipRequest membershipRequest =
			membershipRequestPersistence.findByPrimaryKey(membershipRequestId);

		GroupPermissionUtil.check(
			getPermissionChecker(), membershipRequest.getGroupId(),
			ActionKeys.ASSIGN_MEMBERS);

		membershipRequestLocalService.updateStatus(
			getUserId(), membershipRequestId, reviewComments, statusId, true,
			serviceContext);
	}

}