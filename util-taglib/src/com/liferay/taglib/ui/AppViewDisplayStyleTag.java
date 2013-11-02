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

import com.liferay.taglib.util.IncludeTag;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sergio González
 */
public class AppViewDisplayStyleTag extends IncludeTag {

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public void setDisplayStyle(String displayStyle) {
		_displayStyle = displayStyle;
	}

	public void setDisplayStyles(String[] displayStyles) {
		_displayStyles = displayStyles;
	}

	public void setRequestParams(Map<String, String> requestParams) {
		_requestParams = requestParams;
	}

	@Override
	protected void cleanUp() {
		_displayStyle = null;
		_displayStyles = null;
		_requestParams = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:app-view-display-style:displayStyle", _displayStyle);
		request.setAttribute(
			"liferay-ui:app-view-display-style:displayStyles", _displayStyles);
		request.setAttribute(
			"liferay-ui:app-view-display-style:requestParams", _requestParams);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

	private static final String _PAGE =
		"/html/taglib/ui/app_view_display_style/page.jsp";

	private String _displayStyle;
	private String[] _displayStyles;
	private Map<String, String> _requestParams;

}