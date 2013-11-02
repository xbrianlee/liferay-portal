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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CentralizedThreadLocal;
import com.liferay.portal.util.PropsValues;

import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.aop.TargetSource;

/**
 * @author Michael Young
 */
public class ShardDataSourceTargetSource implements TargetSource {

	public String[] getAvailableShardNames() {
		return _availableShardNames;
	}

	public DataSource getDataSource() {
		return _dataSource.get();
	}

	public Map<String, DataSource> getDataSources() {
		return _dataSources;
	}

	@Override
	public Object getTarget() throws Exception {
		return getDataSource();
	}

	@Override
	public Class<DataSource> getTargetClass() {
		return DataSource.class;
	}

	@Override
	public boolean isStatic() {
		return false;
	}

	@Override
	public void releaseTarget(Object target) throws Exception {
	}

	public void resetDataSource() {
		DataSource dataSource = _dataSources.get(
			PropsValues.SHARD_DEFAULT_NAME);

		_dataSource.set(dataSource);
	}

	public void setDataSource(String shardName) {
		DataSource dataSource = _dataSources.get(shardName);

		_dataSource.set(dataSource);
	}

	public void setDataSources(Map<String, DataSource> dataSources) {
		_dataSources = dataSources;

		Set<String> shardNames = _dataSources.keySet();

		_availableShardNames = shardNames.toArray(
			new String[shardNames.size()]);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Sharding configured with " + _availableShardNames.length +
					" data sources");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ShardDataSourceTargetSource.class);

	private static String[] _availableShardNames;

	private static ThreadLocal<DataSource> _dataSource =
		new CentralizedThreadLocal<DataSource>(false) {

		@Override
		protected DataSource initialValue() {
			return _dataSources.get(PropsValues.SHARD_DEFAULT_NAME);
		}

	};

	private static Map<String, DataSource> _dataSources;

}