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

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Raymond Augé
 */
public class SimpleFacet extends BaseFacet {

	public SimpleFacet(SearchContext searchContext) {
		super(searchContext);
	}

	@Override
	protected BooleanClause doGetFacetClause() {
		SearchContext searchContext = getSearchContext();

		FacetConfiguration facetConfiguration = getFacetConfiguration();

		JSONObject dataJSONObject = facetConfiguration.getData();

		String value = StringPool.BLANK;

		if (isStatic() && dataJSONObject.has("value")) {
			value = dataJSONObject.getString("value");
		}

		String valueParam = GetterUtil.getString(
			searchContext.getAttribute(getFieldId()));

		if (!isStatic() && Validator.isNotNull(valueParam)) {
			value = valueParam;
		}

		if (Validator.isNull(value)) {
			return null;
		}

		return BooleanClauseFactoryUtil.create(
			searchContext, getFieldName(), value,
			BooleanClauseOccur.MUST.getName());
	}

}