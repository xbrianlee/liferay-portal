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
import com.liferay.taglib.util.IncludeTag;

import java.util.LinkedHashMap;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class UserSearchTag extends IncludeTag {

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	public void setRowChecker(RowChecker rowChecker) {
		_rowChecker = rowChecker;
	}

	public void setUserParams(LinkedHashMap<String, Object> userParams) {
		_userParams = userParams;
	}

	@Override
	protected void cleanUp() {
		_portletURL = null;
		_rowChecker = null;
		_userParams = null;
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:user-search:portletURL", _portletURL);
		request.setAttribute("liferay-ui:user-search:rowChecker", _rowChecker);
		request.setAttribute("liferay-ui:user-search:userParams", _userParams);
	}

	private static final String _END_PAGE =
		"/html/taglib/ui/user_search/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/ui/user_search/start.jsp";

	private PortletURL _portletURL;
	private RowChecker _rowChecker;
	private LinkedHashMap<String, Object> _userParams;

}