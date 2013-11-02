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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PropsUtil;

import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Michael C. Han
 */
public class PwdAuthenticator {

	public static boolean authenticate(
			String login, String clearTextPassword,
			String currentEncryptedPassword)
		throws PwdEncryptorException, SystemException {

		String encryptedPassword = PasswordEncryptorUtil.encrypt(
			clearTextPassword, currentEncryptedPassword);

		if (currentEncryptedPassword.equals(encryptedPassword)) {
			return true;
		}
		else if (GetterUtil.getBoolean(
					PropsUtil.get(PropsKeys.AUTH_MAC_ALLOW))) {

			try {
				MessageDigest digester = MessageDigest.getInstance(
					PropsUtil.get(PropsKeys.AUTH_MAC_ALGORITHM));

				digester.update(login.getBytes(StringPool.UTF8));

				String shardKey = PropsUtil.get(PropsKeys.AUTH_MAC_SHARED_KEY);

				encryptedPassword = Base64.encode(
					digester.digest(shardKey.getBytes(StringPool.UTF8)));

				if (currentEncryptedPassword.equals(encryptedPassword)) {
					return true;
				}
				else {
					return false;
				}
			}
			catch (NoSuchAlgorithmException nsae) {
				throw new SystemException(nsae);
			}
			catch (UnsupportedEncodingException uee) {
				throw new SystemException(uee);
			}
		}

		return false;
	}

}