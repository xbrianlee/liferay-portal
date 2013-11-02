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

package com.liferay.portlet.messageboards.action;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.messageboards.NoSuchMessageException;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageDisplay;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian Wing Shun Chan
 */
public class ViewMessageAction extends PortletAction {

	@Override
	public ActionForward render(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		try {
			long messageId = ParamUtil.getLong(renderRequest, "messageId");

			PortalPreferences preferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					renderRequest);

			String threadView = ParamUtil.getString(
				renderRequest, "threadView");

			if (Validator.isNotNull(threadView)) {
				preferences.setValue(
					PortletKeys.MESSAGE_BOARDS, "thread-view", threadView);
			}
			else {
				threadView = preferences.getValue(
					PortletKeys.MESSAGE_BOARDS, "thread-view",
					PropsValues.MESSAGE_BOARDS_THREAD_VIEWS_DEFAULT);
			}

			if (!ArrayUtil.contains(
					PropsValues.MESSAGE_BOARDS_THREAD_VIEWS, threadView)) {

				threadView = PropsValues.MESSAGE_BOARDS_THREAD_VIEWS_DEFAULT;

				preferences.setValue(
					PortletKeys.MESSAGE_BOARDS, "thread-view", threadView);
			}

			boolean includePrevAndNext =
				PropsValues.
					MESSAGE_BOARDS_THREAD_PREVIOUS_AND_NEXT_NAVIGATION_ENABLED;

			MBMessageDisplay messageDisplay =
				MBMessageServiceUtil.getMessageDisplay(
					messageId, WorkflowConstants.STATUS_ANY, threadView,
					includePrevAndNext);

			if (messageDisplay != null) {
				MBMessage message = messageDisplay.getMessage();

				if ((message != null) && message.isInTrash()) {
					throw new NoSuchMessageException();
				}
			}

			renderRequest.setAttribute(
				WebKeys.MESSAGE_BOARDS_MESSAGE, messageDisplay);

			return actionMapping.findForward(
				"portlet.message_boards.view_message");
		}
		catch (Exception e) {
			if (e instanceof NoSuchMessageException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());

				return actionMapping.findForward(
					"portlet.message_boards.error");
			}
			else {
				throw e;
			}
		}
	}

}