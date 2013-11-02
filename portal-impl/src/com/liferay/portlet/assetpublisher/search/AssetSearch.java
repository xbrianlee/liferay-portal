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

package com.liferay.portlet.assetpublisher.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portlet.asset.model.AssetEntry;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 * @author Julio Camarero
 */
public class AssetSearch extends SearchContainer<AssetEntry> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("title");
		headerNames.add("description");
		headerNames.add("user-name");
		headerNames.add("modified-date");
		headerNames.add("scope");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "there-are-no-results";

	public AssetSearch(
		PortletRequest portletRequest, int delta, PortletURL iteratorURL) {

		super(
			portletRequest, new AssetDisplayTerms(portletRequest),
			new AssetSearchTerms(portletRequest), DEFAULT_CUR_PARAM, delta,
			iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		AssetDisplayTerms displayTerms = (AssetDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			AssetDisplayTerms.DESCRIPTION, displayTerms.getDescription());
		iteratorURL.setParameter(
			AssetDisplayTerms.GROUP_ID,
			String.valueOf(displayTerms.getGroupId()));
		iteratorURL.setParameter(
			AssetDisplayTerms.TITLE, displayTerms.getTitle());
		iteratorURL.setParameter(
			AssetDisplayTerms.USER_NAME, displayTerms.getUserName());
	}

	public AssetSearch(PortletRequest portletRequest, PortletURL iteratorURL) {
		this(portletRequest, DEFAULT_DELTA, iteratorURL);
	}

}