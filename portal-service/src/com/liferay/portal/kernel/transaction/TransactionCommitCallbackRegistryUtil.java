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

package com.liferay.portal.kernel.transaction;

import java.util.concurrent.Callable;

/**
 * @author Michael C. Han
 */
public class TransactionCommitCallbackRegistryUtil {

	public static void registerCallback(Callable<?> callable) {
		_transactionCommitCallbackRegistry.registerCallback(callable);
	}

	public void setTransactionCallbackRegistry(
		TransactionCommitCallbackRegistry transactionCommitCallbackRegistry) {

		_transactionCommitCallbackRegistry = transactionCommitCallbackRegistry;
	}

	private static TransactionCommitCallbackRegistry
		_transactionCommitCallbackRegistry;

}