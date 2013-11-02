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

package com.liferay.portlet.blogsadmin.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portlet.blogs.model.BlogsEntry;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Juan Fernández
 */
public class EntrySearch extends SearchContainer<BlogsEntry> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("title");
		headerNames.add("author");
		headerNames.add("createDate");
		headerNames.add("status");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "no-entries-were-found";

	public EntrySearch(PortletRequest portletRequest, PortletURL iteratorURL) {
		super(
			portletRequest, new EntryDisplayTerms(portletRequest),
			new EntrySearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		EntryDisplayTerms displayTerms = (EntryDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			EntryDisplayTerms.AUTHOR, displayTerms.getAuthor());
		iteratorURL.setParameter(
			EntryDisplayTerms.STATUS, String.valueOf(displayTerms.getStatus()));
		iteratorURL.setParameter(
			EntryDisplayTerms.TITLE, displayTerms.getTitle());
	}

}