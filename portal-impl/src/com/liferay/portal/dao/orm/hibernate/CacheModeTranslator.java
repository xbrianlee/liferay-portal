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

package com.liferay.portal.dao.orm.hibernate;

import com.liferay.portal.kernel.dao.orm.CacheMode;

/**
 * @author Brian Wing Shun Chan
 */
public class CacheModeTranslator {

	public static org.hibernate.CacheMode translate(CacheMode cacheMode) {
		if (cacheMode == CacheMode.GET) {
			return org.hibernate.CacheMode.GET;
		}
		else if (cacheMode == CacheMode.IGNORE) {
			return org.hibernate.CacheMode.IGNORE;
		}
		else if (cacheMode == CacheMode.NORMAL) {
			return org.hibernate.CacheMode.NORMAL;
		}
		else if (cacheMode == CacheMode.PUT) {
			return org.hibernate.CacheMode.PUT;
		}
		else if (cacheMode == CacheMode.REFRESH) {
			return org.hibernate.CacheMode.REFRESH;
		}
		else {
			return org.hibernate.CacheMode.parse(cacheMode.getName());
		}
	}

}