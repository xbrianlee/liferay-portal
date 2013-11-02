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

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class AlternateKeywordQueryHitsProcessor implements HitsProcessor {

	@Override
	public boolean process(SearchContext searchContext, Hits hits)
		throws SearchException {

		if (hits.getLength() > 0) {
			return true;
		}

		Map<String, List<String>> spellCheckResults =
			hits.getSpellCheckResults();

		if (spellCheckResults == null) {
			return true;
		}

		String spellCheckedKeywords = hits.getCollatedSpellCheckResult();

		searchContext.overrideKeywords(spellCheckedKeywords);

		String[] querySuggestions = SearchEngineUtil.suggestKeywordQueries(
			searchContext, 5);

		if (ArrayUtil.isNotEmpty(querySuggestions)) {
			searchContext.setKeywords(querySuggestions[0]);
		}

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHitsProcessingEnabled(false);

		Indexer indexer = FacetedSearcher.getInstance();

		Hits alternateResults = indexer.search(searchContext);

		hits.copy(alternateResults);

		return true;
	}

}