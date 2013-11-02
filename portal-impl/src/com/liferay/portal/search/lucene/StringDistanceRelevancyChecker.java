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

package com.liferay.portal.search.lucene;

import org.apache.lucene.search.spell.StringDistance;
import org.apache.lucene.search.spell.SuggestWord;

/**
 * @author Michael C. Han
 */
public class StringDistanceRelevancyChecker implements RelevancyChecker {

	public StringDistanceRelevancyChecker(
		String word, float scoresThreshold, StringDistance stringDistance) {

		_word = word;
		_scoresThreshold = scoresThreshold;
		_stringDistance = stringDistance;
	}

	@Override
	public boolean isRelevant(SuggestWord suggestWord) {
		String word = suggestWord.string;

		if (word.equals(_word)) {
			return false;
		}

		suggestWord.score = _stringDistance.getDistance(
			_word, suggestWord.string);

		if (suggestWord.score <= _scoresThreshold) {
			return false;
		}

		return true;
	}

	private float _scoresThreshold;
	private StringDistance _stringDistance;
	private String _word;

}