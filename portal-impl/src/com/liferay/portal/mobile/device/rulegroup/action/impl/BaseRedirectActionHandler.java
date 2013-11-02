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

package com.liferay.portal.mobile.device.rulegroup.action.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mobile.device.rulegroup.action.ActionHandler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.mobiledevicerules.model.MDRAction;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Edward Han
 */
public abstract class BaseRedirectActionHandler implements ActionHandler {

	@Override
	public void applyAction(
			MDRAction mdrAction, HttpServletRequest request,
			HttpServletResponse response)
		throws PortalException, SystemException {

		String url = getURL(mdrAction, request, response);

		if (Validator.isNull(url)) {
			if (_log.isInfoEnabled()) {
				_log.info("URL is null");
			}

			return;
		}

		String requestURL = String.valueOf(request.getRequestURL());

		if (StringUtil.contains(requestURL, url)) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Skipping redirect. Current URL contains redirect URL.");
			}

			return;
		}

		try {
			response.sendRedirect(url);
		}
		catch (IOException ioe) {
			throw new PortalException("Unable to redirect to " + url, ioe);
		}
	}

	protected abstract String getURL(
			MDRAction mdrAction, HttpServletRequest request,
			HttpServletResponse response)
		throws PortalException, SystemException;

	private static Log _log = LogFactoryUtil.getLog(
		BaseRedirectActionHandler.class);

}