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

package com.liferay.portal.security.pwd;

import com.liferay.portal.UserPasswordException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.util.PropsUtil;
import com.liferay.util.PwdGenerator;

/**
 * @author Brian Wing Shun Chan
 */
public class RegExpToolkit extends BasicToolkit {

	public RegExpToolkit() {
		_pattern = PropsUtil.get(PropsKeys.PASSWORDS_REGEXPTOOLKIT_PATTERN);
		_charset = PropsUtil.get(PropsKeys.PASSWORDS_REGEXPTOOLKIT_CHARSET);
		_length = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.PASSWORDS_REGEXPTOOLKIT_LENGTH));
	}

	@Override
	public String generate(PasswordPolicy passwordPolicy) {
		return PwdGenerator.getPassword(_charset, _length);
	}

	@Override
	public void validate(
			long userId, String password1, String password2,
			PasswordPolicy passwordPolicy)
		throws PortalException {

		boolean value = password1.matches(_pattern);

		if (!value) {
			if (_log.isWarnEnabled()) {
				_log.warn("User " + userId + " attempted an invalid password");
			}

			throw new UserPasswordException(
				UserPasswordException.PASSWORD_INVALID);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(RegExpToolkit.class);

	private String _charset;
	private int _length;
	private String _pattern;

}