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

import com.liferay.portal.kernel.events.SessionAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.notifications.ChannelException;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import javax.servlet.http.HttpSession;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class ChannelSessionDestroyAction extends SessionAction {

	@Override
	public void run(HttpSession session) {
		User user = null;

		try {
			user = (User)session.getAttribute(WebKeys.USER);
		}
		catch (IllegalStateException ise) {
			return;
		}

		try {
			if (user == null) {
				Long userId = (Long)session.getAttribute(WebKeys.USER_ID);

				if (userId != null) {
					user = UserLocalServiceUtil.getUser(userId);
				}
			}

			if ((user == null) || user.isDefaultUser()) {
				return;
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Destroying channel " + user.getUserId());
			}

			try {
				ChannelHubManagerUtil.destroyChannel(
					user.getCompanyId(), user.getUserId());
			}
			catch (ChannelException ce) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"User channel " + user.getUserId() +
							" is already unregistered",
						ce);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ChannelSessionDestroyAction.class);

}