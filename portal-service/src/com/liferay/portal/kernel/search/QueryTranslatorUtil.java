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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Raymond Augé
 */
public class QueryTranslatorUtil {

	public static QueryTranslator getQueryTranslator() {
		PortalRuntimePermission.checkGetBeanProperty(QueryTranslatorUtil.class);

		return _queryTranslator;
	}

	public static Object translate(Query query) throws ParseException {
		return getQueryTranslator().translate(query);
	}

	public static Object translateForSolr(Query query) throws ParseException {
		return getQueryTranslator().translateForSolr(query);
	}

	public void setQueryTranslator(QueryTranslator queryTranslator) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_queryTranslator = queryTranslator;
	}

	private static QueryTranslator _queryTranslator;

}