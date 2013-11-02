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

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class FindThreadAction extends FindMessageAction {

	@Override
	protected long getGroupId(long primaryKey) throws Exception {
		MBThread thread = MBThreadLocalServiceUtil.getThread(primaryKey);

		return thread.getGroupId();
	}

	@Override
	protected String getPrimaryKeyParameterName() {
		return "threadId";
	}

	@Override
	protected String getStrutsAction(
		HttpServletRequest request, String portletId) {

		return "/message_boards/view_message";
	}

	@Override
	protected PortletURL processPortletURL(
			HttpServletRequest request, PortletURL portletURL)
		throws Exception {

		long threadId = ParamUtil.getLong(
			request, getPrimaryKeyParameterName());

		MBThread thread = MBThreadLocalServiceUtil.getThread(threadId);

		portletURL.setParameter(
			"messageId", String.valueOf(thread.getRootMessageId()));

		return portletURL;
	}

}