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

/**
 * @author Brian Wing Shun Chan
 */
public interface OAuthManager {

	public Token getAccessToken(Token requestToken, Verifier verifier)
		throws OAuthException;

	public Token getRequestToken() throws OAuthException;

	public String getVersion() throws OAuthException;

	public void signRequest(Token accessToken, OAuthRequest oAuthRequest)
		throws OAuthException;

}