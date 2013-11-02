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

package com.liferay.portlet.blogs.action;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.struts.FindAction;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class FindEntryAction extends FindAction {

	@Override
	protected long getGroupId(long primaryKey) throws Exception {
		BlogsEntry entry = BlogsEntryLocalServiceUtil.getEntry(primaryKey);

		return entry.getGroupId();
	}

	@Override
	protected String getPrimaryKeyParameterName() {
		return "entryId";
	}

	@Override
	protected String getStrutsAction(
		HttpServletRequest request, String portletId) {

		String strutsAction = StringPool.BLANK;

		if (portletId.equals(PortletKeys.BLOGS_ADMIN)) {
			strutsAction = "/blogs_admin";
		}
		else if (portletId.equals(PortletKeys.BLOGS)) {
			strutsAction = "/blogs";
		}
		else {
			strutsAction = "/blogs_aggregator";
		}

		boolean showAllEntries = ParamUtil.getBoolean(
			request, "showAllEntries");

		if (showAllEntries) {
			strutsAction += "/view";
		}
		else {
			strutsAction += "/view_entry";
		}

		return strutsAction;
	}

	@Override
	protected String[] initPortletIds() {

		// Order is important. See LPS-23770.

		return new String[] {
			PortletKeys.BLOGS_ADMIN, PortletKeys.BLOGS,
			PortletKeys.BLOGS_AGGREGATOR
		};
	}

	@Override
	protected void setPrimaryKeyParameter(
			PortletURL portletURL, long primaryKey)
		throws Exception {

		BlogsEntry entry = BlogsEntryLocalServiceUtil.getEntry(primaryKey);

		portletURL.setParameter("urlTitle", entry.getUrlTitle());
	}

}