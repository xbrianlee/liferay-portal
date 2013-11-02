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

package com.liferay.taglib.portletext;

import com.liferay.portal.kernel.servlet.PipingServletResponse;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

/**
 * @author Brian Wing Shun Chan
 */
public class PreviewTag extends IncludeTag {

	public static void doTag(
			String portletName, String queryString, boolean showBorders,
			String width, ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		doTag(
			_PAGE, portletName, queryString, showBorders, width, servletContext,
			request, response);
	}

	public static void doTag(
			String page, String portletName, String queryString,
			boolean showBorders, String width, ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		request.setAttribute(
			"liferay-portlet:preview:portletName", portletName);
		request.setAttribute(
			"liferay-portlet:preview:queryString", queryString);
		request.setAttribute(
			"liferay-portlet:preview:showBorders", String.valueOf(showBorders));
		request.setAttribute("liferay-portlet:preview:width", width);

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher(page);

		requestDispatcher.include(request, response);
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			doTag(
				getPage(), _portletName, _queryString, _showBorders, _width,
				servletContext, request,
				new PipingServletResponse(pageContext));

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	public void setPortletName(String portletName) {
		_portletName = portletName;
	}

	public void setQueryString(String queryString) {
		_queryString = queryString;
	}

	public void setShowBorders(boolean showBorders) {
		_showBorders = showBorders;
	}

	public void setWidth(String width) {
		_width = width;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	private static final String _PAGE = "/html/taglib/portlet/preview/page.jsp";

	private String _portletName;
	private String _queryString;
	private boolean _showBorders;
	private String _width;

}