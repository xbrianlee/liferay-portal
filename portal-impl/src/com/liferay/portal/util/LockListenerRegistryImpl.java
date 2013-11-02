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

package com.liferay.portal.util;

import com.liferay.portal.kernel.lock.LockListener;
import com.liferay.portal.kernel.lock.LockListenerRegistry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.PropsKeys;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Alexander Chow
 */
@DoPrivileged
public class LockListenerRegistryImpl implements LockListenerRegistry {

	public LockListenerRegistryImpl() {
		String[] lockListenerClassNames = PropsUtil.getArray(
			PropsKeys.LOCK_LISTENERS);

		for (String lockListenerClassName : lockListenerClassNames) {
			try {
				LockListener lockListener =
					(LockListener)InstanceFactory.newInstance(
						lockListenerClassName);

				register(lockListener);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	@Override
	public LockListener getLockListener(String className) {
		return _lockListeners.get(className);
	}

	@Override
	public void register(LockListener lockListener) {
		_lockListeners.put(lockListener.getClassName(), lockListener);
	}

	@Override
	public void unregister(LockListener lockListener) {
		_lockListeners.remove(lockListener.getClassName());
	}

	private static Log _log = LogFactoryUtil.getLog(
		LockListenerRegistryImpl.class);

	private Map<String, LockListener> _lockListeners =
		new ConcurrentHashMap<String, LockListener>();

}