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

package com.liferay.portal.kernel.googleapps;

/**
 * @author Brian Wing Shun Chan
 */
public class GNickname {

	public String getNickname() {
		return _nickname;
	}

	public long getUserId() {
		return _userId;
	}

	public void setNickname(String nickname) {
		_nickname = nickname;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	private String _nickname;
	private long _userId;

}