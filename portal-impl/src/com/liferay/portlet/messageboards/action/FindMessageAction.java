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

import com.liferay.portal.struts.FindAction;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class FindMessageAction extends FindAction {

	@Override
	protected long getGroupId(long primaryKey) throws Exception {
		MBMessage message = MBMessageLocalServiceUtil.getMessage(primaryKey);

		return message.getGroupId();
	}

	@Override
	protected String getPrimaryKeyParameterName() {
		return "messageId";
	}

	@Override
	protected String getStrutsAction(
		HttpServletRequest request, String portletId) {

		if (portletId.equals(PortletKeys.MESSAGE_BOARDS_ADMIN)) {
			return "/message_boards_admin/view_message";
		}
		else {
			return "/message_boards/view_message";
		}
	}

	@Override
	protected String[] initPortletIds() {

		// Order is important. See LPS-23770.

		return new String[] {
			PortletKeys.MESSAGE_BOARDS_ADMIN, PortletKeys.MESSAGE_BOARDS
		};
	}

}