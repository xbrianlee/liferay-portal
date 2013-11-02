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

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.security.pacl.PACLConstants;
import com.liferay.portal.security.pacl.PACLPolicy;
import com.liferay.portal.security.pacl.PACLUtil;

import java.security.Permission;

import java.util.Properties;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public abstract class BaseChecker implements Checker, PACLConstants {

	@Override
	public AuthorizationProperty generateAuthorizationProperty(
		Object... arguments) {

		throw new UnsupportedOperationException();
	}

	@Override
	public ClassLoader getClassLoader() {
		return _paclPolicy.getClassLoader();
	}

	@Override
	public PACLPolicy getPACLPolicy() {
		return _paclPolicy;
	}

	@Override
	public String getServletContextName() {
		return _paclPolicy.getServletContextName();
	}

	public boolean isTrustedCaller(
		Class<?> callerClass, Permission permission) {

		return PACLUtil.isTrustedCaller(
			callerClass, permission, getPACLPolicy());
	}

	@Override
	public void setPACLPolicy(PACLPolicy paclPolicy) {
		_paclPolicy = paclPolicy;
	}

	protected Properties getProperties() {
		return _paclPolicy.getProperties();
	}

	protected String getProperty(String key) {
		return _paclPolicy.getProperty(key);
	}

	protected String[] getPropertyArray(String key) {
		return _paclPolicy.getPropertyArray(key);
	}

	protected boolean getPropertyBoolean(String key) {
		return _paclPolicy.getPropertyBoolean(key);
	}

	protected Set<String> getPropertySet(String key) {
		return _paclPolicy.getPropertySet(key);
	}

	protected void logSecurityException(Log log, String message) {
		if (log.isWarnEnabled()) {
			log.warn(message);
		}
	}

	private PACLPolicy _paclPolicy;

}