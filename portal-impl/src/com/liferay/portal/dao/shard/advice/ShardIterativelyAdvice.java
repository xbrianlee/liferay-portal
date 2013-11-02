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

package com.liferay.portal.dao.shard.advice;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.shard.ShardUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Michael Young
 * @author Alexander Chow
 * @author Shuyang Zhou
 */
public class ShardIterativelyAdvice implements MethodInterceptor {

	/**
	 * Invoke a join point across all shards while using the company service
	 * stack.
	 *
	 * @see ShardGloballyAdvice
	 */
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Object returnValue = null;

		for (String shardName : ShardUtil.getAvailableShardNames()) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Invoking shard " + shardName + " for " +
						methodInvocation.toString());
			}

			_shardAdvice.pushCompanyService(shardName);

			try {
				Object value = methodInvocation.proceed();

				if (shardName.equals(ShardUtil.getDefaultShardName())) {
					returnValue = value;
				}
			}
			finally {
				_shardAdvice.popCompanyService();

				CacheRegistryUtil.clear();
			}
		}

		return returnValue;
	}

	public void setShardAdvice(ShardAdvice shardAdvice) {
		_shardAdvice = shardAdvice;
	}

	private static Log _log = LogFactoryUtil.getLog(
		ShardIterativelyAdvice.class);

	private ShardAdvice _shardAdvice;

}