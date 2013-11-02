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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Juan Fernández
 */
public class DefaultScreenNameGenerator implements ScreenNameGenerator {

	@Override
	public String generate(long companyId, long userId, String emailAddress)
		throws Exception {

		String screenName = null;

		if (Validator.isNotNull(emailAddress)) {
			screenName = StringUtil.extractFirst(emailAddress, CharPool.AT);

			screenName = StringUtil.toLowerCase(screenName);

			for (char c : screenName.toCharArray()) {
				if (!Validator.isChar(c) && !Validator.isDigit(c) &&
					(c != CharPool.DASH) && (c != CharPool.PERIOD)) {

					screenName = StringUtil.replace(
						screenName, c, CharPool.PERIOD);
				}
			}

			if (screenName.equals(DefaultScreenNameValidator.CYRUS) ||
				screenName.equals(DefaultScreenNameValidator.POSTFIX)) {

				screenName += StringPool.PERIOD + userId;
			}
		}
		else {
			screenName = String.valueOf(userId);
		}

		if (!_USERS_SCREEN_NAME_ALLOW_NUMERIC &&
			Validator.isNumber(screenName)) {

			screenName = _NON_NUMERICAL_PREFIX + screenName;
		}

		String[] reservedScreenNames = PrefsPropsUtil.getStringArray(
			companyId, PropsKeys.ADMIN_RESERVED_SCREEN_NAMES,
			StringPool.NEW_LINE, _ADMIN_RESERVED_SCREEN_NAMES);

		for (String reservedScreenName : reservedScreenNames) {
			if (StringUtil.equalsIgnoreCase(screenName, reservedScreenName)) {
				return getUnusedScreenName(companyId, screenName);
			}
		}

		if (UserLocalServiceUtil.fetchUserByScreenName(
				companyId, screenName) != null) {

			return getUnusedScreenName(companyId, screenName);
		}

		if (GroupLocalServiceUtil.fetchFriendlyURLGroup(
				companyId, StringPool.SLASH + screenName) == null) {

			return screenName;
		}

		return getUnusedScreenName(companyId, screenName);
	}

	protected String getUnusedScreenName(long companyId, String screenName)
		throws SystemException {

		for (int i = 1;; i++) {
			String tempScreenName = screenName + StringPool.PERIOD + i;

			if (UserLocalServiceUtil.fetchUserByScreenName(
					companyId, tempScreenName) != null) {

				continue;
			}

			if (GroupLocalServiceUtil.fetchFriendlyURLGroup(
					companyId, StringPool.SLASH + tempScreenName) == null) {

				return tempScreenName;
			}
		}
	}

	private static final String[] _ADMIN_RESERVED_SCREEN_NAMES =
		StringUtil.splitLines(
			PropsUtil.get(PropsKeys.ADMIN_RESERVED_SCREEN_NAMES));

	private static final String _NON_NUMERICAL_PREFIX = "user.";

	private static final boolean _USERS_SCREEN_NAME_ALLOW_NUMERIC =
		GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.USERS_SCREEN_NAME_ALLOW_NUMERIC));

}