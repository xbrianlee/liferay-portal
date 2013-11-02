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

package com.liferay.portal.oauth;

import com.liferay.portal.kernel.oauth.OAuthException;
import com.liferay.portal.kernel.oauth.OAuthManager;
import com.liferay.portal.kernel.oauth.OAuthRequest;
import com.liferay.portal.kernel.oauth.Token;
import com.liferay.portal.kernel.oauth.Verifier;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthConstants;
import org.scribe.oauth.OAuthService;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuthManagerImpl implements OAuthManager {

	public OAuthManagerImpl(
		String key, String secret, final String accessURL,
		final String requestURL, String callbackURL, String scope) {

		Api api = new DefaultApi10a() {

			@Override
			public String getAccessTokenEndpoint() {
				return accessURL;
			}

			@Override
			public String getRequestTokenEndpoint() {
				return requestURL;
			}

		};

		if (callbackURL == null) {
			callbackURL = OAuthConstants.OUT_OF_BAND;
		}

		_oAuthService = api.createService(key, secret, callbackURL, scope);
	}

	@Override
	public Token getAccessToken(Token requestToken, Verifier verifier)
		throws OAuthException {

		try {
			return new TokenImpl(
				_oAuthService.getAccessToken(
					(org.scribe.model.Token)requestToken.getWrappedToken(),
					(org.scribe.model.Verifier)verifier.getWrappedVerifier()));
		}
		catch (Exception e) {
			throw new OAuthException(e);
		}
	}

	@Override
	public Token getRequestToken() throws OAuthException {
		try {
			return new TokenImpl(_oAuthService.getRequestToken());
		}
		catch (Exception e) {
			throw new OAuthException(e);
		}
	}

	@Override
	public String getVersion() throws OAuthException {
		try {
			return _oAuthService.getVersion();
		}
		catch (Exception e) {
			throw new OAuthException(e);
		}
	}

	@Override
	public void signRequest(Token accessToken, OAuthRequest oAuthRequest)
		throws OAuthException {

		try {
			_oAuthService.signRequest(
				(org.scribe.model.Token)accessToken.getWrappedToken(),
				(org.scribe.model.OAuthRequest)
					oAuthRequest.getWrappedOAuthRequest());
		}
		catch (Exception e) {
			throw new OAuthException(e);
		}
	}

	private OAuthService _oAuthService;

}