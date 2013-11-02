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

package com.liferay.portlet.journal.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portlet.journal.model.JournalFeed;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Raymond Augé
 */
public class FeedSearch extends SearchContainer<JournalFeed> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("id");
		headerNames.add("description");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "no-feeds-were-found";

	public FeedSearch(PortletRequest portletRequest, PortletURL iteratorURL) {
		super(
			portletRequest, new FeedDisplayTerms(portletRequest),
			new FeedSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		FeedDisplayTerms displayTerms = (FeedDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			FeedDisplayTerms.DESCRIPTION, displayTerms.getDescription());
		iteratorURL.setParameter(
			FeedDisplayTerms.FEED_ID, displayTerms.getFeedId());
		iteratorURL.setParameter(FeedDisplayTerms.NAME, displayTerms.getName());
		iteratorURL.setParameter(
			FeedDisplayTerms.GROUP_ID,
			String.valueOf(displayTerms.getGroupId()));
	}

}