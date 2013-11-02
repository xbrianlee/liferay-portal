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

package com.liferay.portlet.bookmarks.action;

import com.liferay.portal.struts.FindAction;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.bookmarks.service.BookmarksEntryLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Juan Fernández
 */
public class FindEntryAction extends FindAction {

	@Override
	protected long getGroupId(long primaryKey) throws Exception {
		BookmarksEntry entry = BookmarksEntryLocalServiceUtil.getEntry(
			primaryKey);

		return entry.getGroupId();
	}

	@Override
	protected String getPrimaryKeyParameterName() {
		return "entryId";
	}

	@Override
	protected String getStrutsAction(
		HttpServletRequest request, String portletId) {

		return "/bookmarks/view_entry";
	}

	@Override
	protected String[] initPortletIds() {
		return new String[] {PortletKeys.BOOKMARKS};
	}

}