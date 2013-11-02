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
public class BaseNavTag extends com.liferay.taglib.util.IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doStartTag();
	}

	public java.lang.String getAriaLabel() {
		return _ariaLabel;
	}

	public java.lang.String getAriaRole() {
		return _ariaRole;
	}

	public boolean getCollapsible() {
		return _collapsible;
	}

	public java.lang.String getCssClass() {
		return _cssClass;
	}

	public java.lang.String getIcon() {
		return _icon;
	}

	public java.lang.String getId() {
		return _id;
	}

	public boolean getUseNamespace() {
		return _useNamespace;
	}

	public void setAriaLabel(java.lang.String ariaLabel) {
		_ariaLabel = ariaLabel;

		setScopedAttribute("ariaLabel", ariaLabel);
	}

	public void setAriaRole(java.lang.String ariaRole) {
		_ariaRole = ariaRole;

		setScopedAttribute("ariaRole", ariaRole);
	}

	public void setCollapsible(boolean collapsible) {
		_collapsible = collapsible;

		setScopedAttribute("collapsible", collapsible);
	}

	public void setCssClass(java.lang.String cssClass) {
		_cssClass = cssClass;

		setScopedAttribute("cssClass", cssClass);
	}

	public void setIcon(java.lang.String icon) {
		_icon = icon;

		setScopedAttribute("icon", icon);
	}

	public void setId(java.lang.String id) {
		_id = id;

		setScopedAttribute("id", id);
	}

	public void setUseNamespace(boolean useNamespace) {
		_useNamespace = useNamespace;

		setScopedAttribute("useNamespace", useNamespace);
	}

	@Override
	protected void cleanUp() {
		_ariaLabel = null;
		_ariaRole = null;
		_collapsible = false;
		_cssClass = null;
		_icon = null;
		_id = null;
		_useNamespace = true;
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
		setNamespacedAttribute(request, "ariaLabel", _ariaLabel);
		setNamespacedAttribute(request, "ariaRole", _ariaRole);
		setNamespacedAttribute(request, "collapsible", _collapsible);
		setNamespacedAttribute(request, "cssClass", _cssClass);
		setNamespacedAttribute(request, "icon", _icon);
		setNamespacedAttribute(request, "id", _id);
		setNamespacedAttribute(request, "useNamespace", _useNamespace);
	}

	protected static final String _ATTRIBUTE_NAMESPACE = "aui:nav:";

	private static final String _END_PAGE =
		"/html/taglib/aui/nav/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/aui/nav/start.jsp";

	private java.lang.String _ariaLabel = null;
	private java.lang.String _ariaRole = null;
	private boolean _collapsible = false;
	private java.lang.String _cssClass = null;
	private java.lang.String _icon = null;
	private java.lang.String _id = null;
	private boolean _useNamespace = true;

}