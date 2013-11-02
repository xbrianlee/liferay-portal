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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.permission.PermissionChecker;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 */
public class DummyIndexer implements Indexer {

	@Override
	public void addRelatedEntryFields(Document document, Object obj) {
	}

	@Override
	public void delete(long companyId, String uid) {
	}

	@Override
	public void delete(Object obj) {
	}

	@Override
	public String[] getClassNames() {
		return new String[0];
	}

	@Override
	public Document getDocument(Object obj) {
		return null;
	}

	@Override
	public BooleanQuery getFacetQuery(
		String className, SearchContext searchContext) {

		return null;
	}

	@Override
	public BooleanQuery getFullQuery(SearchContext searchContext) {
		return null;
	}

	@Override
	public IndexerPostProcessor[] getIndexerPostProcessors() {
		return new IndexerPostProcessor[0];
	}

	@Override
	public String getPortletId() {
		return StringPool.BLANK;
	}

	@Override
	public String getSearchEngineId() {
		return StringPool.BLANK;
	}

	@Override
	public String getSortField(String orderByCol) {
		return StringPool.BLANK;
	}

	@Override
	public String getSortField(String orderByCol, int sortType) {
		return StringPool.BLANK;
	}

	@Override
	public Summary getSummary(
		Document document, Locale locale, String snippet,
		PortletURL portletURL) {

		return null;
	}

	@Override
	public boolean hasPermission(
		PermissionChecker permissionChecker, String entryClassName,
		long entryClassPK, String actionId) {

		return false;
	}

	@Override
	public boolean isFilterSearch() {
		return false;
	}

	@Override
	public boolean isPermissionAware() {
		return false;
	}

	@Override
	public boolean isStagingAware() {
		return false;
	}

	@Override
	public void postProcessContextQuery(
		BooleanQuery contextQuery, SearchContext searchContext) {
	}

	@Override
	public void postProcessSearchQuery(
		BooleanQuery searchQuery, SearchContext searchContext) {
	}

	@Override
	public void registerIndexerPostProcessor(
		IndexerPostProcessor indexerPostProcessor) {
	}

	@Override
	public void reindex(Object obj) {
	}

	@Override
	public void reindex(String className, long classPK) {
	}

	@Override
	public void reindex(String[] ids) {
	}

	@Override
	public void reindexDDMStructures(List<Long> ddmStructureIds) {
	}

	@Override
	public Hits search(SearchContext searchContext) {
		return null;
	}

	@Override
	public void unregisterIndexerPostProcessor(
		IndexerPostProcessor indexerPostProcessor) {
	}

}