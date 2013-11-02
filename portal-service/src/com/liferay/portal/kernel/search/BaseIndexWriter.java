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

/**
 * @author Michael C. Han
 */
public abstract class BaseIndexWriter
	implements IndexWriter, SpellCheckIndexWriter {

	@Override
	public void clearQuerySuggestionDictionaryIndexes(
			SearchContext searchContext)
		throws SearchException {

		if (_spellCheckIndexWriter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No spell check index writer configured");
			}
		}

		_spellCheckIndexWriter.clearQuerySuggestionDictionaryIndexes(
			searchContext);
	}

	@Override
	public void clearSpellCheckerDictionaryIndexes(SearchContext searchContext)
		throws SearchException {

		if (_spellCheckIndexWriter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No spell check index writer configured");
			}
		}

		_spellCheckIndexWriter.clearSpellCheckerDictionaryIndexes(
			searchContext);
	}

	@Override
	public void indexKeyword(
			SearchContext searchContext, float weight, String keywordType)
		throws SearchException {

		if (_spellCheckIndexWriter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No spell check index writer configured");
			}
		}

		_spellCheckIndexWriter.indexKeyword(searchContext, weight, keywordType);
	}

	@Override
	public void indexQuerySuggestionDictionaries(SearchContext searchContext)
		throws SearchException {

		if (_spellCheckIndexWriter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No spell check index writer configured");
			}
		}

		_spellCheckIndexWriter.indexQuerySuggestionDictionaries(searchContext);
	}

	@Override
	public void indexQuerySuggestionDictionary(SearchContext searchContext)
		throws SearchException {

		if (_spellCheckIndexWriter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No spell check index writer configured");
			}
		}

		_spellCheckIndexWriter.indexQuerySuggestionDictionary(searchContext);
	}

	@Override
	public void indexSpellCheckerDictionaries(SearchContext searchContext)
		throws SearchException {

		if (_spellCheckIndexWriter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No spell check index writer configured");
			}
		}

		_spellCheckIndexWriter.indexSpellCheckerDictionaries(searchContext);
	}

	@Override
	public void indexSpellCheckerDictionary(SearchContext searchContext)
		throws SearchException {

		if (_spellCheckIndexWriter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No spell check index writer configured");
			}
		}

		_spellCheckIndexWriter.indexSpellCheckerDictionary(searchContext);
	}

	public void setSpellCheckIndexWriter(
		SpellCheckIndexWriter spellCheckIndexWriter) {

		_spellCheckIndexWriter = spellCheckIndexWriter;
	}

	private static Log _log = LogFactoryUtil.getLog(BaseIndexWriter.class);

	private SpellCheckIndexWriter _spellCheckIndexWriter;

}