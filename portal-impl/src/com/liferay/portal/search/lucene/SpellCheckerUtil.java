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

import java.io.IOException;

import java.util.List;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;

/**
 * @author Michael C. Han
 */
public class SpellCheckerUtil {

	public static boolean isValidWord(
			String localizedFieldName, String word,
			List<IndexReader> indexReaders)
		throws IOException {

		if (indexReaders.isEmpty()) {
			return false;
		}

		Term term = new Term(localizedFieldName, word);

		for (IndexReader indexReader : indexReaders) {
			if (indexReader.docFreq(term) > 0) {
				return true;
			}
		}

		return false;
	}

}