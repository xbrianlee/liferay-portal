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

package com.liferay.util.jazzy;

import java.io.Serializable;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class InvalidWord implements Serializable {

	public InvalidWord(
		String invalidWord, List<String> suggestions, String wordContext,
		int wordContextPosition) {

		_invalidWord = invalidWord;
		_suggestions = suggestions;
		_wordContext = wordContext;
		_wordContextPosition = wordContextPosition;
	}

	public String getInvalidWord() {
		return _invalidWord;
	}

	public List<String> getSuggestions() {
		return _suggestions;
	}

	public String getWordContext() {
		return _wordContext;
	}

	public int getWordContextPosition() {
		return _wordContextPosition;
	}

	private String _invalidWord;
	private List<String> _suggestions;
	private String _wordContext;
	private int _wordContextPosition;

}