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

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * This action ensures that all requests are secure. Extend this and override
 * the <code>isRequiresSecure</code> method to programmatically decide when a
 * request requires HTTPS.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class SecureRequestAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		try {
			if (request.isSecure()) {
				return;
			}

			if (!isRequiresSecure(request)) {
				return;
			}

			if (response.isCommitted()) {
				return;
			}

			String redirect = getRedirect(request);

			if (_log.isDebugEnabled()) {
				_log.debug("Redirect " + redirect);
			}

			if (redirect != null) {
				response.sendRedirect(redirect);
			}
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected String getRedirect(HttpServletRequest request) {
		String unsecureCompleteURL = PortalUtil.getCurrentCompleteURL(request);

		if (_log.isDebugEnabled()) {
			_log.debug("Unsecure URL " + unsecureCompleteURL);
		}

		String secureCompleteURL = StringUtil.replaceFirst(
			unsecureCompleteURL, Http.HTTP_WITH_SLASH, Http.HTTPS_WITH_SLASH);

		if (_log.isDebugEnabled()) {
			_log.debug("Secure URL " + secureCompleteURL);
		}

		if (unsecureCompleteURL.equals(secureCompleteURL)) {
			return null;
		}
		else {
			return secureCompleteURL;
		}
	}

	protected boolean isRequiresSecure(HttpServletRequest request) {
		return _REQUIRES_SECURE;
	}

	private static final boolean _REQUIRES_SECURE = true;

	private static Log _log = LogFactoryUtil.getLog(SecureRequestAction.class);

}