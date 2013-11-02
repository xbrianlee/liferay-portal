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

package com.liferay.portlet.mobiledevicerules.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Edward Han
 */
public class RuleGroupSearch extends SearchContainer<MDRRuleGroup> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("name");
		headerNames.add("description");
	}

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-device-families-are-configured";

	public RuleGroupSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new RuleGroupDisplayTerms(portletRequest),
			new RuleGroupSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		RuleGroupDisplayTerms displayTerms =
			(RuleGroupDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			RuleGroupDisplayTerms.GROUP_ID,
			String.valueOf(displayTerms.getGroupId()));
		iteratorURL.setParameter(
			RuleGroupDisplayTerms.NAME, displayTerms.getName());
	}

}