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

package com.liferay.portal.search;

import com.browseengine.bobo.api.BrowseFacet;
import com.browseengine.bobo.api.FacetAccessible;

import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.collector.TermCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raymond Augé
 */
public class BoboFacetCollector implements FacetCollector {

	public BoboFacetCollector(
		String fieldName, FacetAccessible facetAccessible) {

		_fieldName = fieldName;
		_facetAccessible = facetAccessible;

		for (BrowseFacet browseFacet : _facetAccessible.getFacets()) {
			_termCollectors.add(new BoboTermCollector(browseFacet));
		}
	}

	@Override
	public String getFieldName() {
		return _fieldName;
	}

	@Override
	public TermCollector getTermCollector(String term) {
		return new BoboTermCollector(_facetAccessible.getFacet(term));
	}

	@Override
	public List<TermCollector> getTermCollectors() {
		return _termCollectors;
	}

	private FacetAccessible _facetAccessible;
	private String _fieldName;
	private List<TermCollector> _termCollectors =
		new ArrayList<TermCollector>();

}