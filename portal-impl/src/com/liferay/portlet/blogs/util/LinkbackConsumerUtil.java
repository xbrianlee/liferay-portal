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

package com.liferay.portlet.blogs.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Alexander Chow
 */
public class LinkbackConsumerUtil {

	public static void addNewTrackback(
		long messageId, String url, String entryUrl) {

		_trackbacks.add(new Tuple(messageId, url, entryUrl));
	}

	public static void verifyNewTrackbacks() {
		Tuple tuple = null;

		while (!_trackbacks.isEmpty()) {
			synchronized (_trackbacks) {
				tuple = _trackbacks.remove(0);
			}

			long messageId = (Long)tuple.getObject(0);
			String url = (String)tuple.getObject(1);
			String entryUrl = (String)tuple.getObject(2);

			_verifyTrackback(messageId, url, entryUrl);
		}
	}

	public static void verifyPost(BlogsEntry entry, MBMessage message)
		throws Exception {

		long messageId = message.getMessageId();
		String entryURL =
			Portal.FRIENDLY_URL_SEPARATOR + "blogs/" + entry.getUrlTitle();
		String body = message.getBody();
		String url = null;

		int start = body.indexOf("[url=");

		if (start > -1) {
			start += "[url=".length();

			int end = body.indexOf("]", start);

			if (end > -1) {
				url = body.substring(start, end);
			}
		}

		if (Validator.isNotNull(url)) {
			long companyId = message.getCompanyId();
			long userId = message.getUserId();
			long defaultUserId = UserLocalServiceUtil.getDefaultUserId(
				companyId);

			if (userId == defaultUserId) {
				_verifyTrackback(messageId, url, entryURL);
			}
		}
	}

	private static void _verifyTrackback(
		long messageId, String url, String entryURL) {

		try {
			String result = HttpUtil.URLtoString(url);

			if (result.contains(entryURL)) {
				return;
			}
		}
		catch (Exception e) {
		}

		try {
			MBMessageLocalServiceUtil.deleteDiscussionMessage(messageId);
		}
		catch (Exception e) {
			_log.error(
				"Error trying to delete trackback message " + messageId, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LinkbackConsumerUtil.class);

	private static List<Tuple> _trackbacks = Collections.synchronizedList(
		new ArrayList<Tuple>());

}