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

package com.liferay.taglib.theme;

import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.servlet.DirectRequestDispatcherFactoryUtil;
import com.liferay.portal.kernel.servlet.PipingServletResponse;
import com.liferay.portal.kernel.util.ThemeHelper;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Theme;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.taglib.util.ParamAndPropertyAncestorTagImpl;
import com.liferay.taglib.util.ThemeUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTag;

/**
 * @author Brian Wing Shun Chan
 */
public class WrapPortletTag
	extends ParamAndPropertyAncestorTagImpl implements BodyTag {

	public static String doTag(
			String wrapPage, String portletPage, ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response,
			PageContext pageContext)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Theme theme = themeDisplay.getTheme();
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		// Portlet content

		RequestDispatcher requestDispatcher =
			DirectRequestDispatcherFactoryUtil.getRequestDispatcher(
				servletContext, portletPage);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		PipingServletResponse pipingServletResponse = new PipingServletResponse(
			response, unsyncStringWriter);

		requestDispatcher.include(request, pipingServletResponse);

		portletDisplay.setContent(unsyncStringWriter.getStringBundler());

		// Page

		String content = null;

		String extension = theme.getTemplateExtension();

		if (extension.equals(ThemeHelper.TEMPLATE_EXTENSION_FTL)) {
			content = ThemeUtil.includeFTL(
				servletContext, request, pageContext, wrapPage, theme, false);
		}
		else if (extension.equals(ThemeHelper.TEMPLATE_EXTENSION_VM)) {
			content = ThemeUtil.includeVM(
				servletContext, request, pageContext, wrapPage, theme, false);
		}

		return _CONTENT_WRAPPER_PRE.concat(content).concat(
			_CONTENT_WRAPPER_POST);
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			Theme theme = themeDisplay.getTheme();
			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			// Portlet content

			portletDisplay.setContent(getBodyContentAsStringBundler());

			// Page

			ThemeUtil.include(
				servletContext, request, new PipingServletResponse(pageContext),
				pageContext, getPage(), theme);

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
		finally {
			clearParams();
			clearProperties();
		}
	}

	@Override
	public int doStartTag() {
		return EVAL_BODY_BUFFERED;
	}

	public void setPage(String page) {
		_page = page;
	}

	protected String getPage() {
		return _page;
	}

	private static final String _CONTENT_WRAPPER_POST = "</div>";

	private static final String _CONTENT_WRAPPER_PRE =
		"<div class=\"column-1\" id=\"main-content\" role=\"main\">";

	private String _page;

}