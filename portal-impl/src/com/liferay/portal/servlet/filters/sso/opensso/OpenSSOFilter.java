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

package com.liferay.portal.servlet.filters.sso.opensso;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Prashant Dighe
 * @author Hugo Huijser
 */
public class OpenSSOFilter extends BasePortalFilter {

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		try {
			long companyId = PortalUtil.getCompanyId(request);

			boolean enabled = PrefsPropsUtil.getBoolean(
				companyId, PropsKeys.OPEN_SSO_AUTH_ENABLED,
				PropsValues.OPEN_SSO_AUTH_ENABLED);
			String loginUrl = PrefsPropsUtil.getString(
				companyId, PropsKeys.OPEN_SSO_LOGIN_URL,
				PropsValues.OPEN_SSO_LOGIN_URL);
			String logoutUrl = PrefsPropsUtil.getString(
				companyId, PropsKeys.OPEN_SSO_LOGOUT_URL,
				PropsValues.OPEN_SSO_LOGOUT_URL);
			String serviceUrl = PrefsPropsUtil.getString(
				companyId, PropsKeys.OPEN_SSO_SERVICE_URL,
				PropsValues.OPEN_SSO_SERVICE_URL);

			if (enabled && Validator.isNotNull(loginUrl) &&
				Validator.isNotNull(logoutUrl) &&
				Validator.isNotNull(serviceUrl)) {

				return true;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		long companyId = PortalUtil.getCompanyId(request);

		String loginUrl = PrefsPropsUtil.getString(
			companyId, PropsKeys.OPEN_SSO_LOGIN_URL,
			PropsValues.OPEN_SSO_LOGIN_URL);
		String logoutUrl = PrefsPropsUtil.getString(
			companyId, PropsKeys.OPEN_SSO_LOGOUT_URL,
			PropsValues.OPEN_SSO_LOGOUT_URL);
		String serviceUrl = PrefsPropsUtil.getString(
			companyId, PropsKeys.OPEN_SSO_SERVICE_URL,
			PropsValues.OPEN_SSO_SERVICE_URL);

		String requestURI = GetterUtil.getString(request.getRequestURI());

		if (requestURI.endsWith("/portal/logout")) {
			HttpSession session = request.getSession();

			session.invalidate();

			response.sendRedirect(logoutUrl);

			return;
		}

		boolean authenticated = false;

		try {

			// LEP-5943

			authenticated = OpenSSOUtil.isAuthenticated(request, serviceUrl);
		}
		catch (Exception e) {
			_log.error(e, e);

			processFilter(OpenSSOFilter.class, request, response, filterChain);

			return;
		}

		HttpSession session = request.getSession();

		if (authenticated) {

			// LEP-5943

			String newSubjectId = OpenSSOUtil.getSubjectId(request, serviceUrl);

			String oldSubjectId = (String)session.getAttribute(_SUBJECT_ID_KEY);

			if (oldSubjectId == null) {
				session.setAttribute(_SUBJECT_ID_KEY, newSubjectId);
			}
			else if (!newSubjectId.equals(oldSubjectId)) {
				session.invalidate();

				session = request.getSession();

				session.setAttribute(_SUBJECT_ID_KEY, newSubjectId);
			}

			processFilter(OpenSSOFilter.class, request, response, filterChain);

			return;
		}
		else if (PortalUtil.getUserId(request) > 0) {
			session.invalidate();
		}

		if (!PropsValues.AUTH_FORWARD_BY_LAST_PATH ||
			!loginUrl.contains("/portal/login")) {

			response.sendRedirect(loginUrl);

			return;
		}

		String currentURL = PortalUtil.getCurrentURL(request);

		String redirect = currentURL;

		if (currentURL.contains("/portal/login")) {
			redirect = ParamUtil.getString(request, "redirect");

			if (Validator.isNull(redirect)) {
				redirect = PortalUtil.getPathMain();
			}
		}

		redirect =
			loginUrl +
				HttpUtil.encodeURL("?redirect=" + HttpUtil.encodeURL(redirect));

		response.sendRedirect(redirect);
	}

	private static final String _SUBJECT_ID_KEY = "open.sso.subject.id";

	private static Log _log = LogFactoryUtil.getLog(OpenSSOFilter.class);

}