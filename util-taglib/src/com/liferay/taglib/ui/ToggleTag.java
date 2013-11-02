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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.servlet.PipingServletResponse;
import com.liferay.portal.kernel.util.DeterminateKeyGenerator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.SessionClicks;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

/**
 * @author Brian Wing Shun Chan
 */
public class ToggleTag extends IncludeTag {

	public static void doTag(
			String id, String showImage, String hideImage, String showMessage,
			String hideMessage, boolean defaultShowContent, String stateVar,
			ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		doTag(
			_PAGE, id, showImage, hideImage, showMessage, hideMessage,
			defaultShowContent, stateVar, servletContext, request, response);
	}

	public static void doTag(
			String page, String id, String showImage, String hideImage,
			String showMessage, String hideMessage, boolean defaultShowContent,
			String stateVar, ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (Validator.isNull(showImage) && Validator.isNull(showMessage)) {
			showImage =
				themeDisplay.getPathThemeImages() + "/arrows/01_down.png";
		}

		if (Validator.isNull(hideImage) && Validator.isNull(hideImage)) {
			hideImage =
				themeDisplay.getPathThemeImages() + "/arrows/01_right.png";
		}

		String defaultStateValue =
			defaultShowContent ? StringPool.BLANK : "none";
		String defaultImage = defaultShowContent ? hideImage : showImage;
		String defaultMessage = defaultShowContent ? hideMessage : showMessage;

		String clickValue = SessionClicks.get(request, id, null);

		if (defaultShowContent) {
			if ((clickValue != null) && clickValue.equals("none")) {
				defaultStateValue = "none";
				defaultImage = showImage;
				defaultMessage = showMessage;
			}
			else {
				defaultStateValue = "";
				defaultImage = hideImage;
				defaultMessage = hideMessage;
			}
		}
		else {
			if ((clickValue == null) || clickValue.equals("none")) {
				defaultStateValue = "none";
				defaultImage = showImage;
				defaultMessage = showMessage;
			}
			else {
				defaultStateValue = "";
				defaultImage = hideImage;
				defaultMessage = hideMessage;
			}
		}

		if (stateVar == null) {
			stateVar = DeterminateKeyGenerator.generate(
				ToggleTag.class.getName());
		}

		request.setAttribute("liferay-ui:toggle:id", id);
		request.setAttribute("liferay-ui:toggle:showImage", showImage);
		request.setAttribute("liferay-ui:toggle:hideImage", hideImage);
		request.setAttribute("liferay-ui:toggle:showMessage", showMessage);
		request.setAttribute("liferay-ui:toggle:hideMessage", hideMessage);
		request.setAttribute("liferay-ui:toggle:stateVar", stateVar);
		request.setAttribute(
			"liferay-ui:toggle:defaultStateValue", defaultStateValue);
		request.setAttribute("liferay-ui:toggle:defaultImage", defaultImage);
		request.setAttribute(
			"liferay-ui:toggle:defaultMessage", defaultMessage);

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher(page);

		requestDispatcher.include(request, response);
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			doTag(
				getPage(), _id, _showImage, _hideImage, _showMessage,
				_hideMessage, _defaultShowContent, _stateVar, servletContext,
				request, new PipingServletResponse(pageContext));

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	public void setDefaultShowContent(boolean defaultShowContent) {
		_defaultShowContent = defaultShowContent;
	}

	public void setHideImage(String hideImage) {
		_hideImage = hideImage;
	}

	public void setHideMessage(String hideMessage) {
		_hideMessage = hideMessage;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setShowImage(String showImage) {
		_showImage = showImage;
	}

	public void setShowMessage(String showMessage) {
		_showMessage = showMessage;
	}

	public void setStateVar(String stateVar) {
		_stateVar = stateVar;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	private static final String _PAGE = "/html/taglib/ui/toggle/page.jsp";

	private boolean _defaultShowContent = true;
	private String _hideImage;
	private String _hideMessage;
	private String _id;
	private String _showImage;
	private String _showMessage;
	private String _stateVar;

}