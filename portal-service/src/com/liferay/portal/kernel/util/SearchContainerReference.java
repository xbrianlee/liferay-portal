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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.dao.search.SearchContainer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shinn Lok
 */
public class SearchContainerReference {

	public SearchContainerReference(
		HttpServletRequest request, String namespace) {

		_request = request;
		_namespace = namespace;

		request.setAttribute(WebKeys.SEARCH_CONTAINER_REFERENCE, this);
	}

	public String getId() {
		return getId(SearchContainer.DEFAULT_VAR);
	}

	public String getId(String var) {
		SearchContainer<?> searchContainer = _searchContainers.get(var);

		if (searchContainer == null) {
			return StringPool.BLANK;
		}

		return searchContainer.getId(_request, _namespace);
	}

	public void register(SearchContainer<?> searchContainer) {
		register(SearchContainer.DEFAULT_VAR, searchContainer);
	}

	public void register(String var, SearchContainer<?> searchContainer) {
		_searchContainers.put(var, searchContainer);
	}

	private String _namespace;
	private HttpServletRequest _request;
	private Map<String, SearchContainer<?>> _searchContainers =
		new HashMap<String, SearchContainer<?>>();

}