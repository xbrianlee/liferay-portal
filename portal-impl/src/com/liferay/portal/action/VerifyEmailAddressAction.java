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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AuthTokenUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.struts.ActionConstants;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletURLImpl;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Mika Koivisto
 */
public class VerifyEmailAddressAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(request, Constants.CMD);

		if (Validator.isNull(cmd)) {
			return actionMapping.findForward("portal.verify_email_address");
		}

		if (themeDisplay.isSignedIn() && cmd.equals(Constants.SEND)) {
			sendEmailAddressVerification(request, response, themeDisplay);

			return actionMapping.findForward("portal.verify_email_address");
		}

		try {
			verifyEmailAddress(request, response, themeDisplay);

			if (!themeDisplay.isSignedIn()) {
				PortletURL portletURL = new PortletURLImpl(
					request, PortletKeys.LOGIN, themeDisplay.getPlid(),
					PortletRequest.RENDER_PHASE);

				response.sendRedirect(portletURL.toString());

				return null;
			}
			else {
				return actionMapping.findForward(
					ActionConstants.COMMON_REFERER_JSP);
			}
		}
		catch (Exception e) {
			if (e instanceof PortalException || e instanceof SystemException) {
				SessionErrors.add(request, e.getClass());

				return actionMapping.findForward("portal.verify_email_address");
			}

			PortalUtil.sendError(e, request, response);

			return null;
		}
	}

	protected void sendEmailAddressVerification(
			HttpServletRequest request, HttpServletResponse response,
			ThemeDisplay themeDisplay)
		throws Exception {

		User user = themeDisplay.getUser();

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			request);

		UserLocalServiceUtil.sendEmailAddressVerification(
			user, user.getEmailAddress(), serviceContext);
	}

	protected void verifyEmailAddress(
			HttpServletRequest request, HttpServletResponse response,
			ThemeDisplay themeDisplay)
		throws Exception {

		AuthTokenUtil.checkCSRFToken(
			request, VerifyEmailAddressAction.class.getName());

		String ticketKey = ParamUtil.getString(request, "ticketKey");

		UserLocalServiceUtil.verifyEmailAddress(ticketKey);
	}

}