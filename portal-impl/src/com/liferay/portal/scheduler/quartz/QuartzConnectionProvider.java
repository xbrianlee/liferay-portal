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

package com.liferay.portal.scheduler.quartz;

import com.liferay.portal.dao.shard.ShardDataSourceTargetSource;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.InfrastructureUtil;

import java.sql.Connection;

import javax.sql.DataSource;

import org.quartz.utils.ConnectionProvider;

/**
 * @author Bruno Farache
 */
public class QuartzConnectionProvider implements ConnectionProvider {

	@Override
	public Connection getConnection() {
		Connection con = null;

		try {
			ShardDataSourceTargetSource shardDataSourceTargetSource =
				(ShardDataSourceTargetSource)
					InfrastructureUtil.getShardDataSourceTargetSource();

			if (shardDataSourceTargetSource != null) {
				shardDataSourceTargetSource.resetDataSource();
			}

			DataSource dataSource = InfrastructureUtil.getDataSource();

			con = dataSource.getConnection();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return con;
	}

	@Override
	public void shutdown() {
	}

	private static Log _log = LogFactoryUtil.getLog(
		QuartzConnectionProvider.class);

}