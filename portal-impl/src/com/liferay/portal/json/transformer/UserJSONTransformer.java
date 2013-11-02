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

package com.liferay.portal.json.transformer;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.permission.UserPermissionUtil;

/**
 * @author Igor Spasic
 */
public class UserJSONTransformer extends FlexjsonObjectJSONTransformer {

	@Override
	public void transform(Object object) {
		User user = (User)object;

		boolean hidePrivateUserData = true;

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker != null) {
			if ((user.getUserId() == permissionChecker.getUserId()) ||
				UserPermissionUtil.contains(
					permissionChecker, user.getUserId(), ActionKeys.VIEW)) {

				hidePrivateUserData = false;
			}
		}

		if (hidePrivateUserData) {
			user.setPasswordUnencrypted(StringPool.BLANK);
			user.setReminderQueryQuestion(StringPool.BLANK);
			user.setReminderQueryAnswer(StringPool.BLANK);
			user.setEmailAddress(StringPool.BLANK);
			user.setFacebookId(0);
			user.setComments(StringPool.BLANK);
		}

		super.transform(object);
	}

}