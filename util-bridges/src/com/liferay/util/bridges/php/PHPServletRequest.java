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

package com.liferay.util.bridges.php;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Enumeration;
import java.util.Map;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Jorge Ferrer
 */
public class PHPServletRequest extends HttpServletRequestWrapper {

	public PHPServletRequest(
		HttpServletRequest request, ServletConfig servletConfig,
		RenderRequest renderRequest, RenderResponse renderResponse,
		PortletConfig portletConfig, String phpURI, boolean addPortletParams) {

		super(request);

		_servletConfig = servletConfig;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_portletConfig = portletConfig;

		StringBundler sb = new StringBundler();

		int pos = phpURI.indexOf(CharPool.QUESTION);

		if (pos != -1) {
			_path = phpURI.substring(0, pos);

			sb.append(phpURI.substring(pos + 1));
		}
		else {
			_path = phpURI;
		}

		if (addPortletParams) {
			sb.append(StringPool.AMPERSAND);
			sb.append("portlet_namespace");
			sb.append(StringPool.EQUAL);
			sb.append(_renderResponse.getNamespace());
			sb.append(StringPool.AMPERSAND);
			sb.append("portlet_name");
			sb.append(StringPool.EQUAL);
			sb.append(_portletConfig.getPortletName());
		}

		_queryString = sb.toString();

		request.setAttribute(
			JavaConstants.JAVAX_SERVLET_INCLUDE_QUERY_STRING, getQueryString());
		request.setAttribute(
			JavaConstants.JAVAX_SERVLET_INCLUDE_PATH_INFO, getPathInfo());
		request.setAttribute(
			JavaConstants.JAVAX_SERVLET_INCLUDE_REQUEST_URI, getRequestURI());
		request.setAttribute(
			JavaConstants.JAVAX_SERVLET_INCLUDE_SERVLET_PATH, _path);
	}

	@Override
	public String getContextPath() {
		return StringPool.SLASH;
	}

	@Override
	public String getParameter(String name) {
		return _renderRequest.getParameter(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return _renderRequest.getParameterMap();
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return _renderRequest.getParameterNames();
	}

	@Override
	public String[] getParameterValues(String name) {
		return _renderRequest.getParameterValues(name);
	}

	@Override
	public String getPathInfo() {
		return StringPool.BLANK;
	}

	@Override
	public String getPathTranslated() {
		return StringPool.BLANK;
	}

	@Override
	public String getQueryString() {
		return _queryString;
	}

	@Override
	public String getRealPath(String path) {
		return _servletConfig.getServletContext().getRealPath(path);
	}

	@Override
	public String getRequestURI() {
		return _path + StringPool.QUESTION + _queryString;
	}

	@Override
	public StringBuffer getRequestURL() {
		StringBuffer sb = new StringBuffer();

		sb.append(getRequest().getProtocol());
		sb.append("://");
		sb.append(getRequest().getRemoteHost());
		sb.append(StringPool.COLON);
		sb.append(getRequest().getServerPort());
		sb.append(StringPool.SLASH);
		sb.append(getRequestURI());

		return sb;
	}

	@Override
	public String getServletPath() {
		return _path;
	}

	private String _path;
	private PortletConfig _portletConfig;
	private String _queryString;
	private RenderRequest _renderRequest;
	private RenderResponse _renderResponse;
	private ServletConfig _servletConfig;

}