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

package com.liferay.portlet.directory.util;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.HitsOpenSearchImpl;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;
import com.liferay.portlet.usersadmin.util.UserIndexer;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Locale;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 * @author Ryan Park
 */
public class DirectoryOpenSearchImpl extends HitsOpenSearchImpl {

	public static final String SEARCH_PATH = "/c/directory/open_search";

	public static final String TITLE = "Liferay Directory Search: ";

	@Override
	public Indexer getIndexer() {
		return IndexerRegistryUtil.getIndexer(User.class);
	}

	@Override
	public String getPortletId() {
		return UserIndexer.PORTLET_ID;
	}

	@Override
	public String getSearchPath() {
		return SEARCH_PATH;
	}

	@Override
	public Summary getSummary(
			Indexer indexer, Document document, Locale locale, String snippet,
			PortletURL portletURL)
		throws SearchException {

		Summary summary = super.getSummary(
			indexer, document, locale, snippet, portletURL);

		portletURL = summary.getPortletURL();

		portletURL.setParameter("struts_action", "/directory/view_user");

		return summary;
	}

	@Override
	public String getTitle(String keywords) {
		return TITLE + keywords;
	}

	@Override
	protected PortletURL getPortletURL(
			HttpServletRequest request, String portletId, long scopeGroupId)
		throws Exception {

		return super.getPortletURL(
			request, PortletKeys.DIRECTORY, scopeGroupId);
	}

	protected LinkedHashMap<String, Object> getUserParams(
		long companyId, String keywords) {

		LinkedHashMap<String, Object> userParams =
			new LinkedHashMap<String, Object>();

		ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(
			companyId, User.class.getName());

		Enumeration<String> enu = expandoBridge.getAttributeNames();

		while (enu.hasMoreElements()) {
			String attributeName = enu.nextElement();

			UnicodeProperties properties = expandoBridge.getAttributeProperties(
				attributeName);

			int indexType = GetterUtil.getInteger(
				properties.getProperty(ExpandoColumnConstants.INDEX_TYPE));

			if (indexType != ExpandoColumnConstants.INDEX_TYPE_NONE) {
				userParams.put(attributeName, keywords);
			}
		}

		return userParams;
	}

}