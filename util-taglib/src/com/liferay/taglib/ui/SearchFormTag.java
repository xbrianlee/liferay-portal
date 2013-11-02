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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class SearchFormTag<R> extends IncludeTag {

	public void setSearchContainer(SearchContainer<?> searchContainer) {
		_searchContainer = searchContainer;
	}

	public void setShowAddButton(boolean showAddButton) {
		_showAddButton = showAddButton;
	}

	@Override
	protected void cleanUp() {
		_searchContainer = null;
		_showAddButton = false;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		SearchContainerTag<R> searchContainerTag =
			(SearchContainerTag<R>)findAncestorWithClass(
				this, SearchContainerTag.class);

		if (searchContainerTag != null) {
			_searchContainer = searchContainerTag.getSearchContainer();
		}

		request.setAttribute(
			"liferay-ui:search:searchContainer", _searchContainer);
		request.setAttribute(
			"liferay-ui:search:showAddButton", String.valueOf(_showAddButton));
	}

	private SearchContainer<?> _searchContainer;
	private boolean _showAddButton;

}