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

import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Bruno Farache
 * @author Julio Camarero
 */
public class InputRepeatTag extends IncludeTag {

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setEvent(CalEvent event) {
		_event = event;
	}

	@Override
	protected void cleanUp() {
		_cssClass = null;
		_event = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:input-repeat:cssClass", _cssClass);
		request.setAttribute("liferay-ui:input-repeat:event", _event);
	}

	private static final String _PAGE = "/html/taglib/ui/input_repeat/page.jsp";

	private String _cssClass;
	private CalEvent _event;

}