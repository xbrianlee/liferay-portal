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

package com.liferay.portal.kernel.oauth;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuthFactoryUtil {

	public static OAuthManager createOAuthManager(
			String key, String secret, String accessURL, String requestURL,
			String callbackURL, String scope)
		throws OAuthException {

		return getOAuthFactory().createOAuthManager(
			key, secret, accessURL, requestURL, callbackURL, scope);
	}

	public static OAuthRequest createOAuthRequest(Verb verb, String url)
		throws OAuthException {

		return getOAuthFactory().createOAuthRequest(verb, url);
	}

	public static Token createToken(String token, String secret)
		throws OAuthException {

		return getOAuthFactory().createToken(token, secret);
	}

	public static Verifier createVerifier(String verifier)
		throws OAuthException {

		return getOAuthFactory().createVerifier(verifier);
	}

	public static OAuthFactory getOAuthFactory() {
		PortalRuntimePermission.checkGetBeanProperty(OAuthFactoryUtil.class);

		return _oAuthFactory;
	}

	public void setOAuthFactory(OAuthFactory oAuthFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_oAuthFactory = oAuthFactory;
	}

	private static OAuthFactory _oAuthFactory;

}