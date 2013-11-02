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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class NGramHolder {

	public void addNGram(int number, String gram) {
		String key = "gram" + number;

		List<String> grams = _nGrams.get(key);

		if (grams == null) {
			grams = new ArrayList<String>();

			_nGrams.put(key, grams);
		}

		grams.add(gram);
	}

	public void addNGramEnd(int number, String gram) {
		_nGramEnds.put("end" + number, gram);
	}

	public void addNGramStart(int number, String gram) {
		_nGramStarts.put("start" + number, gram);
	}

	public Map<String, String> getNGramEnds() {
		return _nGramEnds;
	}

	public Map<String, List<String>> getNGrams() {
		return _nGrams;
	}

	public Map<String, String> getNGramStarts() {
		return _nGramStarts;
	}

	private Map<String, String> _nGramEnds = new HashMap<String, String>();
	private Map<String, List<String>> _nGrams =
		new HashMap<String, List<String>>();
	private Map<String, String> _nGramStarts = new HashMap<String, String>();

}