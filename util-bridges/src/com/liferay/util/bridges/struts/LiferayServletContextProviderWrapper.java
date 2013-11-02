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

import javax.portlet.GenericPortlet;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Michael Young
 */
public class LiferayServletContextProviderWrapper
	implements org.apache.portals.bridges.common.ServletContextProvider {

	@Override
	public HttpServletRequest getHttpServletRequest(
		GenericPortlet portlet, PortletRequest portletRequest) {

		ServletContextProvider provider = _getProvider(portlet);

		return provider.getHttpServletRequest(portlet, portletRequest);
	}

	@Override
	public HttpServletResponse getHttpServletResponse(
		GenericPortlet portlet, PortletResponse portletResponse) {

		ServletContextProvider provider = _getProvider(portlet);

		return provider.getHttpServletResponse(portlet, portletResponse);
	}

	@Override
	public ServletContext getServletContext(GenericPortlet portlet) {
		ServletContextProvider provider = _getProvider(portlet);

		return provider.getServletContext(portlet);
	}

	private ServletContextProvider _getProvider(GenericPortlet portlet) {
		PortletContext portletContext = portlet.getPortletContext();

		if (_provider == null) {
			_provider = (ServletContextProvider)portletContext.getAttribute(
				ServletContextProvider.STRUTS_BRIDGES_CONTEXT_PROVIDER);
		}

		return _provider;
	}

	private ServletContextProvider _provider;

}