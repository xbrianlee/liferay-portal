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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Set;

/**
 * @author Tomas Polesovsky
 * @author Raymond Augé
 */
public class AuthTokenWhitelistUtil {

	public static AuthTokenWhitelist getAuthTokenWhitelist() {
		PortalRuntimePermission.checkGetBeanProperty(AuthTokenWhitelist.class);

		return _authTokenWhitelist;
	}

	public static Set<String> getPortletCSRFWhitelist() {
		return getAuthTokenWhitelist().getPortletCSRFWhitelist();
	}

	public static Set<String> getPortletCSRFWhitelistActions() {
		return getAuthTokenWhitelist().getPortletCSRFWhitelistActions();
	}

	public static Set<String> getPortletInvocationWhitelist() {
		return getAuthTokenWhitelist().getPortletInvocationWhitelist();
	}

	public static Set<String> getPortletInvocationWhitelistActions() {
		return getAuthTokenWhitelist().getPortletInvocationWhitelistActions();
	}

	public static boolean isCSRFOrigintWhitelisted(
		long companyId, String origin) {

		return getAuthTokenWhitelist().isOriginCSRFWhitelisted(
			companyId, origin);
	}

	public static boolean isPortletCSRFWhitelisted(
		long companyId, String portletId, String strutsAction) {

		return getAuthTokenWhitelist().isPortletCSRFWhitelisted(
			companyId, portletId, strutsAction);
	}

	public static boolean isPortletInvocationWhitelisted(
		long companyId, String portletId, String strutsAction) {

		return getAuthTokenWhitelist().isPortletInvocationWhitelisted(
			companyId, portletId, strutsAction);
	}

	public static boolean isValidSharedSecret(String sharedSecret) {
		return getAuthTokenWhitelist().isValidSharedSecret(sharedSecret);
	}

	public static Set<String> resetOriginCSRFWhitelist() {
		return getAuthTokenWhitelist().resetOriginCSRFWhitelist();
	}

	public static Set<String> resetPortletCSRFWhitelist() {
		return getAuthTokenWhitelist().resetPortletCSRFWhitelist();
	}

	public static Set<String> resetPortletCSRFWhitelistActions() {
		return getAuthTokenWhitelist().resetPortletCSRFWhitelistActions();
	}

	public static Set<String> resetPortletInvocationWhitelist() {
		return getAuthTokenWhitelist().resetPortletInvocationWhitelist();
	}

	public static Set<String> resetPortletInvocationWhitelistActions() {
		return getAuthTokenWhitelist().resetPortletInvocationWhitelistActions();
	}

	public void setAuthTokenWhitelist(AuthTokenWhitelist authTokenWhitelist) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_authTokenWhitelist = authTokenWhitelist;
	}

	private static AuthTokenWhitelist _authTokenWhitelist;

}