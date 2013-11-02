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

package com.liferay.portal.kernel.repository.search;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Mika Koivisto
 */
public class RepositorySearchQueryBuilderUtil {

	public static BooleanQuery getFullQuery(SearchContext searchContext)
		throws SearchException {

		return getRepositorySearchQueryBuilder().getFullQuery(searchContext);
	}

	public static RepositorySearchQueryBuilder
		getRepositorySearchQueryBuilder() {

		PortalRuntimePermission.checkGetBeanProperty(
			RepositorySearchQueryBuilderUtil.class);

		return _repositorySearchQueryBuilder;
	}

	public void setRepositorySearchQueryBuilder(
		RepositorySearchQueryBuilder repositorySearchQueryBuilder) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_repositorySearchQueryBuilder = repositorySearchQueryBuilder;
	}

	private static RepositorySearchQueryBuilder _repositorySearchQueryBuilder;

}