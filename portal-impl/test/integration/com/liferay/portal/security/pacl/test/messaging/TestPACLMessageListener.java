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

package com.liferay.portal.security.pacl.test.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.PortalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.TestPropsValues;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class TestPACLMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		message.setPayload(getResults(message));

		MessageBusUtil.sendMessage(
			message.getResponseDestinationName(), message);
	}

	protected Map<String, Object> getResults(Message message) throws Exception {
		Map<String, Object> results = new HashMap<String, Object>();

		try {
			int buildNumber = PortalServiceUtil.getBuildNumber();

			results.put("PortalServiceUtil#getBuildNumber", buildNumber);
		}
		catch (SecurityException se) {
		}

		try {
			User user = UserLocalServiceUtil.getUser(
				TestPropsValues.getUserId());

			results.put("UserLocalServiceUtil#getUser", user);
		}
		catch (SecurityException se) {
		}

		return results;
	}

}