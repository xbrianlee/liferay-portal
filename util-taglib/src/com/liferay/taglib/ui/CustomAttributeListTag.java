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
public class CustomAttributeListTag extends IncludeTag {

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setEditable(boolean editable) {
		_editable = editable;
	}

	public void setIgnoreAttributeNames(String ignoreAttributeNames) {
		_ignoreAttributeNames = ignoreAttributeNames;
	}

	public void setLabel(boolean label) {
		_label = label;
	}

	@Override
	protected void cleanUp() {
		_className = null;
		_classPK = 0;
		_editable = false;
		_ignoreAttributeNames = null;
		_label = false;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:custom-attribute-list:className", _className);
		request.setAttribute(
			"liferay-ui:custom-attribute-list:classPK",
			String.valueOf(_classPK));
		request.setAttribute(
			"liferay-ui:custom-attribute-list:editable",
			String.valueOf(_editable));
		request.setAttribute(
			"liferay-ui:custom-attribute-list:ignoreAttributeNames",
			_ignoreAttributeNames);
		request.setAttribute(
			"liferay-ui:custom-attribute-list:label", String.valueOf(_label));
	}

	private static final String _PAGE =
		"/html/taglib/ui/custom_attribute_list/page.jsp";

	private String _className;
	private long _classPK;
	private boolean _editable;
	private String _ignoreAttributeNames;
	private boolean _label;

}