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

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sergio González
 */
public class HeaderTag extends IncludeTag {

	public void setBackLabel(String backLabel) {
		_backLabel = backLabel;
	}

	public void setBackURL(String backURL) {
		_backURL = backURL;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setEscapeXml(boolean escapeXml) {
		_escapeXml = escapeXml;
	}

	public void setLocalizeTitle(boolean localizeTitle) {
		_localizeTitle = localizeTitle;
	}

	public void setShowBackURL(boolean showBackURL) {
		_showBackURL = showBackURL;
	}

	public void setTitle(String title) {
		_title = title;
	}

	@Override
	protected void cleanUp() {
		_backLabel = null;
		_backURL = null;
		_cssClass = null;
		_escapeXml = true;
		_localizeTitle = true;
		_showBackURL = true;
		_title = null;
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
		request.setAttribute("liferay-ui:header:backLabel", _backLabel);

		String redirect = ParamUtil.getString(request, "redirect");

		if (Validator.isNull(_backURL) && Validator.isNotNull(redirect)) {
			request.setAttribute("liferay-ui:header:backURL", redirect);
		}
		else {
			request.setAttribute("liferay-ui:header:backURL", _backURL);
		}

		request.setAttribute("liferay-ui:header:cssClass", _cssClass);
		request.setAttribute(
			"liferay-ui:header:escapeXml", String.valueOf(_escapeXml));
		request.setAttribute(
			"liferay-ui:header:localizeTitle", String.valueOf(_localizeTitle));
		request.setAttribute(
			"liferay-ui:header:showBackURL", String.valueOf(_showBackURL));
		request.setAttribute("liferay-ui:header:title", _title);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

	private static final String _PAGE = "/html/taglib/ui/header/page.jsp";

	private String _backLabel;
	private String _backURL;
	private String _cssClass;
	private boolean _escapeXml = true;
	private boolean _localizeTitle = true;
	private boolean _showBackURL = true;
	private String _title;

}