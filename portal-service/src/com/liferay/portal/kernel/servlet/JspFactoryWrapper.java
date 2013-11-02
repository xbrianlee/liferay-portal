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

import com.liferay.portal.kernel.util.ContextPathUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.JspEngineInfo;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

/**
 * @author Shuyang Zhou
 */
public class JspFactoryWrapper extends JspFactory {

	public JspFactoryWrapper(JspFactory jspFactory) {
		_jspFactory = jspFactory;
	}

	@Override
	public JspEngineInfo getEngineInfo() {
		return _jspFactory.getEngineInfo();
	}

	@Override
	public JspApplicationContext getJspApplicationContext(
		ServletContext servletContext) {

		return _jspFactory.getJspApplicationContext(servletContext);
	}

	@Override
	public PageContext getPageContext(
		Servlet servlet, ServletRequest servletRequest,
		ServletResponse servletResponse, String errorPageURL,
		boolean needsSession, int buffer, boolean autoflush) {

		if (autoflush) {
			buffer = _JSP_WRITER_BUFFER_SIZE;
		}

		PageContext pageContext = _jspFactory.getPageContext(
			servlet, servletRequest, servletResponse, errorPageURL,
			needsSession, buffer, autoflush);

		if (_DIRECT_SERVLET_CONTEXT_ENABLED) {
			String servletPath = (String)servletRequest.getAttribute(
				WebKeys.SERVLET_PATH);

			if (servletPath != null) {
				servletRequest.removeAttribute(WebKeys.SERVLET_PATH);

				ServletContext servletContext = pageContext.getServletContext();

				String contextPath = ContextPathUtil.getContextPath(
					servletContext);

				DirectServletRegistryUtil.putServlet(
					contextPath.concat(servletPath), servlet);
			}
		}

		return new PageContextWrapper(pageContext);
	}

	@Override
	public void releasePageContext(PageContext pageContext) {
		if (pageContext instanceof PageContextWrapper) {
			PageContextWrapper pageContextWrapper =
				(PageContextWrapper)pageContext;

			pageContext = pageContextWrapper.getWrappedPageContext();
		}

		_jspFactory.releasePageContext(pageContext);
	}

	private static final boolean _DIRECT_SERVLET_CONTEXT_ENABLED =
		GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.DIRECT_SERVLET_CONTEXT_ENABLED));

	private static final int _JSP_WRITER_BUFFER_SIZE = GetterUtil.getInteger(
		PropsUtil.get(PropsKeys.JSP_WRITER_BUFFER_SIZE));

	private JspFactory _jspFactory;

}