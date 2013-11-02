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

package com.liferay.taglib.aui.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

/**
 * @author Eduardo Lundgren
 * @author Bruno Basto
 * @author Nathan Cavanaugh
 * @author Julio Camarero
 * @generated
 */
public class BaseTranslationManagerTag extends com.liferay.taglib.util.IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doStartTag();
	}

	public java.util.Locale[] getAvailableLocales() {
		return _availableLocales;
	}

	public java.lang.String getDefaultLanguageId() {
		return _defaultLanguageId;
	}

	public java.lang.String getEditingLanguageId() {
		return _editingLanguageId;
	}

	public java.lang.String getId() {
		return _id;
	}

	public boolean getInitialize() {
		return _initialize;
	}

	public boolean getReadOnly() {
		return _readOnly;
	}

	public void setAvailableLocales(java.util.Locale[] availableLocales) {
		_availableLocales = availableLocales;

		setScopedAttribute("availableLocales", availableLocales);
	}

	public void setDefaultLanguageId(java.lang.String defaultLanguageId) {
		_defaultLanguageId = defaultLanguageId;

		setScopedAttribute("defaultLanguageId", defaultLanguageId);
	}

	public void setEditingLanguageId(java.lang.String editingLanguageId) {
		_editingLanguageId = editingLanguageId;

		setScopedAttribute("editingLanguageId", editingLanguageId);
	}

	public void setId(java.lang.String id) {
		_id = id;

		setScopedAttribute("id", id);
	}

	public void setInitialize(boolean initialize) {
		_initialize = initialize;

		setScopedAttribute("initialize", initialize);
	}

	public void setReadOnly(boolean readOnly) {
		_readOnly = readOnly;

		setScopedAttribute("readOnly", readOnly);
	}

	@Override
	protected void cleanUp() {
		_availableLocales = null;
		_defaultLanguageId = null;
		_editingLanguageId = null;
		_id = null;
		_initialize = true;
		_readOnly = false;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		setNamespacedAttribute(request, "availableLocales", _availableLocales);
		setNamespacedAttribute(request, "defaultLanguageId", _defaultLanguageId);
		setNamespacedAttribute(request, "editingLanguageId", _editingLanguageId);
		setNamespacedAttribute(request, "id", _id);
		setNamespacedAttribute(request, "initialize", _initialize);
		setNamespacedAttribute(request, "readOnly", _readOnly);
	}

	protected static final String _ATTRIBUTE_NAMESPACE = "aui:translation-manager:";

	private static final String _PAGE =
		"/html/taglib/aui/translation_manager/page.jsp";

	private java.util.Locale[] _availableLocales = null;
	private java.lang.String _defaultLanguageId = null;
	private java.lang.String _editingLanguageId = null;
	private java.lang.String _id = null;
	private boolean _initialize = true;
	private boolean _readOnly = false;

}