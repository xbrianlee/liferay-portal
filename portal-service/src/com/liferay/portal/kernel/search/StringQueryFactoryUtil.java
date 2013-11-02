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
 * @author Brian Wing Shun Chan
 */
public class StringQueryFactoryUtil {

	public static Query create(String query) {
		return getStringQueryFactory().create(query);
	}

	public static StringQueryFactory getStringQueryFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			StringQueryFactoryUtil.class);

		return _stringQueryFactory;
	}

	public void setStringQueryFactory(StringQueryFactory stringQueryFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_stringQueryFactory = stringQueryFactory;
	}

	private static StringQueryFactory _stringQueryFactory;

}