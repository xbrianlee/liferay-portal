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

package org.slf4j.impl;

import com.liferay.util.sl4fj.LiferayLoggerFactory;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

/**
 * @author Michael C. Han
 */
public class StaticLoggerBinder implements LoggerFactoryBinder {

	// To avoid constant folding by the compiler, this field must not be final
	// as required by the SLF4J API

	public static String REQUESTED_API_VERSION = "1.6.99";

	public static final StaticLoggerBinder getSingleton() {
		return _SINGLETON;
	}

	@Override
	public ILoggerFactory getLoggerFactory() {
		return _iLoggerFactory;
	}

	@Override
	public String getLoggerFactoryClassStr() {
		return _LOGGER_FACTORY_CLASS_NAME;
	}

	private StaticLoggerBinder() {
		_iLoggerFactory = new LiferayLoggerFactory();
	}

	private static final String _LOGGER_FACTORY_CLASS_NAME =
		LiferayLoggerFactory.class.getName();

	private static final StaticLoggerBinder _SINGLETON =
		new StaticLoggerBinder();

	private ILoggerFactory _iLoggerFactory;

}