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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.servlet.filters.secure.NonceUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.util.PortalUtil;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tomas Polesovsky
 */
public class DigestAuthenticationAuthVerifier implements AuthVerifier {

	@Override
	public String getAuthType() {
		return HttpServletRequest.DIGEST_AUTH;
	}

	@Override
	public AuthVerifierResult verify(
			AccessControlContext accessControlContext, Properties configuration)
		throws AuthException {

		try {
			AuthVerifierResult authVerifierResult = new AuthVerifierResult();

			HttpServletRequest request = accessControlContext.getRequest();

			long userId = PortalUtil.getDigestAuthUserId(request);

			if (userId == 0) {

				// Deprecated

				boolean forcedDigestAuth = MapUtil.getBoolean(
					accessControlContext.getSettings(), "digest_auth");

				if (forcedDigestAuth) {
					HttpServletResponse response =
						accessControlContext.getResponse();

					// Must generate a new nonce for each 401 (RFC2617, 3.2.1)

					long companyId = PortalInstances.getCompanyId(request);

					String remoteAddress = request.getRemoteAddr();

					String nonce = NonceUtil.generate(companyId, remoteAddress);

					StringBundler sb = new StringBundler(4);

					sb.append(_DIGEST_REALM);
					sb.append(", nonce=\"");
					sb.append(nonce);
					sb.append("\"");

					response.setHeader(
						HttpHeaders.WWW_AUTHENTICATE, sb.toString());

					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

					authVerifierResult.setState(
						AuthVerifierResult.State.INVALID_CREDENTIALS);
				}

				return authVerifierResult;
			}

			authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
			authVerifierResult.setUserId(userId);

			return authVerifierResult;
		}
		catch (PortalException pe) {
			throw new AuthException(pe);
		}
		catch (SystemException se) {
			throw new AuthException(se);
		}
	}

	private static final String _DIGEST_REALM =
		"Digest realm=\"" + Portal.PORTAL_REALM + "\"";

}