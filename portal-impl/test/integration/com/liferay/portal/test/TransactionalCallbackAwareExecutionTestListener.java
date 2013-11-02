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

package com.liferay.portal.test;

import com.liferay.portal.cache.transactional.TransactionalPortalCacheHelper;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.spring.transaction.TransactionCommitCallbackTestUtil;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Miguel Pastor
 */
public class TransactionalCallbackAwareExecutionTestListener
	extends TransactionalExecutionTestListener {

	@Override
	protected void rollbackTransaction(TransactionContext transactionContext) {
		TransactionalPortalCacheHelper.commit();

		List<Callable<?>> callables =
			TransactionCommitCallbackTestUtil.popCallbackList();

		for (Callable<?> callable : callables) {
			try {
				callable.call();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		super.rollbackTransaction(transactionContext);
	}

	@Override
	protected void startNewTransaction(TransactionContext transactionContext) {
		super.startNewTransaction(transactionContext);

		TransactionalPortalCacheHelper.begin();

		TransactionCommitCallbackTestUtil.pushCallbackList();
	}

	private static Log _log = LogFactoryUtil.getLog(
		TransactionalCallbackAwareExecutionTestListener.class);

}