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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jodd.util.BCrypt;

/**
 * @author Michael C. Han
 * @author Tomas Polesovsky
 */
public class BCryptPasswordEncryptor
	extends BasePasswordEncryptor implements PasswordEncryptor {

	@Override
	public String[] getSupportedAlgorithmTypes() {
		return new String[] {PasswordEncryptorUtil.TYPE_BCRYPT};
	}

	@Override
	protected String doEncrypt(
		String algorithm, String plainTextPassword, String encryptedPassword) {

		String salt = null;

		if (Validator.isNull(encryptedPassword)) {
			int rounds = _ROUNDS;

			Matcher matcher = _pattern.matcher(algorithm);

			if (matcher.matches()) {
				rounds = GetterUtil.getInteger(matcher.group(1), rounds);
			}

			salt = BCrypt.gensalt(rounds);
		}
		else {
			salt = encryptedPassword.substring(0, 29);
		}

		return BCrypt.hashpw(plainTextPassword, salt);
	}

	private static final int _ROUNDS = 10;

	private static Pattern _pattern = Pattern.compile(
		"^BCrypt/([0-9]+)$", Pattern.CASE_INSENSITIVE);

}