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
import com.liferay.portlet.shopping.model.ShoppingCoupon;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 */
public class CouponSearch extends SearchContainer<ShoppingCoupon> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("code");
		headerNames.add("description");
		headerNames.add("start-date");
		headerNames.add("expiration-date");
		headerNames.add("discount-type");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "no-coupons-were-found";

	public CouponSearch(PortletRequest portletRequest, PortletURL iteratorURL) {
		super(
			portletRequest, new CouponDisplayTerms(portletRequest),
			new CouponDisplayTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		CouponDisplayTerms displayTerms = (CouponDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			CouponDisplayTerms.ACTIVE, String.valueOf(displayTerms.isActive()));
		iteratorURL.setParameter(
			CouponDisplayTerms.CODE, displayTerms.getCode());
		iteratorURL.setParameter(
			CouponDisplayTerms.DISCOUNT_TYPE, displayTerms.getDiscountType());
	}

}