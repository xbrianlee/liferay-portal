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
public class FlagsTag extends IncludeTag {

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setContentTitle(String contentTitle) {
		_contentTitle = contentTitle;
	}

	public void setLabel(boolean label) {
		_label = label;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public void setReportedUserId(long reportedUserId) {
		_reportedUserId = reportedUserId;
	}

	@Override
	protected void cleanUp() {
		_className = null;
		_classPK = 0;
		_contentTitle = null;
		_label = true;
		_message = null;
		_reportedUserId = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:flags:className", _className);
		request.setAttribute(
			"liferay-ui:flags:classPK", String.valueOf(_classPK));
		request.setAttribute("liferay-ui:flags:contentTitle", _contentTitle);
		request.setAttribute("liferay-ui:flags:label", String.valueOf(_label));
		request.setAttribute("liferay-ui:flags:message", _message);
		request.setAttribute(
			"liferay-ui:flags:reportedUserId", String.valueOf(_reportedUserId));
	}

	private static final String _PAGE = "/html/taglib/ui/flags/page.jsp";

	private String _className;
	private long _classPK;
	private String _contentTitle;
	private boolean _label = true;
	private String _message;
	private long _reportedUserId;

}