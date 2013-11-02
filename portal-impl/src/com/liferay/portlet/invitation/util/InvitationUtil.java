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

package com.liferay.portlet.invitation.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsUtil;
import com.liferay.util.ContentUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 */
public class InvitationUtil {

	public static String getEmailMessageBody(PortletPreferences preferences) {
		String emailMessageBody = preferences.getValue(
			"emailMessageBody", StringPool.BLANK);

		if (Validator.isNotNull(emailMessageBody)) {
			return emailMessageBody;
		}
		else {
			return ContentUtil.get(
				PropsUtil.get(PropsKeys.INVITATION_EMAIL_MESSAGE_BODY));
		}
	}

	public static int getEmailMessageMaxRecipients() {
		return GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.INVITATION_EMAIL_MAX_RECIPIENTS));
	}

	public static String getEmailMessageSubject(
		PortletPreferences preferences) {

		String emailMessageSubject = preferences.getValue(
			"emailMessageSubject", StringPool.BLANK);

		if (Validator.isNotNull(emailMessageSubject)) {
			return emailMessageSubject;
		}
		else {
			return ContentUtil.get(
				PropsUtil.get(PropsKeys.INVITATION_EMAIL_MESSAGE_SUBJECT));
		}
	}

}