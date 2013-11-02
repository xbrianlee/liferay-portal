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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;

import java.text.Format;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class InputFieldTag extends IncludeTag {

	public void setAutoFocus(boolean autoFocus) {
		_autoFocus = autoFocus;
	}

	public void setAutoSize(boolean autoSize) {
		_autoSize = autoSize;
	}

	public void setBean(Object bean) {
		_bean = bean;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setDateTogglerCheckboxLabel(String dateTogglerCheckboxLabel) {
		_dateTogglerCheckboxLabel = dateTogglerCheckboxLabel;
	}

	public void setDefaultLanguageId(String defaultLanguageId) {
		_defaultLanguageId = defaultLanguageId;
	}

	public void setDefaultValue(Object defaultValue) {
		_defaultValue = defaultValue;
	}

	public void setDisabled(boolean disabled) {
		_disabled = disabled;
	}

	public void setField(String field) {
		_field = field;
	}

	public void setFieldParam(String fieldParam) {
		_fieldParam = fieldParam;
	}

	public void setFormat(Format format) {
		_format = format;
	}

	public void setFormName(String formName) {
		_formName = formName;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIgnoreRequestValue(boolean ignoreRequestValue) {
		_ignoreRequestValue = ignoreRequestValue;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	public void setModel(Class<?> model) {
		_model = model;
	}

	public void setPlaceholder(String placeholder) {
		_placeholder = placeholder;
	}

	@Override
	protected void cleanUp() {
		_autoFocus = false;
		_autoSize = false;
		_bean = null;
		_cssClass = null;
		_dateTogglerCheckboxLabel = null;
		_defaultLanguageId = null;
		_defaultValue = null;
		_disabled = false;
		_field = null;
		_fieldParam = null;
		_format = null;
		_formName = "fm";
		_id = null;
		_ignoreRequestValue = false;
		_languageId = null;
		_model = null;
		_placeholder = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		String fieldParam = _fieldParam;

		if (Validator.isNull(fieldParam)) {
			fieldParam = _field;
		}

		String id = _id;

		if (Validator.isNull(id)) {
			id = fieldParam;
		}

		request.setAttribute(
			"liferay-ui:input-field:autoFocus", String.valueOf(_autoFocus));
		request.setAttribute(
			"liferay-ui:input-field:autoSize", String.valueOf(_autoSize));
		request.setAttribute("liferay-ui:input-field:bean", _bean);
		request.setAttribute("liferay-ui:input-field:cssClass", _cssClass);
		request.setAttribute(
			"liferay-ui:input-field:dateTogglerCheckboxLabel",
			_dateTogglerCheckboxLabel);
		request.setAttribute(
			"liferay-ui:input-field:defaultLanguageId", _defaultLanguageId);
		request.setAttribute(
			"liferay-ui:input-field:defaultValue", _defaultValue);
		request.setAttribute(
			"liferay-ui:input-field:disabled", String.valueOf(_disabled));
		request.setAttribute("liferay-ui:input-field:field", _field);
		request.setAttribute("liferay-ui:input-field:fieldParam", fieldParam);
		request.setAttribute("liferay-ui:input-field:id", id);
		request.setAttribute("liferay-ui:input-field:format", _format);
		request.setAttribute("liferay-ui:input-field:formName", _formName);
		request.setAttribute(
			"liferay-ui:input-field:ignoreRequestValue",
			String.valueOf(_ignoreRequestValue));
		request.setAttribute("liferay-ui:input-field:languageId", _languageId);
		request.setAttribute("liferay-ui:input-field:model", _model.getName());
		request.setAttribute(
			"liferay-ui:input-field:placeholder", _placeholder);
	}

	private static final String _PAGE = "/html/taglib/ui/input_field/page.jsp";

	private boolean _autoFocus;
	private boolean _autoSize;
	private Object _bean;
	private String _cssClass;
	private String _dateTogglerCheckboxLabel;
	private String _defaultLanguageId;
	private Object _defaultValue;
	private boolean _disabled;
	private String _field;
	private String _fieldParam;
	private Format _format;
	private String _formName = "fm";
	private String _id;
	private boolean _ignoreRequestValue;
	private String _languageId;
	private Class<?> _model;
	private String _placeholder;

}