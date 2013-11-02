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

import com.liferay.portal.kernel.util.Validator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Josef Sustacek
 */
public class KeywordsSuggestionHolder {

	public KeywordsSuggestionHolder(
		String suggestedKeywords, String originalKeywords) {

		this(suggestedKeywords, originalKeywords, _KEYWORDS_DELIMETER_REGEXP);
	}

	public KeywordsSuggestionHolder(
		String suggestedKeywords, String originalKeywords,
		String keywordsDelimiterRegexp) {

		Pattern keywordsDelimiterRegexpPattern = Pattern.compile(
			keywordsDelimiterRegexp);

		if (Validator.isNull(suggestedKeywords)) {
			_suggestedKeywords = Collections.emptyList();
		}
		else {
			_suggestedKeywords = Arrays.asList(
				keywordsDelimiterRegexpPattern.split(suggestedKeywords));
		}

		if (Validator.isNull(originalKeywords)) {
			_originalKeywords = Collections.emptyList();
		}
		else {
			_originalKeywords = Arrays.asList(
				keywordsDelimiterRegexpPattern.split(originalKeywords));
		}
	}

	public List<String> getSuggestedKeywords() {
		return _suggestedKeywords;
	}

	public boolean hasChanged(String suggestedKeyword) {
		return !_originalKeywords.contains(suggestedKeyword);
	}

	private static final String _KEYWORDS_DELIMETER_REGEXP = "[ ]+";

	private List<String> _originalKeywords;
	private List<String> _suggestedKeywords;

}