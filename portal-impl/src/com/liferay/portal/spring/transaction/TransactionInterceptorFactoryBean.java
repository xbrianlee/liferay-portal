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

package com.liferay.portal.spring.transaction;

import com.liferay.portal.dao.jdbc.aop.DynamicDataSourceAdvice;
import com.liferay.portal.dao.jdbc.aop.DynamicDataSourceTargetSource;
import com.liferay.portal.kernel.spring.util.FactoryBean;
import com.liferay.portal.kernel.util.InfrastructureUtil;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author Shuyang Zhou
 */
public class TransactionInterceptorFactoryBean
	implements FactoryBean<MethodInterceptor> {

	@Override
	public MethodInterceptor create() {
		return new TransactionInterceptor();
	}

	@Override
	public MethodInterceptor postProcessing(
		MethodInterceptor methodInterceptor) {

		TransactionInterceptor transactionInterceptor =
			(TransactionInterceptor)methodInterceptor;

		TransactionExecutor transactionExecutor =
			TransactionExecutorFactory.createTransactionExecutor(
				transactionInterceptor.platformTransactionManager, false);

		transactionInterceptor.setTransactionExecutor(transactionExecutor);

		DynamicDataSourceTargetSource dynamicDataSourceTargetSource =
			(DynamicDataSourceTargetSource)
				InfrastructureUtil.getDynamicDataSourceTargetSource();

		if (dynamicDataSourceTargetSource == null) {
			return methodInterceptor;
		}

		DynamicDataSourceAdvice dynamicDataSourceAdvice =
			new DynamicDataSourceAdvice();

		dynamicDataSourceAdvice.setDynamicDataSourceTargetSource(
			dynamicDataSourceTargetSource);
		dynamicDataSourceAdvice.setNextMethodInterceptor(methodInterceptor);

		dynamicDataSourceAdvice.setTransactionAttributeSource(
			transactionInterceptor.transactionAttributeSource);

		return dynamicDataSourceAdvice;
	}

}