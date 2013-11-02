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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.aopalliance.intercept.MethodInvocation;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.interceptor.TransactionAttribute;

/**
 * @author Shuyang Zhou
 */
public class CounterTransactionExecutor extends BaseTransactionExecutor {

	@Override
	public Object execute(
			PlatformTransactionManager platformTransactionManager,
			TransactionAttribute transactionAttribute,
			MethodInvocation methodInvocation)
		throws Throwable {

		TransactionStatus transactionStatus =
			platformTransactionManager.getTransaction(transactionAttribute);

		Object returnValue = null;

		try {
			returnValue = methodInvocation.proceed();
		}
		catch (Throwable throwable) {
			processThrowable(
				platformTransactionManager, throwable, transactionAttribute,
				transactionStatus);
		}

		processCommit(platformTransactionManager, transactionStatus);

		return returnValue;
	}

	protected void processCommit(
		PlatformTransactionManager platformTransactionManager,
		TransactionStatus transactionStatus) {

		try {
			platformTransactionManager.commit(transactionStatus);
		}
		catch (TransactionSystemException tse) {
			_log.error(
				"Application exception overridden by commit exception", tse);

			throw tse;
		}
		catch (RuntimeException re) {
			_log.error(
				"Application exception overridden by commit exception", re);

			throw re;
		}
		catch (Error e) {
			_log.error("Application exception overridden by commit error", e);

			throw e;
		}
	}

	protected void processThrowable(
			PlatformTransactionManager platformTransactionManager,
			Throwable throwable, TransactionAttribute transactionAttribute,
			TransactionStatus transactionStatus)
		throws Throwable {

		if (transactionAttribute.rollbackOn(throwable)) {
			try {
				platformTransactionManager.rollback(transactionStatus);
			}
			catch (TransactionSystemException tse) {
				_log.error(
					"Application exception overridden by rollback exception",
					tse);

				throw tse;
			}
			catch (RuntimeException re) {
				_log.error(
					"Application exception overridden by rollback exception",
					re);

				throw re;
			}
			catch (Error e) {
				_log.error(
					"Application exception overridden by rollback error", e);

				throw e;
			}
		}
		else {
			processCommit(platformTransactionManager, transactionStatus);
		}

		throw throwable;
	}

	private static Log _log = LogFactoryUtil.getLog(
		CounterTransactionExecutor.class);

}