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

package com.liferay.portlet.webproxy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BufferCacheServletResponse;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.struts.StrutsUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.RenderResponseImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.portletbridge.portlet.PortletBridgePortlet;

/**
 * @author Brian Wing Shun Chan
 */
public class WebProxyPortlet extends PortletBridgePortlet {

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (!_enabled) {
			printError(renderResponse);

			return;
		}

		PortletPreferences preferences = renderRequest.getPreferences();

		String initUrl = preferences.getValue("initUrl", StringPool.BLANK);

		if (Validator.isNull(initUrl)) {
			PortletContext portletContext = getPortletContext();

			PortletRequestDispatcher portletRequestDispatcher =
				portletContext.getRequestDispatcher(
					StrutsUtil.TEXT_HTML_DIR + "/portal/portlet_not_setup.jsp");

			portletRequestDispatcher.include(renderRequest, renderResponse);
		}
		else {
			super.doView(renderRequest, renderResponse);

			RenderResponseImpl renderResponseImpl =
				(RenderResponseImpl)renderResponse;

			BufferCacheServletResponse bufferCacheServletResponse =
				(BufferCacheServletResponse)
					renderResponseImpl.getHttpServletResponse();

			String output = bufferCacheServletResponse.getString();

			output = StringUtil.replace(
				output, "//pbhs/", PortalUtil.getPathContext() + "/pbhs/");

			bufferCacheServletResponse.setString(output);
		}
	}

	@Override
	public void init() {
		try {
			super.init();

			_enabled = true;
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage());
			}
		}

		if (!_enabled && ServerDetector.isWebLogic() && _log.isInfoEnabled()) {
			_log.info(
				"WebProxyPortlet will not be enabled unless Liferay's " +
					"serializer.jar and xalan.jar files are copied to the " +
						"JDK's endorsed directory");
		}
	}

	protected void printError(RenderResponse renderResponse)
		throws IOException {

		renderResponse.setContentType(ContentTypes.TEXT_HTML_UTF8);

		PrintWriter writer = renderResponse.getWriter();

		writer.print(
			"WebProxyPortlet will not be enabled unless Liferay's " +
				"serializer.jar and xalan.jar files are copied to the " +
					"JDK's endorsed directory");

		writer.close();
	}

	private static Log _log = LogFactoryUtil.getLog(WebProxyPortlet.class);

	private boolean _enabled;

}