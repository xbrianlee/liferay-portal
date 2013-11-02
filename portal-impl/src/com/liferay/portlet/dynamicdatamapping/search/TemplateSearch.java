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

package com.liferay.portlet.dynamicdatamapping.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Eduardo Lundgren
 */
public class TemplateSearch extends SearchContainer<DDMTemplate> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("id");
		headerNames.add("name");
		headerNames.add("type");
		headerNames.add("language");
		headerNames.add("modified-date");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "there-are-no-templates";

	public TemplateSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new TemplateDisplayTerms(portletRequest),
			new TemplateSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		TemplateDisplayTerms displayTerms =
			(TemplateDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			TemplateDisplayTerms.DESCRIPTION, displayTerms.getDescription());
		iteratorURL.setParameter(
			TemplateDisplayTerms.NAME, displayTerms.getName());
	}

}