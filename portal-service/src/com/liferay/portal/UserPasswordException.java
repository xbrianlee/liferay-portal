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

package com.liferay.portal;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class UserPasswordException extends PortalException {

	public static final int PASSWORD_ALREADY_USED = 1;

	public static final int PASSWORD_CONTAINS_TRIVIAL_WORDS = 2;

	public static final int PASSWORD_INVALID = 3;

	public static final int PASSWORD_LENGTH = 4;

	public static final int PASSWORD_NOT_CHANGEABLE = 5;

	public static final int PASSWORD_SAME_AS_CURRENT = 6;

	public static final int PASSWORD_TOO_TRIVIAL = 8;

	public static final int PASSWORD_TOO_YOUNG = 9;

	public static final int PASSWORDS_DO_NOT_MATCH = 10;

	public UserPasswordException(int type) {
		_type = type;
	}

	public int getType() {
		return _type;
	}

	private int _type;

}