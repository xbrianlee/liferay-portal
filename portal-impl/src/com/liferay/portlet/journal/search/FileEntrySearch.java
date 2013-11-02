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
import com.liferay.portal.kernel.repository.model.FileEntry;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 * @author Vilmos Papp
 */
public class FileEntrySearch extends SearchContainer<FileEntry> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("document");
		headerNames.add("size");
		headerNames.add("downloads");
		headerNames.add("locked");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "there-are-no-documents";

	public FileEntrySearch(
		PortletRequest portletRequest, FileEntryDisplayTerms displayTerms,
		FileEntrySearchTerms searchTerms, String cur, int delta,
		PortletURL iteratorURL, List<String> headers,
		String emptyResultsMessage) {

		super(
			portletRequest, new FileEntryDisplayTerms(portletRequest),
			new FileEntrySearchTerms(portletRequest), cur, delta, iteratorURL,
			headers, emptyResultsMessage);

		iteratorURL.setParameter(
			FileEntryDisplayTerms.DOCUMENT, displayTerms.getDocument());
		iteratorURL.setParameter(
			FileEntryDisplayTerms.LOCKED,
			String.valueOf(displayTerms.isLocked()));
		iteratorURL.setParameter(
			FileEntryDisplayTerms.SELECTED_GROUP_ID,
			String.valueOf(displayTerms.getSelectedGroupId()));
		iteratorURL.setParameter(
			FileEntryDisplayTerms.SIZE, displayTerms.getSize());
	}

	public FileEntrySearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		this(
			portletRequest, new FileEntryDisplayTerms(portletRequest),
			new FileEntrySearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);
	}

}