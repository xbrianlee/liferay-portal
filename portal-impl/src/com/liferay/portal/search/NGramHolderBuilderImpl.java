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

package com.liferay.portal.search;

import com.liferay.portal.kernel.search.NGramHolder;
import com.liferay.portal.kernel.search.NGramHolderBuilder;
import com.liferay.portal.kernel.search.SearchException;

import java.io.StringReader;

import org.apache.lucene.analysis.ngram.NGramTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

/**
 * @author Michael C. Han
 */
public class NGramHolderBuilderImpl implements NGramHolderBuilder {

	@Override
	public NGramHolder buildNGramHolder(String input) throws SearchException {
		return buildNGramHolder(
			input, getNGramMinLength(input.length()),
			getNGramMaxLength(input.length()));
	}

	@Override
	public NGramHolder buildNGramHolder(String input, int nGramMaxLength)
		throws SearchException {

		if (nGramMaxLength <= 0) {
			nGramMaxLength = getNGramMaxLength(input.length());
		}

		return buildNGramHolder(
			input, getNGramMinLength(input.length()), nGramMaxLength);
	}

	@Override
	public NGramHolder buildNGramHolder(
			String input, int nGramMinLength, int nGramMaxLength)
		throws SearchException {

		try {
			NGramHolder nGramHolder = new NGramHolder();

			NGramTokenizer nGramTokenizer = new NGramTokenizer(
				new StringReader(input), nGramMinLength, nGramMaxLength);

			CharTermAttribute charTermAttribute = nGramTokenizer.getAttribute(
				CharTermAttribute.class);

			OffsetAttribute offsetAttribute = nGramTokenizer.getAttribute(
				OffsetAttribute.class);

			while (nGramTokenizer.incrementToken()) {
				String nGram = charTermAttribute.toString();

				int currentNGramSize = charTermAttribute.length();

				if ((currentNGramSize >= nGramMinLength) &&
					(currentNGramSize <= nGramMaxLength)) {

					if (offsetAttribute.startOffset() == 0) {
						nGramHolder.addNGramStart(currentNGramSize, nGram);
					}
					else if (offsetAttribute.endOffset() == input.length()) {
						nGramHolder.addNGramEnd(currentNGramSize, nGram);
					}
					else {
						nGramHolder.addNGram(currentNGramSize, nGram);
					}
				}
			}

			return nGramHolder;
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	protected int getNGramMaxLength(int length) {
		if (length > 5) {
			return 4;
		}
		else if (length == 5) {
			return 3;
		}

		return 2;
	}

	protected int getNGramMinLength(int length) {
		if (length > 5) {
			return 3;
		}
		else if (length == 5) {
			return 2;
		}

		return 1;
	}

}