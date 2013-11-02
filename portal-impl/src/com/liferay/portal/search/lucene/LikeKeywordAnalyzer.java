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

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.KeywordAnalyzer;
import org.apache.lucene.analysis.TokenStream;

/**
 * @author Raymond Augé
 */
public class LikeKeywordAnalyzer extends Analyzer {

	public LikeKeywordAnalyzer() {
		_analyzer = new KeywordAnalyzer();
	}

	@Override
	public TokenStream tokenStream(String token, Reader reader) {
		return _analyzer.tokenStream(token, reader);
	}

	private Analyzer _analyzer;

}