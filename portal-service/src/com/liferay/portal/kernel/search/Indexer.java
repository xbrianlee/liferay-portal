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
 * @author Raymond Augé
 * @author Ryan Park
 */
public interface Indexer {

	public static final int DEFAULT_INTERVAL = 10000;

	public void addRelatedEntryFields(Document document, Object obj)
		throws Exception;

	public void delete(long companyId, String uid) throws SearchException;

	public void delete(Object obj) throws SearchException;

	public String[] getClassNames();

	public Document getDocument(Object obj) throws SearchException;

	public BooleanQuery getFacetQuery(
			String className, SearchContext searchContext)
		throws Exception;

	public BooleanQuery getFullQuery(SearchContext searchContext)
		throws SearchException;

	public IndexerPostProcessor[] getIndexerPostProcessors();

	public String getPortletId();

	public String getSearchEngineId();

	public String getSortField(String orderByCol);

	public String getSortField(String orderByCol, int sortType);

	public Summary getSummary(
			Document document, Locale locale, String snippet,
			PortletURL portletURL)
		throws SearchException;

	public boolean hasPermission(
			PermissionChecker permissionChecker, String entryClassName,
			long entryClassPK, String actionId)
		throws Exception;

	public boolean isFilterSearch();

	public boolean isPermissionAware();

	public boolean isStagingAware();

	public void postProcessContextQuery(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception;

	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception;

	public void registerIndexerPostProcessor(
		IndexerPostProcessor indexerPostProcessor);

	public void reindex(Object obj) throws SearchException;

	public void reindex(String className, long classPK) throws SearchException;

	public void reindex(String[] ids) throws SearchException;

	public void reindexDDMStructures(List<Long> ddmStructureIds)
		throws SearchException;

	public Hits search(SearchContext searchContext) throws SearchException;

	public void unregisterIndexerPostProcessor(
		IndexerPostProcessor indexerPostProcessor);

}