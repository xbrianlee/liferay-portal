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

package com.liferay.portal.kernel.lock;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Lock;
import com.liferay.portal.service.LockLocalServiceUtil;

import java.util.Date;

/**
 * @author Zsolt Berentey
 */
public class LockProtectedAction<T> {

	public LockProtectedAction(
		Class<?> clazz, String lockKey, long timeout, long retryDelay) {

		_className = clazz.getName();
		_lockKey = lockKey;
		_timeout = timeout;
		_retryDelay = retryDelay;
	}

	public T getReturnValue() {
		return _returnValue;
	}

	public void performAction() throws PortalException, SystemException {
		Lock lock = null;

		while (true) {
			try {
				lock = LockLocalServiceUtil.lock(
					_className, _lockKey, _lockKey);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to acquire lock. Retrying.");
				}

				continue;
			}

			if (lock.isNew()) {
				try {
					_returnValue = performProtectedAction();
				}
				finally {
					LockLocalServiceUtil.unlock(_className, _lockKey, _lockKey);
				}

				break;
			}

			Date createDate = lock.getCreateDate();

			if ((System.currentTimeMillis() - createDate.getTime()) >=
					_timeout) {

				LockLocalServiceUtil.unlock(
					_className, _lockKey, lock.getOwner());

				if (_log.isWarnEnabled()) {
					_log.warn("Removed lock " + lock + " due to timeout");
				}
			}
			else {
				try {
					Thread.sleep(_retryDelay);
				}
				catch (InterruptedException ie) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Interrupted while waiting to reacquire lock", ie);
					}
				}
			}
		}
	}

	@SuppressWarnings("unused")
	protected T performProtectedAction()
		throws PortalException, SystemException {

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(LockProtectedAction.class);

	private String _className;
	private String _lockKey;
	private long _retryDelay;
	private T _returnValue;
	private long _timeout;

}