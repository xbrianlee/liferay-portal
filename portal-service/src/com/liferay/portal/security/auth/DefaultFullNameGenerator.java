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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.UserConstants;

/**
 * @author Michael C. Han
 */
public class DefaultFullNameGenerator implements FullNameGenerator {

	@Override
	public String getFullName(
		String firstName, String middleName, String lastName) {

		String fullName = buildFullName(firstName, middleName, lastName, false);

		if (fullName.length() <= UserConstants.FULL_NAME_MAX_LENGTH) {
			return fullName;
		}

		if (_log.isInfoEnabled()) {
			StringBundler sb = new StringBundler(5);

			sb.append("Full name exceeds ");
			sb.append(UserConstants.FULL_NAME_MAX_LENGTH);
			sb.append(" characters for user ");
			sb.append(fullName);
			sb.append(". Full name has been shortened.");

			_log.info(sb.toString());
		}

		fullName = buildFullName(firstName, middleName, lastName, true);

		if (fullName.length() <= UserConstants.FULL_NAME_MAX_LENGTH) {
			return fullName;
		}

		return fullName.substring(0, UserConstants.FULL_NAME_MAX_LENGTH);
	}

	@Override
	public String[] splitFullName(String fullName) {
		String firstName = StringPool.BLANK;
		String middleName = StringPool.BLANK;
		String lastName = StringPool.BLANK;

		if (Validator.isNull(fullName)) {
			return new String[] {firstName, middleName, lastName};
		}

		String[] name = StringUtil.split(fullName, CharPool.SPACE);

		firstName = name[0];
		middleName = StringPool.BLANK;
		lastName = name[name.length - 1];

		if (name.length > 2) {
			for (int i = 1; i < name.length - 1; i++) {
				if (Validator.isNull(name[i].trim())) {
					continue;
				}

				if (i != 1) {
					middleName += StringPool.SPACE;
				}

				middleName += name[i].trim();
			}
		}

		return new String[] {firstName, middleName, lastName};
	}

	protected String buildFullName(
		String firstName, String middleName, String lastName,
		boolean useInitials) {

		StringBundler sb = new StringBundler(5);

		if (useInitials) {
			firstName = firstName.substring(0, 1);
		}

		sb.append(firstName);

		if (Validator.isNotNull(middleName)) {
			if (useInitials) {
				middleName = middleName.substring(0, 1);
			}

			sb.append(StringPool.SPACE);
			sb.append(middleName);
		}

		if (Validator.isNotNull(lastName)) {
			sb.append(StringPool.SPACE);
			sb.append(lastName);
		}

		return sb.toString();
	}

	private static Log _log = LogFactoryUtil.getLog(
		DefaultFullNameGenerator.class);

}