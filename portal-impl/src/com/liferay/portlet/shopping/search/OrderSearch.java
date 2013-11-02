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

package com.liferay.portlet.shopping.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portlet.shopping.model.ShoppingOrder;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 */
public class OrderSearch extends SearchContainer<ShoppingOrder> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("number");
		headerNames.add("date");
		headerNames.add("status");
		headerNames.add("customer");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "no-orders-were-found";

	public OrderSearch(PortletRequest portletRequest, PortletURL iteratorURL) {
		super(
			portletRequest, new OrderDisplayTerms(portletRequest),
			new OrderSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		OrderDisplayTerms displayTerms = (OrderDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			OrderDisplayTerms.EMAIL_ADDRESS, displayTerms.getEmailAddress());
		iteratorURL.setParameter(
			OrderDisplayTerms.FIRST_NAME, displayTerms.getFirstName());
		iteratorURL.setParameter(
			OrderDisplayTerms.LAST_NAME, displayTerms.getLastName());
		iteratorURL.setParameter(
			OrderDisplayTerms.NUMBER, displayTerms.getNumber());
		iteratorURL.setParameter(
			OrderDisplayTerms.STATUS, displayTerms.getStatus());
	}

}