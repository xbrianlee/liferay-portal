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

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class SearchToggleTag extends IncludeTag {

	public void setAutoFocus(boolean autoFocus) {
		_autoFocus = autoFocus;
	}

	public void setButtonLabel(String buttonLabel) {
		_buttonLabel = buttonLabel;
	}

	public void setDisplayTerms(DisplayTerms displayTerms) {
		_displayTerms = displayTerms;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setWidth(String width) {
		_width = width;
	}

	@Override
	protected void cleanUp() {
		_autoFocus = false;
		_buttonLabel = null;
		_displayTerms = null;
		_id = null;
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:search-toggle:autoFocus", String.valueOf(_autoFocus));
		request.setAttribute(
			"liferay-ui:search-toggle:buttonLabel", _buttonLabel);
		request.setAttribute(
			"liferay-ui:search-toggle:displayTerms", _displayTerms);
		request.setAttribute("liferay-ui:search-toggle:id", _id);
		request.setAttribute("liferay-ui:search-toggle:width", _width);
	}

	private static final String _END_PAGE =
		"/html/taglib/ui/search_toggle/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/ui/search_toggle/start.jsp";

	private boolean _autoFocus;
	private String _buttonLabel;
	private DisplayTerms _displayTerms;
	private String _id;
	private String _width;

}