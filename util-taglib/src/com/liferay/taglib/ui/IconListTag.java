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

import com.liferay.portal.kernel.servlet.PortalIncludeUtil;
import com.liferay.portal.kernel.servlet.taglib.BaseBodyTagSupport;
import com.liferay.portal.kernel.servlet.taglib.FileAvailabilityUtil;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTag;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class IconListTag extends BaseBodyTagSupport implements BodyTag {

	@Override
	public int doAfterBody() {
		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		IntegerWrapper iconCount = (IntegerWrapper)request.getAttribute(
			"liferay-ui:icon-list:icon-count");

		Boolean singleIcon = (Boolean)request.getAttribute(
			"liferay-ui:icon-list:single-icon");

		if ((iconCount != null) && (iconCount.getValue() == 1) &&
			(singleIcon == null)) {

			bodyContent.clearBody();

			request.setAttribute(
				"liferay-ui:icon-list:single-icon", Boolean.TRUE);

			return EVAL_BODY_AGAIN;
		}
		else {
			return SKIP_BODY;
		}
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			HttpServletRequest request =
				(HttpServletRequest)pageContext.getRequest();

			IntegerWrapper iconCount = (IntegerWrapper)request.getAttribute(
				"liferay-ui:icon-list:icon-count");

			request.removeAttribute("liferay-ui:icon-list:icon-count");

			Boolean singleIcon = (Boolean)request.getAttribute(
				"liferay-ui:icon-list:single-icon");

			request.removeAttribute("liferay-ui:icon-list:single-icon");

			JspWriter jspWriter = pageContext.getOut();

			if ((iconCount != null) && (iconCount.getValue() > 1) &&
				((singleIcon == null) || _showWhenSingleIcon)) {

				if (!FileAvailabilityUtil.isAvailable(
						pageContext.getServletContext(), getStartPage())) {

					jspWriter.write("<ul class=\"taglib-icon-list unstyled\">");
				}
				else {
					PortalIncludeUtil.include(pageContext, _startPage);
				}
			}

			writeBodyContent(jspWriter);

			if ((iconCount != null) && (iconCount.getValue() > 1) &&
				((singleIcon == null) || _showWhenSingleIcon)) {

				if (!FileAvailabilityUtil.isAvailable(
						pageContext.getServletContext(), getEndPage())) {

					jspWriter.write("</ul>");
				}
				else {
					PortalIncludeUtil.include(pageContext, _endPage);
				}
			}

			request.removeAttribute("liferay-ui:icon-list:showWhenSingleIcon");

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
		finally {
			if (!ServerDetector.isResin()) {
				_endPage = null;
				_showWhenSingleIcon = false;
				_startPage = null;
			}
		}
	}

	@Override
	public int doStartTag() {
		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		request.setAttribute(
			"liferay-ui:icon-list:icon-count", new IntegerWrapper());
		request.setAttribute(
			"liferay-ui:icon-list:showWhenSingleIcon",
			String.valueOf(_showWhenSingleIcon));

		return EVAL_BODY_BUFFERED;
	}

	public void setEndPage(String endPage) {
		_endPage = endPage;
	}

	public void setShowWhenSingleIcon(boolean showWhenSingleIcon) {
		_showWhenSingleIcon = showWhenSingleIcon;
	}

	public void setStartPage(String startPage) {
		_startPage = startPage;
	}

	protected String getEndPage() {
		if (Validator.isNull(_endPage)) {
			return _END_PAGE;
		}
		else {
			return _endPage;
		}
	}

	protected String getStartPage() {
		if (Validator.isNull(_startPage)) {
			return _START_PAGE;
		}
		else {
			return _startPage;
		}
	}

	private static final String _END_PAGE = "/html/taglib/ui/icon_list/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/ui/icon_list/start.jsp";

	private String _endPage;
	private boolean _showWhenSingleIcon = false;
	private String _startPage;

}