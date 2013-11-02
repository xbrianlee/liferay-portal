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

package com.liferay.portlet.bookmarks.util;

import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.model.BookmarksFolderConstants;
import com.liferay.portlet.bookmarks.service.BookmarksEntryServiceUtil;
import com.liferay.portlet.bookmarks.service.BookmarksFolderServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Manuel de la Peña
 */
public class BookmarksTestUtil {

	public static BookmarksEntry addEntry(boolean approved) throws Exception {
		return addEntry(TestPropsValues.getGroupId(), approved);
	}

	public static BookmarksEntry addEntry(long groupId, boolean approved)
		throws Exception {

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			groupId);

		return addEntry(
			BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID, approved,
			serviceContext);
	}

	public static BookmarksEntry addEntry(
			long folderId, boolean approved, ServiceContext serviceContext)
		throws Exception {

		boolean workflowEnabled = WorkflowThreadLocal.isEnabled();

		try {
			WorkflowThreadLocal.setEnabled(true);

			String name = "Test Entry";
			String url = "http://www.liferay.com";
			String description = "This is a test entry.";

			serviceContext = (ServiceContext)serviceContext.clone();

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);

			serviceContext.setWorkflowAction(
				WorkflowConstants.ACTION_SAVE_DRAFT);

			BookmarksEntry entry = BookmarksEntryServiceUtil.addEntry(
				serviceContext.getScopeGroupId(), folderId, name, url,
				description, serviceContext);

			if (approved) {
				entry.setStatus(WorkflowConstants.STATUS_APPROVED);

				entry = BookmarksEntryServiceUtil.updateEntry(
					entry.getEntryId(), serviceContext.getScopeGroupId(),
					entry.getFolderId(), entry.getName(), entry.getUrl(),
					entry.getUrl(), serviceContext);
			}

			return entry;
		}
		finally {
			WorkflowThreadLocal.setEnabled(workflowEnabled);
		}
	}

	public static BookmarksFolder addFolder(
			long groupId, long parentFolderId, String name)
		throws Exception {

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			groupId);

		return addFolder(parentFolderId, name, serviceContext);
	}

	public static BookmarksFolder addFolder(long groupId, String name)
		throws Exception {

		return addFolder(
			groupId, BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID, name);
	}

	public static BookmarksFolder addFolder(
			long parentFolderId, String name, ServiceContext serviceContext)
		throws Exception {

		String description = "This is a test folder.";

		return BookmarksFolderServiceUtil.addFolder(
			parentFolderId, name, description, serviceContext);
	}

	public static BookmarksFolder addFolder(String name) throws Exception {
		return addFolder(TestPropsValues.getGroupId(), name);
	}

	public static SearchContext getSearchContext(
		long companyId, long groupId, long folderId, String keywords) {

		return getSearchContext(
			companyId, groupId, folderId, keywords, false, false);
	}

	public static SearchContext getSearchContext(
		long companyId, long groupId, long folderId, String keywords,
		boolean highlight, boolean score) {

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setFolderIds(new long[] {folderId});
		searchContext.setGroupIds(new long[] {groupId});
		searchContext.setKeywords(keywords);

		QueryConfig queryConfig = new QueryConfig();

		queryConfig.setHighlightEnabled(highlight);
		queryConfig.setScoreEnabled(score);

		searchContext.setQueryConfig(queryConfig);

		return searchContext;
	}

}