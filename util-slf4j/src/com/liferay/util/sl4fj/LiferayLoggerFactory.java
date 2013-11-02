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

package com.liferay.util.sl4fj;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * @author Michael C. Han
 */
public class LiferayLoggerFactory implements ILoggerFactory {

	public LiferayLoggerFactory() {
		ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

		_readLock = readWriteLock.readLock();
		_writeLock = readWriteLock.writeLock();
	}

	@Override
	public Logger getLogger(String name) {
		Logger logger = null;

		_readLock.lock();

		try {
			logger = _loggers.get(name);
		}
		finally {
			_readLock.unlock();
		}

		if (logger == null) {
			_writeLock.lock();

			try {
				Log log = LogFactoryUtil.getLog(name);

				logger = new LiferayLoggerAdapter(log);

				_loggers.put(name, logger);
			}
			finally {
				_writeLock.unlock();
			}
		}

		return logger;
	}

	private Map<String, Logger> _loggers = new HashMap<String, Logger>();
	private Lock _readLock;
	private Lock _writeLock;

}