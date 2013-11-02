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

import com.liferay.portal.PwdEncryptorException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PropsUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Scott Lee
 * @author Tomas Polesovsky
 * @author Michael C. Han
 */
public class PasswordEncryptorUtil {

	public static final String PASSWORDS_ENCRYPTION_ALGORITHM =
		StringUtil.toUpperCase(
			GetterUtil.getString(
				PropsUtil.get(PropsKeys.PASSWORDS_ENCRYPTION_ALGORITHM)));

	public static final String TYPE_BCRYPT = "BCRYPT";

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #TYPE_UFC_CRYPT}
	 */
	public static final String TYPE_CRYPT = "CRYPT";

	public static final String TYPE_MD2 = "MD2";

	public static final String TYPE_MD5 = "MD5";

	public static final String TYPE_NONE = "NONE";

	public static final String TYPE_PBKDF2 = "PBKDF2";

	public static final String TYPE_SHA = "SHA";

	public static final String TYPE_SHA_256 = "SHA-256";

	public static final String TYPE_SHA_384 = "SHA-384";

	public static final String TYPE_SSHA = "SSHA";

	public static final String TYPE_UFC_CRYPT = "UFC-CRYPT";

	public static String encrypt(String plainTextPassword)
		throws PwdEncryptorException {

		return encrypt(plainTextPassword, null);
	}

	public static String encrypt(
			String plainTextPassword, String encryptedPassword)
		throws PwdEncryptorException {

		long startTime = 0;

		if (_log.isDebugEnabled()) {
			startTime = System.currentTimeMillis();
		}

		try {
			return encrypt(
				PASSWORDS_ENCRYPTION_ALGORITHM, plainTextPassword,
				encryptedPassword);
		}
		finally {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Password encrypted in " +
						(System.currentTimeMillis() - startTime) + "ms");
			}
		}
	}

	public static String encrypt(
			String algorithm, String plainTextPassword,
			String encryptedPassword)
		throws PwdEncryptorException {

		return _passwordEncryptor.encrypt(
			algorithm, plainTextPassword, encryptedPassword);
	}

	public PasswordEncryptor getPasswordEncryptor() {
		return _passwordEncryptor;
	}

	public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
		_passwordEncryptor = passwordEncryptor;
	}

	private static Log _log = LogFactoryUtil.getLog(
		PasswordEncryptorUtil.class);

	private static PasswordEncryptor _passwordEncryptor;

}