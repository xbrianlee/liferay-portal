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

package com.liferay.portal.kernel.servlet;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Deepak Gothe
 */
public class PortletServletObjectsFactory implements ServletObjectsFactory {

	@Override
	public ServletConfig getServletConfig(
		PortletConfig portletConfig, PortletRequest portletRequest) {

		Object servletConfig = portletConfig.getPortletContext().getAttribute(
			_PORTLET_CONTAINER_SERVLET_CONFIG);

		if (servletConfig == null) {
			servletConfig = portletRequest.getAttribute(
				PortletServlet.PORTLET_SERVLET_CONFIG);
		}

		return (ServletConfig)servletConfig;
	}

	@Override
	public HttpServletRequest getServletRequest(PortletRequest portletRequest) {
		Object request = portletRequest.getAttribute(
			_PORTLET_CONTAINER_SERVLET_REQUEST);

		if (request == null) {
			request = portletRequest.getAttribute(
				PortletServlet.PORTLET_SERVLET_REQUEST);
		}

		return (HttpServletRequest)request;
	}

	@Override
	public HttpServletResponse getServletResponse(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Object response = portletRequest.getAttribute(
			_PORTLET_CONTAINER_SERVLET_RESPONSE);

		if (response == null) {
			response = portletRequest.getAttribute(
				PortletServlet.PORTLET_SERVLET_RESPONSE);
		}

		return (HttpServletResponse)response;
	}

	private static final String _PORTLET_CONTAINER_SERVLET_CONFIG =
		"javax.portlet.portletc.servletConfig";

	private static final String _PORTLET_CONTAINER_SERVLET_REQUEST =
		"javax.portlet.portletc.httpServletRequest";

	private static final String _PORTLET_CONTAINER_SERVLET_RESPONSE =
		"javax.portlet.portletc.httpServletResponse";

}