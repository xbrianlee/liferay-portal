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

package com.liferay.portal.spring.remoting;

import com.liferay.portal.PwdEncryptorException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.pwd.PasswordEncryptorUtil;

import java.io.IOException;

import java.net.HttpURLConnection;

import org.apache.commons.codec.binary.Base64;

import org.springframework.remoting.httpinvoker.SimpleHttpInvokerRequestExecutor;

/**
 * <p>
 * An HttpInvoker request executor for Spring Remoting that provides HTTP BASIC
 * authentication information for service invocations.
 * </p>
 *
 * @author Joel Kozikowski
 */
public class AuthenticatingHttpInvokerRequestExecutor
	extends SimpleHttpInvokerRequestExecutor {

	public AuthenticatingHttpInvokerRequestExecutor() {
		super();
	}

	public String getPassword() {
		return _password;
	}

	public long getUserId() {
		return _userId;
	}

	public void setPassword(String password) throws PwdEncryptorException {
		_password = PasswordEncryptorUtil.encrypt(password);
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	/**
	 * Called every time a HTTP invocation is made. This implementation allows
	 * the parent to setup the connection, and then adds an
	 * <code>Authorization</code> HTTP header property for BASIC authentication.
	 */
	@Override
	protected void prepareConnection(HttpURLConnection con, int contentLength)
		throws IOException {

		super.prepareConnection(con, contentLength);

		if (getUserId() > 0) {
			String password = GetterUtil.getString(getPassword());

			String base64 = getUserId() + StringPool.COLON + password;

			con.setRequestProperty(
				"Authorization",
				"Basic " + new String(Base64.encodeBase64(base64.getBytes())));
		}
	}

	private String _password;
	private long _userId;

}