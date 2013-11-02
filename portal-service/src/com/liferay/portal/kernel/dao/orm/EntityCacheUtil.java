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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class EntityCacheUtil {

	public static void clearCache() {
		getEntityCache().clearCache();
	}

	public static void clearCache(String className) {
		getEntityCache().clearCache(className);
	}

	public static void clearLocalCache() {
		getEntityCache().clearLocalCache();
	}

	public static EntityCache getEntityCache() {
		PortalRuntimePermission.checkGetBeanProperty(EntityCacheUtil.class);

		return _entityCache;
	}

	public static Serializable getResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey) {

		return getEntityCache().getResult(
			entityCacheEnabled, clazz, primaryKey);
	}

	public static void invalidate() {
		getEntityCache().invalidate();
	}

	public static Serializable loadResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey,
		SessionFactory sessionFactory) {

		return getEntityCache().loadResult(
			entityCacheEnabled, clazz, primaryKey, sessionFactory);
	}

	public static void putResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey,
		Serializable result) {

		getEntityCache().putResult(
			entityCacheEnabled, clazz, primaryKey, result);
	}

	public static void removeCache(String className) {
		getEntityCache().removeCache(className);
	}

	public static void removeResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey) {

		getEntityCache().removeResult(entityCacheEnabled, clazz, primaryKey);
	}

	public void setEntityCache(EntityCache entityCache) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_entityCache = entityCache;
	}

	private static EntityCache _entityCache;

}