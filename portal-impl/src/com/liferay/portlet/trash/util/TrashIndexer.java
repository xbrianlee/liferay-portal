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

package com.liferay.portlet.trash.util;

import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.trash.model.TrashEntry;

import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * @author Julio Camarero
 * @author Zsolt Berentey
 */
public class TrashIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {TrashEntry.class.getName()};

	public static final String PORTLET_ID = PortletKeys.TRASH;

	public TrashIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public BooleanQuery getFullQuery(SearchContext searchContext)
		throws SearchException {

		try {
			BooleanQuery contextQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			contextQuery.addRequiredTerm(
				Field.COMPANY_ID, searchContext.getCompanyId());

			BooleanQuery excludeAttachmentsQuery =
				BooleanQueryFactoryUtil.create(searchContext);

			excludeAttachmentsQuery.addRequiredTerm(
				Field.ENTRY_CLASS_NAME, DLFileEntryConstants.getClassName());
			excludeAttachmentsQuery.addRequiredTerm(Field.HIDDEN, true);

			contextQuery.add(
				excludeAttachmentsQuery, BooleanClauseOccur.MUST_NOT);

			BooleanQuery excludeJournalArticleVersionsQuery =
				BooleanQueryFactoryUtil.create(searchContext);

			excludeJournalArticleVersionsQuery.addRequiredTerm(
				Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());

			excludeJournalArticleVersionsQuery.addRequiredTerm("head", false);

			contextQuery.add(
				excludeJournalArticleVersionsQuery,
				BooleanClauseOccur.MUST_NOT);

			BooleanQuery groupQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			for (long groupId : searchContext.getGroupIds()) {
				groupQuery.addTerm(
					Field.GROUP_ID, String.valueOf(groupId), false,
					BooleanClauseOccur.SHOULD);
			}

			contextQuery.add(groupQuery, BooleanClauseOccur.MUST);

			contextQuery.addRequiredTerm(
				Field.STATUS, WorkflowConstants.STATUS_IN_TRASH);

			BooleanQuery fullQuery = createFullQuery(
				contextQuery, searchContext);

			return fullQuery;
		}
		catch (SearchException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	@Override
	public String getPortletId() {
		return PORTLET_ID;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, String entryClassName,
			long entryClassPK, String actionId)
		throws Exception {

		TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(
			entryClassName);

		return trashHandler.hasTrashPermission(
			permissionChecker, 0, entryClassPK, actionId);
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		if (searchContext.getAttributes() == null) {
			return;
		}

		addSearchTerm(searchQuery, searchContext, Field.CONTENT, true);
		addSearchTerm(
			searchQuery, searchContext, Field.REMOVED_BY_USER_NAME, true);
		addSearchTerm(searchQuery, searchContext, Field.TITLE, true);
		addSearchTerm(searchQuery, searchContext, Field.TYPE, false);
		addSearchTerm(searchQuery, searchContext, Field.USER_NAME, true);
	}

	@Override
	protected void doDelete(Object obj) {
	}

	@Override
	protected Document doGetDocument(Object obj) {
		return null;
	}

	@Override
	protected String doGetSortField(String orderByCol) {
		if (orderByCol.equals("removed-date")) {
			return Field.REMOVED_DATE;
		}
		else if (orderByCol.equals("removed-by")) {
			return Field.REMOVED_BY_USER_NAME;
		}
		else {
			return orderByCol;
		}
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletURL portletURL) {

		return null;
	}

	@Override
	protected void doReindex(Object obj) {
	}

	@Override
	protected void doReindex(String className, long classPK) {
	}

	@Override
	protected void doReindex(String[] ids) {
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

}