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

package com.liferay.portlet.bookmarks.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class BookmarksEntryImpl extends BookmarksEntryBaseImpl {

	public BookmarksEntryImpl() {
	}

	@Override
	public String buildTreePath() throws PortalException, SystemException {
		BookmarksFolder folder = getFolder();

		return folder.buildTreePath();
	}

	@Override
	public BookmarksFolder getFolder() throws PortalException, SystemException {
		if (getFolderId() <= 0) {
			return new BookmarksFolderImpl();
		}

		return BookmarksFolderLocalServiceUtil.getFolder(getFolderId());
	}

}