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

package com.liferay.portal.kernel.dao.shard;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.StringPool;

import javax.sql.DataSource;

/**
 * @author Alexander Chow
 * @author Raymond Augé
 */
public class ShardUtil {

	public static String[] getAvailableShardNames() {
		Shard shard = getShard();

		if (shard != null) {
			String[] availableShardNames = shard.getAvailableShardNames();

			if (availableShardNames != null) {
				return availableShardNames;
			}
		}

		return _DEFAULT_SHARD_ARRAY;
	}

	public static String getCurrentShardName() {
		Shard shard = getShard();

		if (shard != null) {
			return shard.getCurrentShardName();
		}

		return StringPool.BLANK;
	}

	public static DataSource getDataSource() {
		Shard shard = getShard();

		if (shard != null) {
			return shard.getDataSource();
		}

		return null;
	}

	public static String getDefaultShardName() {
		Shard shard = getShard();

		if (shard != null) {
			return shard.getDefaultShardName();
		}

		return null;
	}

	public static Shard getShard() {
		PortalRuntimePermission.checkGetBeanProperty(ShardUtil.class);

		return _shard;
	}

	public static boolean isEnabled() {
		Shard shard = getShard();

		if (shard != null) {
			return shard.isEnabled();
		}

		return false;
	}

	public static String popCompanyService() {
		String value = null;

		Shard shard = getShard();

		if (shard != null) {
			value = shard.popCompanyService();
		}

		return value;
	}

	public static void pushCompanyService(long companyId) {
		Shard shard = getShard();

		if (shard != null) {
			shard.pushCompanyService(companyId);
		}
	}

	public static void pushCompanyService(String shardName) {
		Shard shard = getShard();

		if (shard != null) {
			shard.pushCompanyService(shardName);
		}
	}

	public static String setTargetSource(String shardName) {
		String value = null;

		Shard shard = getShard();

		if (shard != null) {
			value = shard.setTargetSource(shardName);
		}

		return value;
	}

	public void setShard(Shard shard) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_shard = shard;
	}

	private static final String[] _DEFAULT_SHARD_ARRAY = new String[0];

	private static Shard _shard;

}