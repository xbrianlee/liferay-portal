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

package com.liferay.portal.poller;

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.poller.PollerHeader;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class PollerServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		try {
			String content = getContent(request);

			if (content == null) {
				PortalUtil.sendError(
					HttpServletResponse.SC_NOT_FOUND,
					new NoSuchLayoutException(), request, response);
			}
			else {
				response.setContentType(ContentTypes.TEXT_PLAIN_UTF8);

				ServletResponseUtil.write(
					response, content.getBytes(StringPool.UTF8));
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			PortalUtil.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e, request,
				response);
		}
	}

	protected String getContent(HttpServletRequest request) throws Exception {
		long companyId = PortalUtil.getCompanyId(request);
		long userId = PortalUtil.getUserId(request);

		if (userId == 0) {
			return StringPool.BLANK;
		}

		String pollerRequestString = ParamUtil.getString(
			request, "pollerRequest");

		PollerHeader pollerHeader = PollerRequestHandlerUtil.getPollerHeader(
			pollerRequestString);

		if (pollerHeader == null) {
			return StringPool.BLANK;
		}

		if (userId != pollerHeader.getUserId()) {
			return StringPool.BLANK;
		}

		JSONObject pollerResponseHeaderJSONObject =
			PollerRequestHandlerUtil.processRequest(
				request, pollerRequestString);

		if (pollerResponseHeaderJSONObject == null) {
			return StringPool.BLANK;
		}

		SynchronousPollerChannelListener synchronousPollerChannelListener =
			new SynchronousPollerChannelListener(
				companyId, userId, pollerResponseHeaderJSONObject);

		return synchronousPollerChannelListener.getNotificationEvents(
			PropsValues.POLLER_REQUEST_TIMEOUT);
	}

	private static Log _log = LogFactoryUtil.getLog(PollerServlet.class);

}