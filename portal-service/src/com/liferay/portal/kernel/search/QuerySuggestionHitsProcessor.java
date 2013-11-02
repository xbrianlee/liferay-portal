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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.util.ArrayUtil;

/**
 * @author Michael C. Han
 * @author Josef Sustacek
 */
public class QuerySuggestionHitsProcessor implements HitsProcessor {

	@Override
	public boolean process(SearchContext searchContext, Hits hits)
		throws SearchException {

		QueryConfig queryConfig = searchContext.getQueryConfig();

		if (!queryConfig.isQuerySuggestionEnabled()) {
			return true;
		}

		if (hits.getLength() >=
				queryConfig.getQuerySuggestionScoresThreshold()) {

			return true;
		}

		String[] querySuggestions = SearchEngineUtil.suggestKeywordQueries(
			searchContext, queryConfig.getQuerySuggestionMax());

		querySuggestions = ArrayUtil.remove(
			querySuggestions, searchContext.getKeywords());

		hits.setQuerySuggestions(querySuggestions);

		return true;
	}

}