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

package com.liferay.portal.kernel.executor;

import com.liferay.portal.kernel.util.CentralizedThreadLocal;
import com.liferay.portal.kernel.util.ThreadLocalBinder;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author Shuyang Zhou
 */
public abstract class CopyThreadLocalCallable<T> implements Callable<T> {

	public CopyThreadLocalCallable(boolean readOnly, boolean clearOnExit) {
		this(null, readOnly, clearOnExit);
	}

	public CopyThreadLocalCallable(
		ThreadLocalBinder threadLocalBinder, boolean readOnly,
		boolean clearOnExit) {

		_threadLocalBinder = threadLocalBinder;

		if (_threadLocalBinder != null) {
			_threadLocalBinder.record();
		}

		if (readOnly) {
			_longLivedThreadLocals = Collections.unmodifiableMap(
				CentralizedThreadLocal.getLongLivedThreadLocals());
			_shortLivedlThreadLocals = Collections.unmodifiableMap(
				CentralizedThreadLocal.getShortLivedThreadLocals());
		}
		else {
			_longLivedThreadLocals =
				CentralizedThreadLocal.getLongLivedThreadLocals();
			_shortLivedlThreadLocals =
				CentralizedThreadLocal.getShortLivedThreadLocals();
		}

		_clearOnExit = clearOnExit;
	}

	@Override
	public final T call() throws Exception {
		CentralizedThreadLocal.setThreadLocals(
			_longLivedThreadLocals, _shortLivedlThreadLocals);

		if (_threadLocalBinder != null) {
			_threadLocalBinder.bind();
		}

		try {
			return doCall();
		}
		finally {
			if (_clearOnExit) {
				if (_threadLocalBinder != null) {
					_threadLocalBinder.cleanUp();
				}

				CentralizedThreadLocal.clearLongLivedThreadLocals();
				CentralizedThreadLocal.clearShortLivedThreadLocals();
			}
		}
	}

	public abstract T doCall() throws Exception;

	private final boolean _clearOnExit;
	private final Map<CentralizedThreadLocal<?>, Object> _longLivedThreadLocals;
	private final Map<CentralizedThreadLocal<?>, Object>
		_shortLivedlThreadLocals;
	private final ThreadLocalBinder _threadLocalBinder;

}