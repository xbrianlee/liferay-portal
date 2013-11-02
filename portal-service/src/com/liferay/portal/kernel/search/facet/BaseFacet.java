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

package com.liferay.portal.kernel.search.facet;

import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.search.facet.util.BaseFacetValueValidator;
import com.liferay.portal.kernel.search.facet.util.FacetValueValidator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Raymond Augé
 */
public abstract class BaseFacet implements Facet {

	public BaseFacet(SearchContext searchContext) {
		_searchContext = searchContext;
	}

	@Override
	public BooleanClause getFacetClause() {
		return doGetFacetClause();
	}

	@Override
	public FacetCollector getFacetCollector() {
		return _facetCollector;
	}

	@Override
	public FacetConfiguration getFacetConfiguration() {
		return _facetConfiguration;
	}

	@Override
	public FacetValueValidator getFacetValueValidator() {
		if (_facetValueValidator == null) {
			_facetValueValidator = new BaseFacetValueValidator();
		}

		return _facetValueValidator;
	}

	@Override
	public String getFieldId() {
		return StringUtil.replace(
			getFieldName(), StringPool.SLASH, StringPool.UNDERLINE);
	}

	@Override
	public String getFieldName() {
		return _facetConfiguration.getFieldName();
	}

	@Override
	public SearchContext getSearchContext() {
		return _searchContext;
	}

	@Override
	public boolean isStatic() {
		return _facetConfiguration.isStatic();
	}

	@Override
	public void setFacetCollector(FacetCollector facetCollector) {
		_facetCollector = facetCollector;
	}

	@Override
	public void setFacetConfiguration(FacetConfiguration facetConfiguration) {
		_facetConfiguration = facetConfiguration;
	}

	@Override
	public void setFacetValueValidator(
		FacetValueValidator facetValueValidator) {

		_facetValueValidator = facetValueValidator;
	}

	@Override
	public void setFieldName(String fieldName) {
		_facetConfiguration.setFieldName(fieldName);
	}

	@Override
	public void setStatic(boolean isStatic) {
		_facetConfiguration.setStatic(isStatic);
	}

	protected abstract BooleanClause doGetFacetClause();

	private FacetCollector _facetCollector;
	private FacetConfiguration _facetConfiguration = new FacetConfiguration();
	private FacetValueValidator _facetValueValidator;
	private SearchContext _searchContext;

}