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

package com.liferay.portal.facebook;

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BufferCacheServletResponse;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.servlet.filters.gzip.GZipFilter;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.social.util.FacebookUtil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class FacebookServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		try {
			String[] facebookData = FacebookUtil.getFacebookData(request);

			if ((facebookData == null) ||
				!PortalUtil.isValidResourceId(facebookData[1])) {

				PortalUtil.sendError(
					HttpServletResponse.SC_NOT_FOUND,
					new NoSuchLayoutException(), request, response);
			}
			else {
				String facebookCanvasPageURL = facebookData[0];
				String redirect = facebookData[1];

				request.setAttribute(
					WebKeys.FACEBOOK_CANVAS_PAGE_URL, facebookCanvasPageURL);
				request.setAttribute(GZipFilter.SKIP_FILTER, Boolean.TRUE);

				ServletContext servletContext = getServletContext();

				RequestDispatcher requestDispatcher =
					servletContext.getRequestDispatcher(redirect);

				BufferCacheServletResponse bufferCacheServletResponse =
					new BufferCacheServletResponse(response);

				requestDispatcher.forward(request, bufferCacheServletResponse);

				String fbml = bufferCacheServletResponse.getString();

				fbml = fixFbml(fbml);

				ServletResponseUtil.write(response, fbml);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			PortalUtil.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e, request,
				response);
		}
	}

	protected String fixFbml(String fbml) {
		fbml = StringUtil.replace(
			fbml,
			new String[] {
				"<nobr>", "</nobr>"
			},
			new String[] {
				StringPool.BLANK, StringPool.BLANK
			});

		return fbml;
	}

	private static Log _log = LogFactoryUtil.getLog(FacebookServlet.class);

}