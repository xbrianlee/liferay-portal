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

import com.liferay.portal.dao.shard.advice.ShardAdvice;
import com.liferay.portal.kernel.dao.shard.Shard;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.util.PropsValues;

import javax.sql.DataSource;

/**
 * @author Alexander Chow
 */
@DoPrivileged
public class ShardImpl implements Shard {

	@Override
	public String[] getAvailableShardNames() {
		ShardDataSourceTargetSource shardDataSourceTargetSource =
			(ShardDataSourceTargetSource)
				InfrastructureUtil.getShardDataSourceTargetSource();

		if (shardDataSourceTargetSource != null) {
			return shardDataSourceTargetSource.getAvailableShardNames();
		}

		return null;
	}

	@Override
	public String getCurrentShardName() {
		return _shardAdvice.getCurrentShardName();
	}

	@Override
	public DataSource getDataSource() {
		return _shardAdvice.getDataSource();
	}

	@Override
	public String getDefaultShardName() {
		return PropsValues.SHARD_DEFAULT_NAME;
	}

	@Override
	public boolean isEnabled() {
		if (_shardAdvice != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String popCompanyService() {
		String value = null;

		if (_shardAdvice != null) {
			value = _shardAdvice.popCompanyService();
		}

		return value;
	}

	@Override
	public void pushCompanyService(long companyId) {
		if (_shardAdvice != null) {
			_shardAdvice.pushCompanyService(companyId);
		}
	}

	@Override
	public void pushCompanyService(String shardName) {
		if (_shardAdvice != null) {
			_shardAdvice.pushCompanyService(shardName);
		}
	}

	public void setShardAdvice(ShardAdvice shardAdvice) {
		_shardAdvice = shardAdvice;
	}

	@Override
	public String setTargetSource(String shardName) {
		if (_shardAdvice == null) {
			return null;
		}

		String currentShardName = getCurrentShardName();

		ShardDataSourceTargetSource shardDataSourceTargetSource =
			_shardAdvice.getShardDataSourceTargetSource();

		shardDataSourceTargetSource.setDataSource(shardName);

		ShardSessionFactoryTargetSource shardSessionFactoryTargetSource =
			_shardAdvice.getShardSessionFactoryTargetSource();

		shardSessionFactoryTargetSource.setSessionFactory(shardName);

		return currentShardName;
	}

	private static ShardAdvice _shardAdvice;

}