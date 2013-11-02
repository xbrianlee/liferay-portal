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
public class BaseValidatorTagImpl extends com.liferay.portal.kernel.servlet.taglib.BaseBodyTagSupport {

	@Override
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}

	public java.lang.String getErrorMessage() {
		return _errorMessage;
	}

	public java.lang.String getName() {
		return _name;
	}

	public void setErrorMessage(java.lang.String errorMessage) {
		_errorMessage = errorMessage;
	}

	public void setName(java.lang.String name) {
		_name = name;
	}

	protected void cleanUp() {
		_errorMessage = null;
		_name = null;
	}

	protected String getPage() {
		return _PAGE;
	}

	private static final String _PAGE =
		"/html/taglib/aui/validator/page.jsp";

	private java.lang.String _errorMessage = null;
	private java.lang.String _name = null;

}