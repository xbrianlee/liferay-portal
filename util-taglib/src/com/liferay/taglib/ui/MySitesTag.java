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
public class MySitesTag extends IncludeTag {

	public void setClassNames(String[] classNames) {
		_classNames = classNames;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setIncludeControlPanel(boolean includeControlPanel) {
		_includeControlPanel = includeControlPanel;
	}

	public void setMax(int max) {
		_max = max;
	}

	@Override
	protected void cleanUp() {
		_classNames = null;
		_cssClass = null;
		_includeControlPanel = false;
		_max = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:my_sites:classNames", _classNames);
		request.setAttribute(
			"liferay-ui:my_sites:cssClass", String.valueOf(_cssClass));
		request.setAttribute(
			"liferay-ui:my_sites:includeControlPanel",
			String.valueOf(_includeControlPanel));
		request.setAttribute("liferay-ui:my_sites:max", String.valueOf(_max));
	}

	private static final String _PAGE = "/html/taglib/ui/my_sites/page.jsp";

	private String[] _classNames;
	private String _cssClass;
	private boolean _includeControlPanel;
	private int _max;

}