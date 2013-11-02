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

package com.liferay.util.bridges.struts;

import com.liferay.portal.kernel.servlet.ServletContextProvider;

import javax.servlet.ServletContext;

import org.apache.portals.bridges.struts.PortletServlet;

/**
 * @author Michael Young
 */
public class LiferayPortletServlet extends PortletServlet {

	@Override
	public ServletContext getServletContext() {
		ServletContext servletContext = super.getServletContext();

		ServletContextProvider servletContextProvider =
			(ServletContextProvider)servletContext.getAttribute(
				ServletContextProvider.STRUTS_BRIDGES_CONTEXT_PROVIDER);

		if (servletContextProvider != null) {
			servletContext = servletContextProvider.getServletContext(
				servletContext);
		}

		return servletContext;
	}

}