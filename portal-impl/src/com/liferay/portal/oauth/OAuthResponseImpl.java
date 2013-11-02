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

import com.liferay.portal.kernel.oauth.OAuthResponse;

import org.scribe.model.Response;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuthResponseImpl implements OAuthResponse {

	public OAuthResponseImpl(Response response) {
		_response = response;
	}

	@Override
	public String getBody() {
		return _response.getBody();
	}

	@Override
	public int getStatus() {
		return _response.getCode();
	}

	@Override
	public Object getWrappedOAuthResponse() {
		return _response;
	}

	private Response _response;

}