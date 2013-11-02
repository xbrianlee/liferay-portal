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

package com.liferay.portlet;

import com.liferay.portal.model.Layout;
import com.liferay.portal.model.User;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class EventResponseFactory {

	public static EventResponseImpl create(
			EventRequestImpl eventRequestImpl, HttpServletResponse response,
			String portletName, User user, Layout layout)
		throws Exception {

		EventResponseImpl eventResponseImpl = new EventResponseImpl();

		eventResponseImpl.init(
			eventRequestImpl, response, portletName, user, layout);

		return eventResponseImpl;
	}

}