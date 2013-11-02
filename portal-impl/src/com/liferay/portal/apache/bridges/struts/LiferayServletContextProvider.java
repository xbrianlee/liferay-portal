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

package com.liferay.portal.apache.bridges.struts;

import com.liferay.portal.kernel.portlet.LiferayPortletContext;
import com.liferay.portal.kernel.servlet.ServletContextProvider;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.util.PortalUtil;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author James Schopp
 * @author Michael Young
 * @author Deepak Gothe
 * @author Raymond Augé
 */
public class LiferayServletContextProvider implements ServletContextProvider {

	@Override
	public HttpServletRequest getHttpServletRequest(
		GenericPortlet portlet, PortletRequest portletRequest) {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		return new LiferayStrutsRequestImpl(request);
	}

	@Override
	public HttpServletResponse getHttpServletResponse(
		GenericPortlet portlet, PortletResponse portletResponse) {

		return PortalUtil.getHttpServletResponse(portletResponse);
	}

	@Override
	public ServletContext getServletContext(GenericPortlet portlet) {
		PortletContext portletContext = portlet.getPortletContext();

		ServletContext servletContext =
			(ServletContext)portletContext.getAttribute(
				JavaConstants.JAVAX_PORTLET_SERVLET_CONTEXT);

		if (servletContext == null) {
			LiferayPortletContext liferayPortletContext =
				(LiferayPortletContext)portlet.getPortletContext();

			servletContext = liferayPortletContext.getServletContext();
		}

		return getServletContext(servletContext);
	}

	@Override
	public ServletContext getServletContext(ServletContext servletContext) {
		return new LiferayServletContext(servletContext);
	}

}