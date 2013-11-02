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
import com.liferay.portal.kernel.search.facet.util.FacetValueValidator;

/**
 * @author Raymond Augé
 */
public interface Facet {

	public BooleanClause getFacetClause();

	public FacetCollector getFacetCollector();

	public FacetConfiguration getFacetConfiguration();

	public FacetValueValidator getFacetValueValidator();

	public String getFieldId();

	public String getFieldName();

	public SearchContext getSearchContext();

	public boolean isStatic();

	public void setFacetCollector(FacetCollector facetCollector);

	public void setFacetConfiguration(FacetConfiguration facetConfiguration);

	public void setFacetValueValidator(FacetValueValidator facetValueValidator);

	public void setFieldName(String fieldName);

	public void setStatic(boolean isStatic);

}