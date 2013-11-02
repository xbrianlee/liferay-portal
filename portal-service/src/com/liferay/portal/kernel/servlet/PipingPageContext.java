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

package com.liferay.portal.kernel.servlet;

import java.io.Writer;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

/**
 * @author Shuyang Zhou
 */
public class PipingPageContext extends PageContextWrapper {

	public PipingPageContext(PageContext pageContext, Writer writer) {
		super(pageContext);

		_jspWriter = new PipingJspWriter(writer);
	}

	@Override
	public JspWriter getOut() {
		return _jspWriter;
	}

	private JspWriter _jspWriter;

}