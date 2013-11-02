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

import com.liferay.portal.kernel.servlet.taglib.FileAvailabilityUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

/**
 * @author Raymond Augé
 */
public class ToggleAreaTag extends IncludeTag {

	@Override
	public int doEndTag() throws JspException {
		try {
			if (!FileAvailabilityUtil.isAvailable(
					servletContext, getEndPage())) {

				JspWriter jspWriter = pageContext.getOut();

				jspWriter.write("</div>");
			}
			else {
				include(_endPage);
			}

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
		finally {
			if (!ServerDetector.isResin()) {
				_startPage = null;
				_endPage = null;
				_id = null;
				_showMessage = null;
				_hideMessage = null;
				_defaultShowContent = true;
				_stateVar = null;
				_align = "left";
			}
		}
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			HttpServletRequest request =
				(HttpServletRequest)pageContext.getRequest();

			request.setAttribute("liferay-ui:toggle-area:id", _id);
			request.setAttribute(
				"liferay-ui:toggle-area:showImage", _showImage);
			request.setAttribute(
				"liferay-ui:toggle-area:hideImage", _hideImage);
			request.setAttribute(
				"liferay-ui:toggle-area:showMessage", _showMessage);
			request.setAttribute(
				"liferay-ui:toggle-area:hideMessage", _hideMessage);
			request.setAttribute(
				"liferay-ui:toggle-area:defaultShowContent",
				String.valueOf(_defaultShowContent));
			request.setAttribute("liferay-ui:toggle-area:stateVar", _stateVar);
			request.setAttribute("liferay-ui:toggle-area:align", _align);

			include(getStartPage());

			return EVAL_BODY_INCLUDE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	public void setAlign(String align) {
		_align = align;
	}

	public void setDefaultShowContent(boolean defaultShowContent) {
		_defaultShowContent = defaultShowContent;
	}

	public void setEndPage(String endPage) {
		_endPage = endPage;
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

	public void setStartPage(String startPage) {
		_startPage = startPage;
	}

	public void setStateVar(String stateVar) {
		_stateVar = stateVar;
	}

	@Override
	protected String getEndPage() {
		if (Validator.isNull(_endPage)) {
			return _END_PAGE;
		}
		else {
			return _endPage;
		}
	}

	@Override
	protected String getStartPage() {
		if (Validator.isNull(_startPage)) {
			return _START_PAGE;
		}
		else {
			return _startPage;
		}
	}

	private static final String _END_PAGE =
		"/html/taglib/ui/toggle_area/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/ui/toggle_area/start.jsp";

	private String _align = "left";
	private boolean _defaultShowContent = true;
	private String _endPage;
	private String _hideImage;
	private String _hideMessage;
	private String _id;
	private String _showImage;
	private String _showMessage;
	private String _startPage;
	private String _stateVar;

}