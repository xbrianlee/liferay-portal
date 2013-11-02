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

import javax.servlet.jsp.JspException;

/**
 * @author Eduardo Lundgren
 * @author Bruno Basto
 * @author Nathan Cavanaugh
 * @author Julio Camarero
 * @generated
 */
public class BaseToolTagImpl extends javax.servlet.jsp.tagext.TagSupport {

	@Override
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}

	public java.lang.String getHandler() {
		return _handler;
	}

	public java.lang.String getIcon() {
		return _icon;
	}

	@Override
	public java.lang.String getId() {
		return _id;
	}

	public void setHandler(java.lang.String handler) {
		_handler = handler;
	}

	public void setIcon(java.lang.String icon) {
		_icon = icon;
	}

	@Override
	public void setId(java.lang.String id) {
		_id = id;
	}

	protected void cleanUp() {
		_handler = null;
		_icon = null;
		_id = null;
	}

	protected String getPage() {
		return _PAGE;
	}

	private static final String _PAGE =
		"/html/taglib/aui/tool/page.jsp";

	private java.lang.String _handler = null;
	private java.lang.String _icon = null;
	private java.lang.String _id = null;

}