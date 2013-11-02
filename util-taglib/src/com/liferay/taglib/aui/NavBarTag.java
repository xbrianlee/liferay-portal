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

package com.liferay.taglib.aui;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.taglib.aui.base.BaseNavBarTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;

/**
 * @author Eduardo Lundgren
 * @author Bruno Basto
 * @author Nathan Cavanaugh
 * @author Julio Camarero
 */
public class NavBarTag extends BaseNavBarTag implements BodyTag {

	@Override
	public int doEndTag() throws JspException {
		setNamespacedAttribute(
			request, "responsiveButtons", _responsiveButtonsSB.toString());

		return super.doEndTag();
	}

	public StringBundler getResponsiveButtonsSB() {
		return _responsiveButtonsSB;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_responsiveButtonsSB.setIndex(0);
	}

	@Override
	protected int processStartTag() throws Exception {
		return EVAL_BODY_BUFFERED;
	}

	private StringBundler _responsiveButtonsSB = new StringBundler();

}