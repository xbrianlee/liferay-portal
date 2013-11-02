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

package com.liferay.portal.jericho;

import com.liferay.portal.kernel.util.ReflectionUtil;

import java.lang.reflect.Method;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.htmlparser.jericho.Config;
import net.htmlparser.jericho.Logger;
import net.htmlparser.jericho.LoggerProvider;

/**
 * @author Shuyang Zhou
 */
public class CachedLoggerProvider implements LoggerProvider {

	public static void install() throws Exception {
		Class<?> clazz = Class.forName("net.htmlparser.jericho.LoggerFactory");

		Method method = ReflectionUtil.getDeclaredMethod(
			clazz, "getDefaultLoggerProvider");

		LoggerProvider loggerProvider = (LoggerProvider)method.invoke(null);

		CachedLoggerProvider cachedLoggerProvider = new CachedLoggerProvider(
			loggerProvider);

		Config.LoggerProvider = cachedLoggerProvider;
	}

	public CachedLoggerProvider(LoggerProvider loggerProvider) {
		_loggerProvider = loggerProvider;
	}

	@Override
	public Logger getLogger(String name) {
		Logger logger = _loggers.get(name);

		if (logger == null) {
			logger = _loggerProvider.getLogger(name);

			_loggers.put(name, logger);
		}

		return logger;
	}

	private LoggerProvider _loggerProvider;
	private Map<String, Logger> _loggers =
		new ConcurrentHashMap<String, Logger>();

}