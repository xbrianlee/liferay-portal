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

import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.journal.model.JournalFolder;

import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * @author Eduardo Garcia
 */
public class FolderSearcher extends BaseIndexer {

	public static final String[] CLASS_NAMES = {
		BookmarksFolder.class.getName(), DLFolder.class.getName(),
		JournalFolder.class.getName()
	};

	public static Indexer getInstance() {
		return new FolderSearcher();
	}

	public FolderSearcher() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public IndexerPostProcessor[] getIndexerPostProcessors() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPortletId() {
		return null;
	}

	@Override
	public void registerIndexerPostProcessor(
		IndexerPostProcessor indexerPostProcessor) {

		throw new UnsupportedOperationException();
	}

	@Override
	protected BooleanQuery createFullQuery(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {

		long[] folderIds = searchContext.getFolderIds();

		BooleanQuery entryClassPKQuery = BooleanQueryFactoryUtil.create(
			searchContext);

		for (long folderId : folderIds) {
			entryClassPKQuery.addTerm(Field.ENTRY_CLASS_PK, folderId);
		}

		contextQuery.add(entryClassPKQuery, BooleanClauseOccur.MUST);

		return super.createFullQuery(contextQuery, searchContext);
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Summary doGetSummary(
			Document document, Locale locale, String snippet,
			PortletURL portletURL)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return null;
	}

}