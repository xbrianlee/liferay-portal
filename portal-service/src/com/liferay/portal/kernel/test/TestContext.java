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

package com.liferay.portal.kernel.test;

import java.lang.reflect.Method;

/**
 * @author Miguel Pastor
 */
public class TestContext {

	public TestContext(Class<?> clazz) {
		_clazz = clazz;
	}

	public TestContext(Object instance, Method method) {
		_instance = instance;
		_method = method;
	}

	public Class<?> getClazz() {
		return _clazz;
	}

	public Object getInstance() {
		return _instance;
	}

	public Method getMethod() {
		return _method;
	}

	public void setInstance(Object instance) {
		_instance = instance;
	}

	public void setMethod(Method method) {
		_method = method;
	}

	private Class<?> _clazz;
	private Object _instance;
	private Method _method;

}