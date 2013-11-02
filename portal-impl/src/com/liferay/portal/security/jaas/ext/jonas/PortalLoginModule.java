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

package com.liferay.portal.security.jaas.ext.jonas;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassResolverUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.security.jaas.ext.BasicLoginModule;

import java.lang.reflect.Method;

import java.security.Principal;

import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalLoginModule extends BasicLoginModule {

	@Override
	public boolean commit() throws LoginException {
		boolean commitValue = super.commit();

		if (!commitValue) {
			return false;
		}

		Subject subject = getSubject();

		Set<Principal> principals = subject.getPrincipals();

		principals.add(getPrincipal());

		Set<Object> privateCredentials = subject.getPrivateCredentials();

		privateCredentials.add(getPassword());

		try {
			Principal group = (Principal)InstanceFactory.newInstance(
				_JGROUP, String.class, "Roles");
			Object role = InstanceFactory.newInstance(
				_JROLE, String.class, "users");

			MethodKey methodKey = new MethodKey(
				ClassResolverUtil.resolveByContextClassLoader(_JGROUP),
				"addMember", role.getClass());

			Method method = methodKey.getMethod();

			method.invoke(group, new Object[] {role});

			principals.add(group);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return commitValue;
	}

	@Override
	protected Principal getPortalPrincipal(String name) throws LoginException {
		try {
			return (Principal)InstanceFactory.newInstance(
				_JPRINCIPAL, String.class, name);
		}
		catch (Exception e) {
			throw new LoginException(e.getMessage());
		}
	}

	private static final String _JGROUP =
		"org.objectweb.jonas.security.auth.JGroup";

	private static final String _JPRINCIPAL =
		"org.objectweb.jonas.security.auth.JPrincipal";

	private static final String _JROLE =
		"org.objectweb.jonas.security.auth.JRole";

	private static Log _log = LogFactoryUtil.getLog(PortalLoginModule.class);

}