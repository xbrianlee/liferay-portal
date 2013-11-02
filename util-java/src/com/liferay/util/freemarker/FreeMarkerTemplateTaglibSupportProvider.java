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

package com.liferay.util.freemarker;

import com.liferay.portal.kernel.servlet.PortletServlet;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateTaglibSupportProvider;
import com.liferay.portal.util.PortalUtil;

import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.ext.servlet.ServletContextHashModel;

import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateHashModel;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Raymond Augé
 */
public class FreeMarkerTemplateTaglibSupportProvider
	implements TemplateTaglibSupportProvider {

	@Override
	public void addTaglibSupport(
			Template template, String servletContextName,
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		template.prepare(request);

		template.put(
			"fullTemplatesPath", servletContextName.concat(
				TemplateConstants.SERVLET_SEPARATOR));

		ServletConfig servletConfig =
			(ServletConfig)portletRequest.getAttribute(
				PortletServlet.PORTLET_SERVLET_CONFIG);

		PortletServlet portletServlet = new PortletServlet();

		portletServlet.init(servletConfig);

		ServletContextHashModel servletContextHashModel =
			new ServletContextHashModel(
				portletServlet, ObjectWrapper.DEFAULT_WRAPPER);

		template.put("Application", servletContextHashModel);

		ServletContext servletContext = ServletContextPool.get(
			servletContextName);

		TemplateHashModel taglibsFactory =
			FreeMarkerTaglibFactoryUtil.createTaglibFactory(servletContext);

		template.put("PortletJspTagLibs", taglibsFactory);

		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			portletResponse);

		HttpRequestHashModel httpRequestHashModel = new HttpRequestHashModel(
			request, response, ObjectWrapper.DEFAULT_WRAPPER);

		template.put("Request", httpRequestHashModel);
	}

}