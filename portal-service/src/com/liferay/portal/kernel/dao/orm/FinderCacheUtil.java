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

package com.liferay.portal.kernel.dao.orm;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class FinderCacheUtil {

	public static void clearCache() {
		getFinderCache().clearCache();
	}

	public static void clearCache(String className) {
		getFinderCache().clearCache(className);
	}

	public static void clearLocalCache() {
		getFinderCache().clearLocalCache();
	}

	public static FinderCache getFinderCache() {
		PortalRuntimePermission.checkGetBeanProperty(FinderCacheUtil.class);

		return _finderCache;
	}

	public static Object getResult(
		FinderPath finderPath, Object[] args, SessionFactory sessionFactory) {

		return getFinderCache().getResult(finderPath, args, sessionFactory);
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	public static Object getResult(
		String className, String methodName, String[] params, Object[] args,
		SessionFactory sessionFactory) {

		_log.error(
			"Regenerate " + className +
				" via \"ant build-service\" or else caching will not work");

		return null;
	}

	public static void invalidate() {
		getFinderCache().invalidate();
	}

	/**
	 * @deprecated As of 6.1.0
	 */
	public static void putResult(
		boolean classNameCacheEnabled, String className, String methodName,
		String[] params, Object[] args, Object result) {

		_log.error(
			"Regenerate " + className +
				" via \"ant build-service\" or else caching will not work");
	}

	public static void putResult(
		FinderPath finderPath, Object[] args, Object result) {

		getFinderCache().putResult(finderPath, args, result);
	}

	public static void removeCache(String className) {
		getFinderCache().removeCache(className);
	}

	public static void removeResult(FinderPath finderPath, Object[] args) {
		getFinderCache().removeResult(finderPath, args);
	}

	public void setFinderCache(FinderCache finderCache) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_finderCache = finderCache;
	}

	private static Log _log = LogFactoryUtil.getLog(FinderCacheUtil.class);

	private static FinderCache _finderCache;

}