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

/**
 * @author Bruno Farache
 */
public class BooleanClauseFactoryUtil {

	public static BooleanClause create(
		SearchContext searchContext, Query query, String occur) {

		return getBooleanClauseFactory(searchContext).create(
			searchContext, query, occur);
	}

	public static BooleanClause create(
		SearchContext searchContext, String field, String value, String occur) {

		return getBooleanClauseFactory(searchContext).create(
			searchContext, field, value, occur);
	}

	public static BooleanClauseFactory getBooleanClauseFactory(
		SearchContext searchContext) {

		String searchEngineId = searchContext.getSearchEngineId();

		SearchEngine searchEngine = SearchEngineUtil.getSearchEngine(
			searchEngineId);

		return searchEngine.getBooleanClauseFactory();
	}

}