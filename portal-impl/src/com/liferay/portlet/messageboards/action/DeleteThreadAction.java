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
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.messageboards.LockedThreadException;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBThreadServiceUtil;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Deepak Gothe
 * @author Sergio González
 * @author Zsolt Berentey
 */
public class DeleteThreadAction extends PortletAction {

	@Override
	public void processAction(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteThreads(actionRequest, false);
			}
			else if (cmd.equals(Constants.MOVE_TO_TRASH)) {
				deleteThreads(actionRequest, true);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception e) {
			if (e instanceof LockedThreadException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				setForward(actionRequest, "portlet.message_boards.error");
			}
			else {
				throw e;
			}
		}
	}

	protected void deleteThreads(
			ActionRequest actionRequest, boolean moveToTrash)
		throws Exception {

		String deleteEntryTitle = null;

		long[] deleteThreadIds = null;

		long threadId = ParamUtil.getLong(actionRequest, "threadId");

		if (threadId > 0) {
			deleteThreadIds = new long[] {threadId};
		}
		else {
			deleteThreadIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "threadIds"), 0L);
		}

		for (int i = 0; i < deleteThreadIds.length; i++) {
			long deleteThreadId = deleteThreadIds[i];

			if (moveToTrash) {
				MBThread thread = MBThreadServiceUtil.moveThreadToTrash(
					deleteThreadId);

				if (i == 0) {
					MBMessage message = MBMessageLocalServiceUtil.getMessage(
						thread.getRootMessageId());

					deleteEntryTitle = message.getSubject();
				}
			}
			else {
				MBThreadServiceUtil.deleteThread(deleteThreadId);
			}
		}

		if (moveToTrash && (deleteThreadIds.length > 0)) {
			Map<String, String[]> data = new HashMap<String, String[]>();

			data.put(
				"deleteEntryClassName",
				new String[] {MBThread.class.getName()});

			if (Validator.isNotNull(deleteEntryTitle)) {
				data.put("deleteEntryTitle", new String[] {deleteEntryTitle});
			}

			data.put(
				"restoreThreadIds", ArrayUtil.toStringArray(deleteThreadIds));

			SessionMessages.add(
				actionRequest,
				PortalUtil.getPortletId(actionRequest) +
					SessionMessages.KEY_SUFFIX_DELETE_SUCCESS_DATA, data);

			hideDefaultSuccessMessage(actionRequest);
		}
	}

}