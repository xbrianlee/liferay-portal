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

package com.liferay.portal.kernel.poller.comet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public class CometHandlerPool {

	public void closeCometHandler(String sessionId) throws CometException {
		if (_log.isDebugEnabled()) {
			_log.debug("Close comet handler " + sessionId);
		}

		Lock writeLock = _cometHandlerPoolReadWriteLock.writeLock();

		try {
			writeLock.lock();

			CometHandler cometHandler = _cometHandlers.remove(sessionId);

			if (cometHandler != null) {
				cometHandler.destroy();
			}
		}
		finally {
			writeLock.unlock();
		}
	}

	public void closeCometHandlers() throws CometException {
		Lock writeLock = _cometHandlerPoolReadWriteLock.writeLock();

		try {
			writeLock.lock();

			Set<Map.Entry<String, CometHandler>> cometHandlers =
				_cometHandlers.entrySet();

			Iterator<Map.Entry<String, CometHandler>> itr =
				cometHandlers.iterator();

			while (itr.hasNext()) {
				Map.Entry<String, CometHandler> entry = itr.next();

				CometHandler cometHandler = entry.getValue();

				if (cometHandler != null) {
					cometHandler.destroy();
				}

				itr.remove();
			}
		}
		finally {
			writeLock.unlock();
		}
	}

	public CometHandler getCometHandler(String sessionId) {
		Lock readLock = _cometHandlerPoolReadWriteLock.readLock();

		try {
			readLock.lock();

			return _cometHandlers.get(sessionId);
		}
		finally {
			readLock.unlock();
		}
	}

	public void startCometHandler(
			CometSession cometSession, CometHandler cometHandler)
		throws CometException {

		String sessionId = cometSession.getSessionId();

		if (_log.isDebugEnabled()) {
			_log.debug("Start comet handler " + sessionId);
		}

		Lock writeLock = _cometHandlerPoolReadWriteLock.writeLock();

		try {
			writeLock.lock();

			if (_cometHandlers.containsKey(sessionId)) {
				closeCometHandler(sessionId);
			}

			_cometHandlers.put(sessionId, cometHandler);

			if (_log.isWarnEnabled()) {
				_log.warn("Initialize comet handler " + sessionId);
			}

			cometHandler.init(cometSession);
		}
		finally {
			writeLock.unlock();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CometHandlerPool.class);

	private ReadWriteLock _cometHandlerPoolReadWriteLock =
		new ReentrantReadWriteLock();
	private Map<String, CometHandler> _cometHandlers =
		new HashMap<String, CometHandler>();

}