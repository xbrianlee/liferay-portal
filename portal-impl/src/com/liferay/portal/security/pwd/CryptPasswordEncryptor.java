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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.Validator;

import java.io.UnsupportedEncodingException;

import java.util.Random;

import org.vps.crypt.Crypt;

/**
 * @author Michael C. Han
 * @author Tomas Polesovsky
 */
public class CryptPasswordEncryptor
	extends BasePasswordEncryptor implements PasswordEncryptor {

	@Override
	@SuppressWarnings("deprecation")
	public String[] getSupportedAlgorithmTypes() {
		return new String[] {
			PasswordEncryptorUtil.TYPE_CRYPT,
			PasswordEncryptorUtil.TYPE_UFC_CRYPT
		};
	}

	@Override
	protected String doEncrypt(
			String algorithm, String plainTextPassword,
			String encryptedPassword)
		throws PwdEncryptorException {

		byte[] saltBytes = getSalt(encryptedPassword);

		try {
			return Crypt.crypt(
				saltBytes, plainTextPassword.getBytes(Digester.ENCODING));
		}
		catch (UnsupportedEncodingException uee) {
			throw new PwdEncryptorException(uee.getMessage(), uee);
		}
	}

	protected byte[] getSalt(String encryptedPassword)
		throws PwdEncryptorException {

		byte[] saltBytes = null;

		try {
			if (Validator.isNull(encryptedPassword)) {
				Random random = new Random();

				int x = random.nextInt(Integer.MAX_VALUE) % _SALT.length;
				int y = random.nextInt(Integer.MAX_VALUE) % _SALT.length;

				String salt = _SALT[x].concat(_SALT[y]);

				saltBytes = salt.getBytes(Digester.ENCODING);
			}
			else {
				String salt = encryptedPassword.substring(0, 2);

				saltBytes = salt.getBytes(Digester.ENCODING);
			}
		}
		catch (UnsupportedEncodingException uee) {
			throw new PwdEncryptorException(
				"Unable to extract salt from encrypted password " +
					uee.getMessage(),
				uee);
		}

		return saltBytes;
	}

	private static final String[] _SALT = ArrayUtil.toStringArray(
		"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789./".
			toCharArray());

}