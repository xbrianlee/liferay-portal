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

import org.aopalliance.intercept.MethodInvocation;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.support.TransactionCallback;

/**
 * @author Shuyang Zhou
 */
public class CounterCallbackPreferringTransactionExecutor
	extends CallbackPreferringTransactionExecutor {

	@Override
	protected TransactionCallback<Object> createTransactionCallback(
		TransactionAttribute transactionAttribute,
		MethodInvocation methodInvocation) {

		return new CounterCallbackPreferringTransactionCallback(
			transactionAttribute, methodInvocation);
	}

	private class CounterCallbackPreferringTransactionCallback
		implements TransactionCallback<Object> {

		private CounterCallbackPreferringTransactionCallback(
			TransactionAttribute transactionAttribute,
			MethodInvocation methodInvocation) {

			_transactionAttribute = transactionAttribute;
			_methodInvocation = methodInvocation;
		}

		@Override
		public Object doInTransaction(TransactionStatus transactionStatus) {
			try {
				return _methodInvocation.proceed();
			}
			catch (Throwable throwable) {
				if (_transactionAttribute.rollbackOn(throwable)) {
					if (throwable instanceof RuntimeException) {
						throw (RuntimeException)throwable;
					}
					else {
						throw new ThrowableHolderException(throwable);
					}
				}
				else {
					return new ThrowableHolder(throwable);
				}
			}
		}

		private MethodInvocation _methodInvocation;
		private TransactionAttribute _transactionAttribute;

	}

}