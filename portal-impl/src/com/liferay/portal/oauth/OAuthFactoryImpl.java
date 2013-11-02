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
import com.liferay.portal.kernel.oauth.OAuthFactory;
import com.liferay.portal.kernel.oauth.OAuthManager;
import com.liferay.portal.kernel.oauth.OAuthRequest;
import com.liferay.portal.kernel.oauth.Token;
import com.liferay.portal.kernel.oauth.Verb;
import com.liferay.portal.kernel.oauth.Verifier;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class OAuthFactoryImpl implements OAuthFactory {

	@Override
	public OAuthManager createOAuthManager(
			String key, String secret, String accessURL, String requestURL,
			String callbackURL, String scope)
		throws OAuthException {

		try {
			return new OAuthManagerImpl(
				key, secret, accessURL, requestURL, callbackURL, scope);
		}
		catch (Exception e) {
			throw new OAuthException(e);
		}
	}

	@Override
	public OAuthRequest createOAuthRequest(Verb verb, String url)
		throws OAuthException {

		try {
			return new OAuthRequestImpl(
				new org.scribe.model.OAuthRequest(
					VerbTranslator.translate(verb), url));
		}
		catch (Exception e) {
			throw new OAuthException(e);
		}
	}

	@Override
	public Token createToken(String token, String secret)
		throws OAuthException {

		try {
			return new TokenImpl(new org.scribe.model.Token(token, secret));
		}
		catch (Exception e) {
			throw new OAuthException(e);
		}
	}

	@Override
	public Verifier createVerifier(String verifier) throws OAuthException {
		try {
			return new VerifierImpl(new org.scribe.model.Verifier(verifier));
		}
		catch (Exception e) {
			throw new OAuthException(e);
		}
	}

}