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
 * @author Michael C. Han
 */
public interface SpellCheckIndexWriter {

	public void clearQuerySuggestionDictionaryIndexes(
			SearchContext searchContext)
		throws SearchException;

	public void clearSpellCheckerDictionaryIndexes(SearchContext searchContext)
		throws SearchException;

	public void indexKeyword(
			SearchContext searchContext, float weight, String keywordType)
		throws SearchException;

	public void indexQuerySuggestionDictionaries(SearchContext searchContext)
		throws SearchException;

	public void indexQuerySuggestionDictionary(SearchContext searchContext)
		throws SearchException;

	public void indexSpellCheckerDictionaries(SearchContext searchContext)
		throws SearchException;

	public void indexSpellCheckerDictionary(SearchContext searchContext)
		throws SearchException;

}