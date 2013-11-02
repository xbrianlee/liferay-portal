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

package com.liferay.portal.struts;

import com.liferay.portal.util.WebKeys;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletActionServlet extends ActionServlet {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);

		ServletContext servletContext = getServletContext();

		ModuleConfig moduleConfig = (ModuleConfig)servletContext.getAttribute(
			Globals.MODULE_KEY);

		PortletRequestProcessor portletRequestProcessor =
			PortletRequestProcessor.getInstance(this, moduleConfig);

		servletContext.setAttribute(
			WebKeys.PORTLET_STRUTS_PROCESSOR, portletRequestProcessor);
	}

}