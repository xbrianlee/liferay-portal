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

import com.liferay.portal.kernel.io.OutputStreamWriter;
import com.liferay.portal.kernel.servlet.taglib.aui.ScriptData;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;
import java.io.OutputStream;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.RenderFilter;
import javax.portlet.filter.ResourceFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shuyang Zhou
 * @author Bruno Basto
 * @author Eduardo Lundgren
 */
public class ScriptDataPortletFilter implements RenderFilter, ResourceFilter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(
			RenderRequest renderRequest, RenderResponse renderResponse,
			FilterChain filterChain)
		throws IOException, PortletException {

		filterChain.doFilter(renderRequest, renderResponse);

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			renderRequest);

		ScriptData scriptData = (ScriptData)request.getAttribute(
			WebKeys.AUI_SCRIPT_DATA);

		if (scriptData == null) {
			return;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (themeDisplay.isIsolated() || themeDisplay.isStateExclusive()) {
			_flushScriptData(
				request, scriptData, (MimeResponseImpl)renderResponse);
		}
	}

	@Override
	public void doFilter(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			FilterChain filterChain)
		throws IOException, PortletException {

		filterChain.doFilter(resourceRequest, resourceResponse);

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			resourceRequest);

		ScriptData scriptData = (ScriptData)request.getAttribute(
			WebKeys.AUI_SCRIPT_DATA);

		if (scriptData == null) {
			return;
		}

		_flushScriptData(
			request, scriptData, (MimeResponseImpl)resourceResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	private void _flushScriptData(
			HttpServletRequest request, ScriptData scriptData,
			MimeResponseImpl mimeResponseImpl)
		throws IOException {

		if (mimeResponseImpl.isCalledGetPortletOutputStream()) {
			OutputStream outputStream =
				mimeResponseImpl.getPortletOutputStream();

			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
				outputStream);

			scriptData.writeTo(request, outputStreamWriter);

			outputStreamWriter.flush();
		}
		else {
			scriptData.writeTo(request, mimeResponseImpl.getWriter());
		}
	}

}