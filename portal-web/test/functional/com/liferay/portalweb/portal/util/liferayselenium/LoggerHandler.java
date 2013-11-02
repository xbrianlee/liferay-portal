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

package com.liferay.portalweb.portal.util.liferayselenium;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Brian Wing Shun Chan
 */
public class LoggerHandler implements InvocationHandler {

	public LoggerHandler(LiferaySelenium liferaySelenium) {
		_liferaySelenium = liferaySelenium;

		_logger = new Logger(liferaySelenium);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		String methodName = method.getName();

		try {
			if (methodName.equals("getPrimaryTestSuiteName") ||
				methodName.equals("setPrimaryTestSuiteName")) {
			}
			else if (methodName.equals("sendActionLogger")) {
				_logger.logActionCommand(arguments);
			}
			else if (methodName.equals("sendLogger")) {
				_logger.send(arguments);
			}
			else if (methodName.equals("startLogger")) {
				_logger.start();
			}
			else if (methodName.equals("stopLogger")) {
				_logger.stop();
			}
			else {
				_logger.logSeleniumCommand(method, arguments);
			}

			return method.invoke(_liferaySelenium, arguments);
		}
		catch (InvocationTargetException ite) {
			Throwable throwable = ite.getCause();

			if (methodName.equals("stop") || methodName.equals("stopLogger")) {
				System.out.println("Unable to stop " + throwable.getMessage());

				return null;
			}

			_logger.logError(method, arguments, throwable);

			throw throwable;
		}
	}

	private LiferaySelenium _liferaySelenium;
	private Logger _logger;

}