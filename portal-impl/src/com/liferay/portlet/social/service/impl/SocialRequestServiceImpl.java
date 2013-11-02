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

package com.liferay.portlet.social.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialRequest;
import com.liferay.portlet.social.service.base.SocialRequestServiceBaseImpl;
import com.liferay.portlet.social.service.permission.SocialRequestPermissionUtil;

/**
 * Provides the remote service for updating social requests. Its methods include
 * permission checks.
 *
 * @author Brian Wing Shun Chan
 */
public class SocialRequestServiceImpl extends SocialRequestServiceBaseImpl {

	@Override
	public SocialRequest updateRequest(
			long requestId, int status, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		SocialRequestPermissionUtil.check(
			getPermissionChecker(), requestId, ActionKeys.UPDATE);

		return socialRequestLocalService.updateRequest(
			requestId, status, themeDisplay);
	}

}