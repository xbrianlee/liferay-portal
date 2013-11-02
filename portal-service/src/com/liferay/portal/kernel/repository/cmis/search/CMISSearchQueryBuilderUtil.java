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

package com.liferay.portal.kernel.repository.cmis.search;

import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Mika Koivisto
 */
public class CMISSearchQueryBuilderUtil {

	public static String buildQuery(SearchContext searchContext, Query query)
		throws SearchException {

		return getCmisSearchQueryBuilder().buildQuery(searchContext, query);
	}

	public static CMISSearchQueryBuilder getCmisSearchQueryBuilder() {
		PortalRuntimePermission.checkGetBeanProperty(
			CMISSearchQueryBuilderUtil.class);

		return _cmisSearchQueryBuilder;
	}

	public void setCmisSearchQueryBuilder(
		CMISSearchQueryBuilder cmisSearchQueryBuilder) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_cmisSearchQueryBuilder = cmisSearchQueryBuilder;
	}

	private static CMISSearchQueryBuilder _cmisSearchQueryBuilder;

}