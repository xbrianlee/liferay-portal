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

package com.liferay.portal.dao.shard;

import com.liferay.portal.kernel.dao.shard.ShardUtil;
import com.liferay.portal.util.PortalInstances;

import java.util.Map;

/**
 * @author Alexander Chow
 */
public class RoundRobinShardSelector implements ShardSelector {

	@Override
	public String getShardName(
		String scope, String shardName, Map<String, String> params) {

		if (scope.equals(ShardSelector.COMPANY_SCOPE)) {
			String[] availableShardNames = ShardUtil.getAvailableShardNames();

			int instances = PortalInstances.getCompanyIds().length;
			int shards = availableShardNames.length;

			return availableShardNames[instances % shards];
		}
		else {
			return ShardUtil.getDefaultShardName();
		}
	}

}