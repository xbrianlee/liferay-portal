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

package com.liferay.portal.lar;

import com.liferay.portal.kernel.lar.UserIdStrategy;
import com.liferay.portal.model.User;

/**
 * @author Bruno Farache
 */
public class AlwaysCurrentUserIdStrategy implements UserIdStrategy {

	public AlwaysCurrentUserIdStrategy(User user) {
		_user = user;
	}

	@Override
	public long getUserId(String userUuid) {
		return _user.getUserId();
	}

	private User _user;

}