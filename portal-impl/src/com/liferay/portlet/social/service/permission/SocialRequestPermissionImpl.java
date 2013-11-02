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

package com.liferay.portlet.social.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.social.model.SocialRequest;
import com.liferay.portlet.social.service.SocialRequestLocalServiceUtil;

/**
 * @author Shinn Lok
 */
public class SocialRequestPermissionImpl implements SocialRequestPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker, long requestId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, requestId, actionId)) {
			throw new PrincipalException();
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long requestId,
			String actionId)
		throws PortalException, SystemException {

		if (permissionChecker.isOmniadmin()) {
			return true;
		}

		if (actionId.equals(ActionKeys.UPDATE)) {
			SocialRequest request =
				SocialRequestLocalServiceUtil.getSocialRequest(requestId);

			if (permissionChecker.getUserId() == request.getReceiverUserId()) {
				return true;
			}
		}

		return false;
	}

}