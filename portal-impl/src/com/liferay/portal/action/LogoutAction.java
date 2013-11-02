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

package com.liferay.portal.action;

import com.liferay.portal.events.EventsProcessorUtil;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.struts.ActionConstants;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian Wing Shun Chan
 */
public class LogoutAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		try {
			HttpSession session = request.getSession();

			EventsProcessorUtil.process(
				PropsKeys.LOGOUT_EVENTS_PRE, PropsValues.LOGOUT_EVENTS_PRE,
				request, response);

			String domain = CookieKeys.getDomain(request);

			Cookie companyIdCookie = new Cookie(
				CookieKeys.COMPANY_ID, StringPool.BLANK);

			if (Validator.isNotNull(domain)) {
				companyIdCookie.setDomain(domain);
			}

			companyIdCookie.setMaxAge(0);
			companyIdCookie.setPath(StringPool.SLASH);

			Cookie idCookie = new Cookie(CookieKeys.ID, StringPool.BLANK);

			if (Validator.isNotNull(domain)) {
				idCookie.setDomain(domain);
			}

			idCookie.setMaxAge(0);
			idCookie.setPath(StringPool.SLASH);

			Cookie passwordCookie = new Cookie(
				CookieKeys.PASSWORD, StringPool.BLANK);

			if (Validator.isNotNull(domain)) {
				passwordCookie.setDomain(domain);
			}

			passwordCookie.setMaxAge(0);
			passwordCookie.setPath(StringPool.SLASH);

			boolean rememberMe = GetterUtil.getBoolean(
				CookieKeys.getCookie(request, CookieKeys.REMEMBER_ME));

			if (!rememberMe) {
				Cookie loginCookie = new Cookie(
					CookieKeys.LOGIN, StringPool.BLANK);

				if (Validator.isNotNull(domain)) {
					loginCookie.setDomain(domain);
				}

				loginCookie.setMaxAge(0);
				loginCookie.setPath(StringPool.SLASH);

				CookieKeys.addCookie(request, response, loginCookie);
			}

			Cookie rememberMeCookie = new Cookie(
				CookieKeys.REMEMBER_ME, StringPool.BLANK);

			if (Validator.isNotNull(domain)) {
				rememberMeCookie.setDomain(domain);
			}

			rememberMeCookie.setMaxAge(0);
			rememberMeCookie.setPath(StringPool.SLASH);

			CookieKeys.addCookie(request, response, companyIdCookie);
			CookieKeys.addCookie(request, response, idCookie);
			CookieKeys.addCookie(request, response, passwordCookie);
			CookieKeys.addCookie(request, response, rememberMeCookie);

			try {
				session.invalidate();
			}
			catch (Exception e) {
			}

			EventsProcessorUtil.process(
				PropsKeys.LOGOUT_EVENTS_POST, PropsValues.LOGOUT_EVENTS_POST,
				request, response);

			request.setAttribute(WebKeys.LOGOUT, true);

			return actionMapping.findForward(ActionConstants.COMMON_REFERER);
		}
		catch (Exception e) {
			PortalUtil.sendError(e, request, response);

			return null;
		}
	}

}