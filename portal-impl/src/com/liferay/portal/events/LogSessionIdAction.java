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

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class LogSessionIdAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Session id " + session.getId() + " is " +
					(!session.isNew() ? "not " : "") + "new");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LogSessionIdAction.class);

}