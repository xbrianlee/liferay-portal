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

package com.liferay.portal.kernel.dao.search;

import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class DisplayTerms {

	public static final String ADVANCED_SEARCH = "advancedSearch";

	public static final String AND_OPERATOR = "andOperator";

	public static final String KEYWORDS = "keywords";

	public DisplayTerms(HttpServletRequest request) {
		advancedSearch = ParamUtil.getBoolean(request, ADVANCED_SEARCH);
		andOperator = ParamUtil.getBoolean(request, AND_OPERATOR, true);
		keywords = ParamUtil.getString(request, KEYWORDS);
	}

	public DisplayTerms(PortletRequest portletRequest) {
		advancedSearch = ParamUtil.getBoolean(portletRequest, ADVANCED_SEARCH);
		andOperator = ParamUtil.getBoolean(portletRequest, AND_OPERATOR, true);
		keywords = ParamUtil.getString(portletRequest, KEYWORDS);
	}

	public String getKeywords() {
		return keywords;
	}

	public boolean isAdvancedSearch() {
		return advancedSearch;
	}

	public boolean isAndOperator() {
		return andOperator;
	}

	public void setAdvancedSearch(boolean advancedSearch) {
		this.advancedSearch = advancedSearch;
	}

	protected boolean advancedSearch;
	protected boolean andOperator;
	protected String keywords;

}