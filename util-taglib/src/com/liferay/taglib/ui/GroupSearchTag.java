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

import com.liferay.portal.kernel.dao.search.RowChecker;

import java.util.LinkedHashMap;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Brian Wing Shun Chan
 */
public class GroupSearchTag extends TagSupport {

	public void setGroupParams(LinkedHashMap<String, Object> groupParams) {
		_groupParams = groupParams;
	}

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	public void setRowChecker(RowChecker rowChecker) {
		_rowChecker = rowChecker;
	}

	protected void cleanUp() {
		_groupParams = null;
		_portletURL = null;
		_rowChecker = null;
	}

	protected String getEndPage() {
		return _END_PAGE;
	}

	protected String getStartPage() {
		return _START_PAGE;
	}

	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:group-search:groupParams", _groupParams);
		request.setAttribute("liferay-ui:group-search:portletURL", _portletURL);
		request.setAttribute("liferay-ui:group-search:rowChecker", _rowChecker);
	}

	private static final String _END_PAGE =
		"/html/taglib/ui/group_search/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/ui/group_search/start.jsp";

	private LinkedHashMap<String, Object> _groupParams;
	private PortletURL _portletURL;
	private RowChecker _rowChecker;

}