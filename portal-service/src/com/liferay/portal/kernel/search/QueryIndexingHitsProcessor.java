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

import java.util.Locale;

/**
 * @author Michael C. Han
 * @author Josef Sustacek
 */
public class QueryIndexingHitsProcessor implements HitsProcessor {

	@Override
	public boolean process(SearchContext searchContext, Hits hits)
		throws SearchException {

		QueryConfig queryConfig = searchContext.getQueryConfig();

		if (!queryConfig.isQueryIndexingEnabled()) {
			return true;
		}

		if (hits.getLength() >= queryConfig.getQueryIndexingThreshold()) {
			addDocument(
				searchContext.getCompanyId(), searchContext.getKeywords(),
				searchContext.getLocale());
		}

		return true;
	}

	protected void addDocument(long companyId, String keywords, Locale locale)
		throws SearchException {

		SearchEngineUtil.indexKeyword(
			companyId, keywords, 0, SuggestionConstants.TYPE_QUERY_SUGGESTION,
			locale);
	}

}