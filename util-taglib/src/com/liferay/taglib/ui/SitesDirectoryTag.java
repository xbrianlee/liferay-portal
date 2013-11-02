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
 * @author Sergio González
 */
public class SitesDirectoryTag extends IncludeTag {

	public static final String SITES_CHILDREN = "children";

	public static final String SITES_PARENT_LEVEL = "parent-level";

	public static final String SITES_SIBLINGS = "siblings";

	public static final String SITES_TOP_LEVEL = "top-level";

	public void setDisplayStyle(String displayStyle) {
		_displayStyle = displayStyle;
	}

	public void setSites(String sites) {
		_sites = sites;
	}

	@Override
	protected void cleanUp() {
		_displayStyle = "descriptive";
		_sites = SITES_TOP_LEVEL;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:sites-directory:displayStyle", _displayStyle);
		request.setAttribute(
			"liferay-ui:sites-directory:sites", String.valueOf(_sites));
	}

	private static final String _PAGE =
		"/html/taglib/ui/sites_directory/page.jsp";

	private String _displayStyle = "descriptive";
	private String _sites = SITES_TOP_LEVEL;

}