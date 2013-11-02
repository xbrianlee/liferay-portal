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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.UserConstants;

/**
 * @author Wesley Gong
 */
public class DefaultEmailAddressGenerator implements EmailAddressGenerator {

	@Override
	public String generate(long companyId, long userId) {
		return userId + UserConstants.USERS_EMAIL_ADDRESS_AUTO_SUFFIX;
	}

	@Override
	public boolean isFake(String emailAddress) {
		if (Validator.isNull(emailAddress) ||
			StringUtil.endsWith(
				emailAddress, UserConstants.USERS_EMAIL_ADDRESS_AUTO_SUFFIX)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isGenerated(String emailAddress) {
		return isFake(emailAddress);
	}

}