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

import com.liferay.portal.kernel.messaging.proxy.BaseMultiDestinationProxyBean;
import com.liferay.portal.kernel.messaging.proxy.ProxyRequest;

import java.util.Collection;

/**
 * @author Bruno Farache
 * @author Tina Tian
 */
public class IndexWriterProxyBean extends BaseMultiDestinationProxyBean
	implements IndexWriter {

	@Override
	public void addDocument(SearchContext searchContext, Document document) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addDocuments(
		SearchContext searchContext, Collection<Document> documents) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void clearQuerySuggestionDictionaryIndexes(
		SearchContext searchContext) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void clearSpellCheckerDictionaryIndexes(
		SearchContext searchContext) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteDocument(SearchContext searchContext, String uid) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteDocuments(
		SearchContext searchContext, Collection<String> uids) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deletePortletDocuments(
		SearchContext searchContext, String portletId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public String getDestinationName(ProxyRequest proxyRequest) {
		Object[] arguments = proxyRequest.getArguments();

		SearchContext searchContext = (SearchContext)arguments[0];

		String searchEngineId = searchContext.getSearchEngineId();

		return SearchEngineUtil.getSearchWriterDestinationName(searchEngineId);
	}

	@Override
	public void indexKeyword(
		SearchContext searchContext, float weight, String keywordType) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void indexQuerySuggestionDictionaries(SearchContext searchContext) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void indexQuerySuggestionDictionary(SearchContext searchContext) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void indexSpellCheckerDictionaries(SearchContext searchContext) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void indexSpellCheckerDictionary(SearchContext searchContext) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateDocument(SearchContext searchContext, Document document) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateDocuments(
		SearchContext searchContext, Collection<Document> documents) {

		throw new UnsupportedOperationException();
	}

}