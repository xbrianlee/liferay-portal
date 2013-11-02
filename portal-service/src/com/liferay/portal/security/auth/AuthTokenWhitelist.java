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

import java.util.Set;

/**
 * @author Tomas Polesovsky
 * @author Raymond Augé
 */
public interface AuthTokenWhitelist {

	public Set<String> getOriginCSRFWhitelist();

	public Set<String> getPortletCSRFWhitelist();

	public Set<String> getPortletCSRFWhitelistActions();

	public Set<String> getPortletInvocationWhitelist();

	public Set<String> getPortletInvocationWhitelistActions();

	public boolean isOriginCSRFWhitelisted(long companyId, String origin);

	public boolean isPortletCSRFWhitelisted(
		long companyId, String portletId, String strutsAction);

	public boolean isPortletInvocationWhitelisted(
		long companyId, String portletId, String strutsAction);

	public boolean isValidSharedSecret(String sharedSecret);

	public Set<String> resetOriginCSRFWhitelist();

	public Set<String> resetPortletCSRFWhitelist();

	public Set<String> resetPortletCSRFWhitelistActions();

	public Set<String> resetPortletInvocationWhitelist();

	public Set<String> resetPortletInvocationWhitelistActions();

}