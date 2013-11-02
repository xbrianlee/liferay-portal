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

import com.liferay.counter.service.persistence.CounterFinder;
import com.liferay.counter.service.persistence.CounterPersistence;
import com.liferay.portal.dao.shard.ShardDataSourceTargetSource;
import com.liferay.portal.dao.shard.ShardSessionFactoryTargetSource;
import com.liferay.portal.kernel.dao.shard.ShardUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.CompanyPersistence;
import com.liferay.portal.service.persistence.PortalPreferencesPersistence;
import com.liferay.portal.service.persistence.ReleasePersistence;
import com.liferay.portal.service.persistence.ResourceActionPersistence;
import com.liferay.portal.service.persistence.ServiceComponentPersistence;
import com.liferay.portal.service.persistence.ShardPersistence;
import com.liferay.portal.service.persistence.VirtualHostPersistence;
import com.liferay.portal.util.PropsValues;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Michael Young
 * @author Alexander Chow
 * @author Shuyang Zhou
 */
public class ShardPersistenceAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		ShardDataSourceTargetSource shardDataSourceTargetSource =
			_shardAdvice.getShardDataSourceTargetSource();
		ShardSessionFactoryTargetSource shardSessionFactoryTargetSource =
			_shardAdvice.getShardSessionFactoryTargetSource();

		if ((shardDataSourceTargetSource == null) ||
			(shardSessionFactoryTargetSource == null)) {

			return methodInvocation.proceed();
		}

		Object target = methodInvocation.getThis();

		if (target instanceof ClassNamePersistence ||
			target instanceof CompanyPersistence ||
			target instanceof CounterFinder ||
			target instanceof CounterPersistence ||
			target instanceof PortalPreferencesPersistence ||
			target instanceof ReleasePersistence ||
			target instanceof ResourceActionPersistence ||
			target instanceof ServiceComponentPersistence ||
			target instanceof ShardPersistence ||
			target instanceof VirtualHostPersistence) {

			String currentShardName = ShardUtil.setTargetSource(
				PropsValues.SHARD_DEFAULT_NAME);

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Using default shard for " + methodInvocation.toString());
			}

			_shardAdvice.pushCompanyService(PropsValues.SHARD_DEFAULT_NAME);

			try {
				return methodInvocation.proceed();
			}
			finally {
				_shardAdvice.popCompanyService();

				ShardUtil.setTargetSource(currentShardName);
			}
		}

		if (_shardAdvice.getGlobalCall() == null) {
			String shardName = _shardAdvice.setShardNameByCompany();

			ShardUtil.setTargetSource(shardName);

			if (_log.isInfoEnabled()) {
				_log.info(
					"Using shard name " + shardName + " for " +
						methodInvocation.toString());
			}

			return methodInvocation.proceed();
		}
		else {
			return methodInvocation.proceed();
		}
	}

	public void setShardAdvice(ShardAdvice shardAdvice) {
		_shardAdvice = shardAdvice;
	}

	private static Log _log = LogFactoryUtil.getLog(
		ShardPersistenceAdvice.class);

	private ShardAdvice _shardAdvice;

}