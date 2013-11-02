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

import com.liferay.portal.kernel.search.facet.collector.TermCollector;

/**
 * @author Raymond Augé
 */
public class BoboTermCollector implements TermCollector {

	public BoboTermCollector(BrowseFacet browseFacet) {
		_browseFacet = browseFacet;
	}

	@Override
	public int getFrequency() {
		return _browseFacet.getFacetValueHitCount();
	}

	@Override
	public String getTerm() {
		return _browseFacet.getValue();
	}

	private BrowseFacet _browseFacet;

}