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

package com.liferay.portal.security.jaas.ext.weblogic;

import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.security.jaas.ext.BasicLoginModule;

import java.security.Principal;

import javax.security.auth.login.LoginException;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalLoginModule extends BasicLoginModule {

	@Override
	protected Principal getPortalPrincipal(String name) throws LoginException {
		try {
			return (Principal)InstanceFactory.newInstance(
				_WLS_USER_IMPL, String.class, name);
		}
		catch (Exception e) {
			throw new LoginException(e.getMessage());
		}
	}

	private static final String _WLS_USER_IMPL =
		"weblogic.security.principal.WLSUserImpl";

}