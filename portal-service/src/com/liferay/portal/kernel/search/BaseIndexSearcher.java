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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public abstract class BaseIndexSearcher
	implements IndexSearcher, QuerySuggester {

	public void setQuerySuggester(QuerySuggester querySuggester) {
		_querySuggester = querySuggester;
	}

	@Override
	public String spellCheckKeywords(SearchContext searchContext)
		throws SearchException {

		if (_querySuggester == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No query suggester configured");
			}

			return StringPool.BLANK;
		}

		return _querySuggester.spellCheckKeywords(searchContext);
	}

	@Override
	public Map<String, List<String>> spellCheckKeywords(
			SearchContext searchContext, int max)
		throws SearchException {

		if (_querySuggester == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No query suggester configured");
			}

			return Collections.emptyMap();
		}

		return _querySuggester.spellCheckKeywords(searchContext, max);
	}

	@Override
	public String[] suggestKeywordQueries(SearchContext searchContext, int max)
		throws SearchException {

		if (_querySuggester == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No query suggester configured");
			}

			return StringPool.EMPTY_ARRAY;
		}

		return _querySuggester.suggestKeywordQueries(searchContext, max);
	}

	private static Log _log = LogFactoryUtil.getLog(BaseIndexSearcher.class);

	private QuerySuggester _querySuggester;

}