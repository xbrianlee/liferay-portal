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

package com.liferay.portal.words;

import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.SecureRandomUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.util.ContentUtil;
import com.liferay.util.jazzy.BasicSpellCheckListener;
import com.liferay.util.jazzy.InvalidWord;

import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.event.DefaultWordFinder;
import com.swabunga.spell.event.SpellChecker;
import com.swabunga.spell.event.StringWordTokenizer;

import java.io.IOException;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class WordsUtil {

	public static List<InvalidWord> checkSpelling(String text) {
		return _instance._checkSpelling(text);
	}

	public static List<String> getDictionaryList() {
		return _instance._getDictionaryList();
	}

	public static Set<String> getDictionarySet() {
		return _instance._getDictionarySet();
	}

	public static String getRandomWord() {
		return _instance._getRandomWord();
	}

	public static boolean isDictionaryWord(String word) {
		return _instance._isDictionaryWord(word);
	}

	private WordsUtil() {
		_dictionaryList = ListUtil.fromArray(
			StringUtil.splitLines(
				ContentUtil.get(
					"com/liferay/portal/words/dependencies/words.txt")));

		_dictionaryList = new UnmodifiableList<String>(_dictionaryList);

		_dictionarySet = new HashSet<String>(_dictionaryList.size());

		_dictionarySet.addAll(_dictionaryList);

		_dictionarySet = Collections.unmodifiableSet(_dictionarySet);

		try {
			_spellDictionaryHashMap = new SpellDictionaryHashMap();

			String[] dics = new String[] {
				"center.dic", "centre.dic", "color.dic", "colour.dic",
				"eng_com.dic", "english.0", "english.1", "ise.dic", "ize.dic",
				"labeled.dic", "labelled.dic", "yse.dic", "yze.dic"
			};

			for (int i = 0; i < dics.length; i++) {
				_spellDictionaryHashMap.addDictionary(
					new UnsyncStringReader(
						ContentUtil.get(
							"com/liferay/portal/words/dependencies/" +
								dics[i])));
			}
		}
		catch (IOException ioe) {
			_log.error(ioe);
		}
	}

	private List<InvalidWord> _checkSpelling(String text) {
		SpellChecker spellChecker = new SpellChecker(_spellDictionaryHashMap);

		BasicSpellCheckListener basicSpellCheckListener =
			new BasicSpellCheckListener(text);

		spellChecker.addSpellCheckListener(basicSpellCheckListener);

		spellChecker.checkSpelling(
			new StringWordTokenizer(new DefaultWordFinder(text)));

		return basicSpellCheckListener.getInvalidWords();
	}

	private List<String> _getDictionaryList() {
		return _dictionaryList;
	}

	private Set<String> _getDictionarySet() {
		return _dictionarySet;
	}

	private String _getRandomWord() {
		Random random = new Random(SecureRandomUtil.nextInt());

		int pos = random.nextInt(_dictionaryList.size());

		return _dictionaryList.get(pos);
	}

	private boolean _isDictionaryWord(String word) {
		return _dictionarySet.contains(word);
	}

	private static Log _log = LogFactoryUtil.getLog(WordsUtil.class);

	private static WordsUtil _instance = new WordsUtil();

	private List<String> _dictionaryList;
	private Set<String> _dictionarySet;
	private SpellDictionaryHashMap _spellDictionaryHashMap;

}