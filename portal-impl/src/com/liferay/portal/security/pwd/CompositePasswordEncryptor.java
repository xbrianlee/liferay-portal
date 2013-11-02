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
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class CompositePasswordEncryptor
	extends BasePasswordEncryptor implements PasswordEncryptor {

	@Override
	public String[] getSupportedAlgorithmTypes() {
		throw new UnsupportedOperationException();
	}

	public void setDefaultPasswordEncryptor(
		PasswordEncryptor defaultPasswordEncryptor) {

		_defaultPasswordEncryptor = defaultPasswordEncryptor;
	}

	public void setPasswordEncryptors(
		List<PasswordEncryptor> passwordEncryptors) {

		for (PasswordEncryptor passwordEncryptor : passwordEncryptors) {
			if (_log.isDebugEnabled()) {
				_log.debug("Registering " + passwordEncryptor);
			}

			String[] supportedAlgorithmTypes =
				passwordEncryptor.getSupportedAlgorithmTypes();

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Registering " + StringUtil.merge(supportedAlgorithmTypes) +
						" for " + passwordEncryptor.getClass().getName());
			}

			for (String supportedAlgorithmType : supportedAlgorithmTypes) {
				_passwordEncryptors.put(
					supportedAlgorithmType, passwordEncryptor);
			}
		}
	}

	@Override
	protected String doEncrypt(
			String algorithm, String plainTextPassword,
			String encryptedPassword)
		throws PwdEncryptorException {

		if (Validator.isNull(algorithm)) {
			throw new IllegalArgumentException("Invalid algorithm");
		}

		PasswordEncryptor passwordEncryptor = null;

		if (algorithm.startsWith(PasswordEncryptorUtil.TYPE_BCRYPT)) {
			passwordEncryptor = _passwordEncryptors.get(
				PasswordEncryptorUtil.TYPE_BCRYPT);
		}
		else if (algorithm.startsWith(PasswordEncryptorUtil.TYPE_PBKDF2)) {
			passwordEncryptor = _passwordEncryptors.get(
				PasswordEncryptorUtil.TYPE_PBKDF2);
		}
		else {
			passwordEncryptor = _passwordEncryptors.get(algorithm);
		}

		if (passwordEncryptor == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No password encryptor found for " + algorithm);
			}

			passwordEncryptor = _defaultPasswordEncryptor;
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Found " + ClassUtil.getClassName(passwordEncryptor) +
					" to encrypt password using " + algorithm);
		}

		return passwordEncryptor.encrypt(
			algorithm, plainTextPassword, encryptedPassword);
	}

	private static Log _log = LogFactoryUtil.getLog(
		CompositePasswordEncryptor.class);

	private PasswordEncryptor _defaultPasswordEncryptor;
	private Map<String, PasswordEncryptor> _passwordEncryptors =
		new HashMap<String, PasswordEncryptor>();

}