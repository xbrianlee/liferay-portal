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

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.CallbackPreferringPlatformTransactionManager;

/**
 * @author Shuyang Zhou
 */
public class TransactionExecutorFactory {

	public static TransactionExecutor createTransactionExecutor(
		PlatformTransactionManager platformTransactionManager,
		boolean counter) {

		if (counter) {
			if (platformTransactionManager instanceof
					CallbackPreferringPlatformTransactionManager) {

				return new CounterCallbackPreferringTransactionExecutor();
			}
			else {
				return new CounterTransactionExecutor();
			}
		}
		else {
			if (platformTransactionManager instanceof
					CallbackPreferringPlatformTransactionManager) {

				return new CallbackPreferringTransactionExecutor();
			}
			else {
				return new DefaultTransactionExecutor();
			}
		}
	}

}