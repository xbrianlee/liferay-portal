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

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 */
public class FormNavigatorTag extends IncludeTag {

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public void setBackURL(String backURL) {
		_backURL = backURL;
	}

	public void setCategoryNames(String[] categoryNames) {
		_categoryNames = categoryNames;
	}

	public void setCategorySections(String[][] categorySections) {
		_categorySections = categorySections;
	}

	public void setDisplayStyle(String displayStyle) {
		_displayStyle = displayStyle;
	}

	public void setFormName(String formName) {
		_formName = formName;
	}

	public void setHtmlBottom(String htmlBottom) {
		_htmlBottom = htmlBottom;
	}

	public void setHtmlTop(String htmlTop) {
		_htmlTop = htmlTop;
	}

	public void setJspPath(String jspPath) {
		_jspPath = jspPath;
	}

	public void setShowButtons(boolean showButtons) {
		_showButtons = showButtons;
	}

	@Override
	protected void cleanUp() {
		_backURL = null;
		_categoryNames = null;
		_categorySections = null;
		_displayStyle = "form";
		_formName = "fm";
		_htmlBottom = null;
		_htmlTop = null;
		_jspPath = null;
		_showButtons = true;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:form-navigator:backURL", _backURL);
		request.setAttribute(
			"liferay-ui:form-navigator:categoryNames", _categoryNames);
		request.setAttribute(
			"liferay-ui:form-navigator:categorySections", _categorySections);
		request.setAttribute(
			"liferay-ui:form-navigator:displayStyle", _displayStyle);
		request.setAttribute("liferay-ui:form-navigator:formName", _formName);
		request.setAttribute(
			"liferay-ui:form-navigator:htmlBottom", _htmlBottom);
		request.setAttribute("liferay-ui:form-navigator:htmlTop", _htmlTop);
		request.setAttribute("liferay-ui:form-navigator:jspPath", _jspPath);
		request.setAttribute(
			"liferay-ui:form-navigator:showButtons",
			String.valueOf(_showButtons));
	}

	private static final String _PAGE =
		"/html/taglib/ui/form_navigator/page.jsp";

	private String _backURL;
	private String[] _categoryNames;
	private String[][] _categorySections;
	private String _displayStyle = "form";
	private String _formName = "fm";
	private String _htmlBottom;
	private String _htmlTop;
	private String _jspPath;
	private boolean _showButtons = true;

}