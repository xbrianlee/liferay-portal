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

import com.swabunga.spell.engine.Word;
import com.swabunga.spell.event.SpellCheckEvent;
import com.swabunga.spell.event.SpellCheckListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class BasicSpellCheckListener implements SpellCheckListener {

	public BasicSpellCheckListener(String text) {
		_text = text;
		_textCharArray = text.toCharArray();
		_invalidWords = new ArrayList<InvalidWord>();
	}

	public List<InvalidWord> getInvalidWords() {
		return _invalidWords;
	}

	@Override
	public void spellingError(SpellCheckEvent event) {
		List<String> suggestions = new ArrayList<String>();

		for (Word word : (List<Word>)event.getSuggestions()) {
			suggestions.add(word.getWord());
		}

		int pos = event.getWordContextPosition();

		if (pos >= 0) {
			if ((pos == 0) ||
				((pos > 0) &&
				 //(_text.charAt(pos - 1) != '<') &&
				 (!_isInsideHtmlTag(pos)) &&
				 (_text.charAt(pos - 1) != '&') &&
				 (event.getInvalidWord().length() > 1))) {

				_invalidWords.add(
					new InvalidWord(
						event.getInvalidWord(), suggestions,
						event.getWordContext(), pos));
			}
		}
	}

	private boolean _isInsideHtmlTag(int pos) {
		boolean insideHtmlTag = false;

		for (int i = pos; i >= 0; i--) {
			if (_textCharArray[i] == '<') {
				insideHtmlTag = true;

				break;
			}
			else if (_textCharArray[i] == '>') {
				break;
			}
		}

		if (insideHtmlTag) {
			for (int i = pos; i < _textCharArray.length; i++) {
				if (_textCharArray[i] == '<') {
					insideHtmlTag = false;

					break;
				}
				else if (_textCharArray[i] == '>') {
					break;
				}
			}
		}

		return insideHtmlTag;
	}

	private List<InvalidWord> _invalidWords;
	private String _text;
	private char[] _textCharArray;

}