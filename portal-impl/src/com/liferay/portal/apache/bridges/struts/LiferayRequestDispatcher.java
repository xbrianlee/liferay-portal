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

import com.liferay.portal.kernel.servlet.DynamicServletRequest;
import com.liferay.portal.kernel.servlet.PipingServletResponse;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portlet.PortletRequestImpl;
import com.liferay.portlet.PortletResponseImpl;
import com.liferay.portlet.PortletServletRequest;
import com.liferay.portlet.PortletServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Michael Young
 * @author Brian Myunghun Kim
 * @author Brian Wing Shun Chan
 * @author Deepak Gothe
 */
public class LiferayRequestDispatcher implements RequestDispatcher {

	public LiferayRequestDispatcher(
		RequestDispatcher requestDispatcher, String path) {

		_requestDispatcher = requestDispatcher;
		_path = path;
	}

	@Override
	public void forward(
			ServletRequest servletRequest, ServletResponse servletResponse)
		throws IOException, ServletException {

		PortletRequest portletRequest =
			(PortletRequest)servletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_REQUEST);

		if (portletRequest != null) {
			invoke(servletRequest, servletResponse, false);
		}
		else {
			_requestDispatcher.forward(servletRequest, servletResponse);
		}
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #forward(ServletRequest,
	 *             ServletResponse)}
	 */
	public void forward(
			ServletRequest servletRequest, ServletResponse servletResponse,
			boolean named)
		throws IOException, ServletException {

		forward(servletRequest, servletResponse);
	}

	@Override
	public void include(
			ServletRequest servletRequest, ServletResponse servletResponse)
		throws IOException, ServletException {

		PortletRequest portletRequest =
			(PortletRequest)servletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_REQUEST);

		if (portletRequest != null) {
			invoke(servletRequest, servletResponse, true);
		}
		else {
			_requestDispatcher.include(servletRequest, servletResponse);
		}
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #include(ServletRequest,
	 *             ServletResponse)}
	 */
	public void include(
			ServletRequest servletRequest, ServletResponse servletResponse,
			boolean named)
		throws IOException, ServletException {

		include(servletRequest, servletResponse);
	}

	public void invoke(
			ServletRequest servletRequest, ServletResponse servletResponse,
			boolean include)
		throws IOException, ServletException {

		String pathInfo = null;
		String queryString = null;
		String requestURI = null;
		String servletPath = null;

		PortletRequest portletRequest =
			(PortletRequest)servletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_REQUEST);

		PortletResponse portletResponse =
			(PortletResponse)servletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_RESPONSE);

		if (_path != null) {
			String pathNoQueryString = _path;

			int pos = _path.indexOf(CharPool.QUESTION);

			if (pos != -1) {
				pathNoQueryString = _path.substring(0, pos);
				queryString = _path.substring(pos + 1);

				servletRequest = DynamicServletRequest.addQueryString(
					(HttpServletRequest)servletRequest, queryString);
			}

			Set<String> servletURLPatterns = getServletURLPatterns(
				servletRequest, portletRequest, portletResponse);

			for (String urlPattern : servletURLPatterns) {
				if (urlPattern.endsWith("/*")) {
					pos = urlPattern.indexOf("/*");

					urlPattern = urlPattern.substring(0, pos + 1);

					if (pathNoQueryString.startsWith(urlPattern)) {
						pathInfo = pathNoQueryString.substring(
							urlPattern.length());
						servletPath = urlPattern;

						break;
					}
				}
			}

			if ((pathInfo == null) && (servletPath == null)) {
				pathInfo = StringPool.BLANK;
				servletPath = pathNoQueryString;
			}

			requestURI = portletRequest.getContextPath() + pathNoQueryString;
		}

		HttpServletRequest portletServletRequest = getPortletServletRequest(
			servletRequest, portletRequest, pathInfo, queryString, requestURI,
			servletPath, include);

		HttpServletResponse portletServletResponse = getPortletServletResponse(
			servletResponse, portletRequest, portletResponse, include);

		if (include) {
			_requestDispatcher.include(
				portletServletRequest, portletServletResponse);
		}
		else {
			_requestDispatcher.forward(
				portletServletRequest, portletServletResponse);
		}
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #invoke(ServletRequest,
	 *             ServletResponse, boolean)}
	 */
	public void invoke(
			ServletRequest servletRequest, ServletResponse servletResponse,
			boolean named, boolean include)
		throws IOException, ServletException {

		invoke(servletRequest, servletResponse, include);
	}

	protected HttpServletRequest getPortletServletRequest(
		ServletRequest servletRequest, PortletRequest portletRequest,
		String pathInfo, String queryString, String requestURI,
		String servletPath, boolean include) {

		HttpServletRequest request = (HttpServletRequest)servletRequest;
		boolean named = false;

		PortletRequestImpl portletRequestImpl =
			PortletRequestImpl.getPortletRequestImpl(portletRequest);

		return new PortletServletRequest(
			request, portletRequestImpl, pathInfo, queryString, requestURI,
			servletPath, named, include);
	}

	protected HttpServletResponse getPortletServletResponse(
			ServletResponse servletResponse, PortletRequest portletRequest,
			PortletResponse portletResponse, boolean include)
		throws IOException {

		HttpServletResponse response = (HttpServletResponse)servletResponse;

		PortletResponseImpl portletResponseImpl =
			(PortletResponseImpl)portletResponse;

		HttpServletResponse httpServletResponse = new PortletServletResponse(
			response, portletResponseImpl, include);

		PrintWriter printWriter = servletResponse.getWriter();

		if (printWriter != null) {
			httpServletResponse = new PipingServletResponse(
				httpServletResponse, printWriter);
		}

		return httpServletResponse;
	}

	protected Set<String> getServletURLPatterns(
		ServletRequest servletRequest, PortletRequest portletRequest,
		PortletResponse portletResponse) {

		PortletRequestImpl portletRequestImpl =
			PortletRequestImpl.getPortletRequestImpl(portletRequest);

		Portlet portlet = portletRequestImpl.getPortlet();

		PortletApp portletApp = portlet.getPortletApp();

		return portletApp.getServletURLPatterns();
	}

	private String _path;
	private RequestDispatcher _requestDispatcher;

}