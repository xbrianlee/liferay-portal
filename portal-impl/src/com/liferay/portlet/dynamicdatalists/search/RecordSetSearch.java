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

package com.liferay.portlet.dynamicdatalists.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Marcellus Tavares
 */
public class RecordSetSearch extends SearchContainer<DDLRecordSet> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("id");
		headerNames.add("name");
		headerNames.add("description");
		headerNames.add("modified-date");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "no-entries-were-found";

	public RecordSetSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new RecordSetDisplayTerms(portletRequest),
			new RecordSetSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		RecordSetDisplayTerms displayTerms =
			(RecordSetDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			RecordSetDisplayTerms.DESCRIPTION, displayTerms.getDescription());
		iteratorURL.setParameter(
			RecordSetDisplayTerms.NAME, String.valueOf(displayTerms.getName()));
	}

}