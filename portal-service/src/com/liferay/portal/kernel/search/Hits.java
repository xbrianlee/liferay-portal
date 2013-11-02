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

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public interface Hits extends Serializable {

	public void copy(Hits hits);

	public Document doc(int n);

	public String getCollatedSpellCheckResult();

	public Document[] getDocs();

	public int getLength();

	public Query getQuery();

	public String[] getQuerySuggestions();

	public String[] getQueryTerms();

	public float[] getScores();

	public float getSearchTime();

	public String[] getSnippets();

	public Map<String, List<String>> getSpellCheckResults();

	public long getStart();

	public float score(int n);

	public void setCollatedSpellCheckResult(String collatedSpellCheckResult);

	public void setDocs(Document[] docs);

	public void setLength(int length);

	public void setQuery(Query query);

	public void setQuerySuggestions(String[] querySuggestions);

	public void setQueryTerms(String[] queryTerms);

	public void setScores(float[] scores);

	public void setScores(Float[] scores);

	public void setSearchTime(float time);

	public void setSnippets(String[] snippets);

	public void setSpellCheckResults(
		Map<String, List<String>> spellCheckResults);

	public void setStart(long start);

	public String snippet(int n);

	public List<Document> toList();

}