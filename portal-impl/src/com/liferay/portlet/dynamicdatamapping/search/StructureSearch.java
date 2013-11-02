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
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Eduardo Lundgren
 */
public class StructureSearch extends SearchContainer<DDMStructure> {

	public static final String EMPTY_RESULTS_MESSAGE = "there-are-no-results";

	public StructureSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new StructureDisplayTerms(portletRequest),
			new StructureSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, null, EMPTY_RESULTS_MESSAGE);

		StructureDisplayTerms displayTerms =
			(StructureDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			StructureDisplayTerms.CLASS_NAME_ID,
			String.valueOf(displayTerms.getClassNameId()));
		iteratorURL.setParameter(
			StructureDisplayTerms.DESCRIPTION, displayTerms.getDescription());
		iteratorURL.setParameter(
			StructureDisplayTerms.NAME, displayTerms.getName());
		iteratorURL.setParameter(
			StructureDisplayTerms.STORAGE_TYPE, displayTerms.getStorageType());
	}

}