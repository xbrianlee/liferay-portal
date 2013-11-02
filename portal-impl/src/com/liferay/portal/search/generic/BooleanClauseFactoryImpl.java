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

package com.liferay.portal.search.generic;

import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseFactory;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanClauseOccurImpl;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.TermQueryFactory;

/**
 * @author Bruno Farache
 */
public class BooleanClauseFactoryImpl implements BooleanClauseFactory {

	@Override
	public BooleanClause create(
		SearchContext searchContext, Query query, String occur) {

		BooleanClauseOccur booleanClauseOccur = new BooleanClauseOccurImpl(
			occur);

		return new BooleanClauseImpl(query, booleanClauseOccur);
	}

	@Override
	public BooleanClause create(
		SearchContext searchContext, String field, String value, String occur) {

		String searchEngineId = searchContext.getSearchEngineId();

		SearchEngine searchEngine = SearchEngineUtil.getSearchEngine(
			searchEngineId);

		TermQueryFactory termQueryFactory = searchEngine.getTermQueryFactory();

		Query query = termQueryFactory.create(field, value);

		BooleanClauseOccur booleanClauseOccur = new BooleanClauseOccurImpl(
			occur);

		return new BooleanClauseImpl(query, booleanClauseOccur);
	}

}