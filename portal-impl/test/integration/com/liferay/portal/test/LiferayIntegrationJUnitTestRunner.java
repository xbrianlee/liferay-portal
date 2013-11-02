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

package com.liferay.portal.test;

import com.liferay.portal.kernel.test.AbstractIntegrationJUnitTestRunner;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.util.InitUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * @author Miguel Pastor
 * @author Carlos Sierra
 * @author Shuyang Zhou
 */
public class LiferayIntegrationJUnitTestRunner
	extends AbstractIntegrationJUnitTestRunner {

	public LiferayIntegrationJUnitTestRunner(Class<?> clazz)
		throws InitializationError {

		super(clazz);
	}

	@Override
	public void initApplicationContext() {
		System.setProperty("catalina.base", ".");

		InitUtil.initWithSpring();
	}

	@Override
	protected Statement classBlock(RunNotifier notifier) {
		final Statement classBlock = super.classBlock(notifier);

		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				Thread currentThread = Thread.currentThread();

				Object inheritableThreadLocals =
					_inheritableThreadLocalsField.get(currentThread);

				if (inheritableThreadLocals != null) {
					_inheritableThreadLocalsField.set(
						currentThread,
						_createInheritedMapMethod.invoke(
							null, inheritableThreadLocals));
				}

				Object threadLocals = _threadLocalsField.get(currentThread);

				_threadLocalsField.set(currentThread, null);

				try {
					classBlock.evaluate();
				}
				finally {
					_inheritableThreadLocalsField.set(
						currentThread, inheritableThreadLocals);
					_threadLocalsField.set(currentThread, threadLocals);
				}
			}

		};
	}

	private static final Method _createInheritedMapMethod;
	private static final Field _inheritableThreadLocalsField;
	private static final Class<?> _threadLocalMapClass;
	private static final Field _threadLocalsField;

	static {
		try {
			_threadLocalMapClass = Class.forName(
				ThreadLocal.class.getName().concat("$ThreadLocalMap"));

			_createInheritedMapMethod = ReflectionUtil.getDeclaredMethod(
				ThreadLocal.class, "createInheritedMap", _threadLocalMapClass);

			_inheritableThreadLocalsField = ReflectionUtil.getDeclaredField(
				Thread.class, "inheritableThreadLocals");
			_threadLocalsField = ReflectionUtil.getDeclaredField(
				Thread.class, "threadLocals");
		}
		catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

}