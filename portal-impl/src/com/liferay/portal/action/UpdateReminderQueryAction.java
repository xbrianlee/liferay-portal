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

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.UserReminderQueryException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.AuthTokenUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.struts.ActionConstants;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.usersadmin.util.UsersAdmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian Wing Shun Chan
 */
public class UpdateReminderQueryAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String cmd = ParamUtil.getString(request, Constants.CMD);

		if (Validator.isNull(cmd)) {
			return actionMapping.findForward("portal.update_reminder_query");
		}

		try {
			updateReminderQuery(request, response);

			return actionMapping.findForward(
				ActionConstants.COMMON_REFERER_JSP);
		}
		catch (Exception e) {
			if (e instanceof UserReminderQueryException) {
				SessionErrors.add(request, e.getClass());

				return actionMapping.findForward(
					"portal.update_reminder_query");
			}
			else if (e instanceof NoSuchUserException ||
					 e instanceof PrincipalException) {

				SessionErrors.add(request, e.getClass());

				return actionMapping.findForward("portal.error");
			}

			PortalUtil.sendError(e, request, response);

			return null;
		}
	}

	protected void updateReminderQuery(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		AuthTokenUtil.checkCSRFToken(
			request, UpdateReminderQueryAction.class.getName());

		long userId = PortalUtil.getUserId(request);
		String question = ParamUtil.getString(request, "reminderQueryQuestion");
		String answer = ParamUtil.getString(request, "reminderQueryAnswer");

		if (question.equals(UsersAdmin.CUSTOM_QUESTION)) {
			question = ParamUtil.getString(
				request, "reminderQueryCustomQuestion");
		}

		UserServiceUtil.updateReminderQuery(userId, question, answer);
	}

}