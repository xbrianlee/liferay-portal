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

import com.liferay.portal.kernel.util.DigesterUtil;

/**
 * @author Michael C. Han
 * @author Tomas Polesovsky
 */
public class DefaultPasswordEncryptor
	extends BasePasswordEncryptor implements PasswordEncryptor {

	@Override
	public String[] getSupportedAlgorithmTypes() {
		return new String[0];
	}

	@Override
	protected String doEncrypt(
		String algorithm, String plainTextPassword, String encryptedPassword) {

		return DigesterUtil.digest(algorithm, plainTextPassword);
	}

}