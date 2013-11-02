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
 * @author Brian Wing Shun Chan
 */
public class InputSelectTag extends IncludeTag {

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setDefaultValue(boolean defaultValue) {
		_defaultValue = Boolean.valueOf(defaultValue);
	}

	public void setDisabled(boolean disabled) {
		_disabled = disabled;
	}

	public void setFormName(String formName) {
		_formName = formName;
	}

	public void setParam(String param) {
		_param = param;
	}

	@Override
	protected void cleanUp() {
		_cssClass = null;
		_defaultValue = Boolean.FALSE;
		_disabled = false;
		_formName = "fm";
		_param = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:input-select:cssClass", _cssClass);
		request.setAttribute(
			"liferay-ui:input-select:defaultValue", _defaultValue);
		request.setAttribute(
			"liferay-ui:input-select:disabled", String.valueOf(_disabled));
		request.setAttribute("liferay-ui:input-select:formName", _formName);
		request.setAttribute("liferay-ui:input-select:param", _param);
	}

	private static final String _PAGE = "/html/taglib/ui/input_select/page.jsp";

	private String _cssClass;
	private Boolean _defaultValue = Boolean.FALSE;
	private boolean _disabled;
	private String _formName = "fm";
	private String _param;

}