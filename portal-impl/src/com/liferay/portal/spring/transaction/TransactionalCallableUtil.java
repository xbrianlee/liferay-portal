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

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import java.util.concurrent.Callable;

import org.aopalliance.intercept.MethodInvocation;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAttribute;

/**
 * @author Shuyang Zhou
 */
public class TransactionalCallableUtil {

	public static <T> T call(
			TransactionAttribute transactionAttribute, Callable<T> callable)
		throws Throwable {

		return (T)_transactionExecutor.execute(
			_platformTransactionManager, transactionAttribute,
			new CallableMethodInvocation(callable));
	}

	public void setPlatformTransactionManager(
		PlatformTransactionManager platformTransactionManager) {

		_platformTransactionManager = platformTransactionManager;
	}

	public void setTransactionExecutor(
		TransactionExecutor transactionExecutor) {

		_transactionExecutor = transactionExecutor;
	}

	private static PlatformTransactionManager _platformTransactionManager;
	private static TransactionExecutor _transactionExecutor;

	private static class CallableMethodInvocation implements MethodInvocation {

		private CallableMethodInvocation(Callable<?> callable) {
			_callable = callable;
		}

		@Override
		public Object[] getArguments() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Method getMethod() {
			throw new UnsupportedOperationException();
		}

		@Override
		public AccessibleObject getStaticPart() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object getThis() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object proceed() throws Throwable {
			return _callable.call();
		}

		private Callable<?> _callable;

	}

}