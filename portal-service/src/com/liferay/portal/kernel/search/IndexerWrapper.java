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

import com.liferay.portal.security.permission.PermissionChecker;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 */
public class IndexerWrapper implements Indexer {

	public IndexerWrapper(Indexer indexer) {
		_indexer = indexer;
	}

	@Override
	public void addRelatedEntryFields(Document document, Object obj)
		throws Exception {

		_indexer.addRelatedEntryFields(document, obj);
	}

	@Override
	public void delete(long companyId, String uid) throws SearchException {
		_indexer.delete(companyId, uid);
	}

	@Override
	public void delete(Object obj) throws SearchException {
		_indexer.delete(obj);
	}

	@Override
	public String[] getClassNames() {
		return _indexer.getClassNames();
	}

	@Override
	public Document getDocument(Object obj) throws SearchException {
		return _indexer.getDocument(obj);
	}

	@Override
	public BooleanQuery getFacetQuery(
			String className, SearchContext searchContext)
		throws Exception {

		return _indexer.getFacetQuery(className, searchContext);
	}

	@Override
	public BooleanQuery getFullQuery(SearchContext searchContext)
		throws SearchException {

		return _indexer.getFullQuery(searchContext);
	}

	@Override
	public IndexerPostProcessor[] getIndexerPostProcessors() {
		return _indexer.getIndexerPostProcessors();
	}

	@Override
	public String getPortletId() {
		return _indexer.getPortletId();
	}

	@Override
	public String getSearchEngineId() {
		return _indexer.getSearchEngineId();
	}

	@Override
	public String getSortField(String orderByCol) {
		return _indexer.getSortField(orderByCol);
	}

	@Override
	public String getSortField(String orderByCol, int sortType) {
		return _indexer.getSortField(orderByCol, sortType);
	}

	@Override
	public Summary getSummary(
			Document document, Locale locale, String snippet,
			PortletURL portletURL)
		throws SearchException {

		return _indexer.getSummary(document, locale, snippet, portletURL);
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, String entryClassName,
			long entryClassPK, String actionId)
		throws Exception {

		return _indexer.hasPermission(
			permissionChecker, entryClassName, entryClassPK, actionId);
	}

	@Override
	public boolean isFilterSearch() {
		return _indexer.isFilterSearch();
	}

	@Override
	public boolean isPermissionAware() {
		return _indexer.isPermissionAware();
	}

	@Override
	public boolean isStagingAware() {
		return _indexer.isStagingAware();
	}

	@Override
	public void postProcessContextQuery(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {

		_indexer.postProcessContextQuery(contextQuery, searchContext);
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		_indexer.postProcessSearchQuery(searchQuery, searchContext);
	}

	@Override
	public void registerIndexerPostProcessor(
		IndexerPostProcessor indexerPostProcessor) {

		_indexer.registerIndexerPostProcessor(indexerPostProcessor);
	}

	@Override
	public void reindex(Object obj) throws SearchException {
		_indexer.reindex(obj);
	}

	@Override
	public void reindex(String className, long classPK) throws SearchException {
		_indexer.reindex(className, classPK);
	}

	@Override
	public void reindex(String[] ids) throws SearchException {
		_indexer.reindex(ids);
	}

	@Override
	public void reindexDDMStructures(List<Long> ddmStructureIds)
		throws SearchException {

		_indexer.reindexDDMStructures(ddmStructureIds);
	}

	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		return _indexer.search(searchContext);
	}

	@Override
	public void unregisterIndexerPostProcessor(
		IndexerPostProcessor indexerPostProcessor) {

		_indexer.unregisterIndexerPostProcessor(indexerPostProcessor);
	}

	private Indexer _indexer;

}