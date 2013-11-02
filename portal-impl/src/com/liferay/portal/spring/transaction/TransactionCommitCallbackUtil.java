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

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Shuyang Zhou
 */
class TransactionCommitCallbackUtil {

	public static void registerCallback(Callable<?> callable) {
		List<List<Callable<?>>> callbackListList =
			_callbackListListThreadLocal.get();

		if (callbackListList.isEmpty()) {

			// Not within a transaction boundary, should only happen during an
			// upgrade and verify process. See DBUpgrader#_disableTransactions.

			try {
				callable.call();
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		else {
			int index = callbackListList.size() - 1;

			List<Callable<?>> callableList = callbackListList.get(index);

			if (callableList == Collections.<Callable<?>>emptyList()) {
				callableList = new ArrayList<Callable<?>>();

				callbackListList.set(index, callableList);
			}

			callableList.add(callable);
		}
	}

	protected static List<Callable<?>> popCallbackList() {
		List<List<Callable<?>>> callbackListList =
			_callbackListListThreadLocal.get();

		return callbackListList.remove(callbackListList.size() - 1);
	}

	protected static void pushCallbackList() {
		List<List<Callable<?>>> callbackListList =
			_callbackListListThreadLocal.get();

		callbackListList.add(Collections.<Callable<?>>emptyList());
	}

	private static ThreadLocal<List<List<Callable<?>>>>
		_callbackListListThreadLocal =
			new AutoResetThreadLocal<List<List<Callable<?>>>>(
				TransactionCommitCallbackUtil.class +
					"._callbackListListThreadLocal") {

				@Override
				protected List<List<Callable<?>>> initialValue() {
					return new ArrayList<List<Callable<?>>>();
				}

			};

}