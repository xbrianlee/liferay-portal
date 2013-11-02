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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;

/**
 * <p>
 * A class loader proxy able to serialize simple POJOs between two class
 * loaders. It only works for simple POJOs following the Java Beans semantics.
 * The local and remote classes do not have to match or even be derived from
 * each other as long as their properties match. Any bean properties that the
 * source bean exposes but the target bean does not will silently be ignored.
 * </p>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 */
public class SimplePojoClp<T> {

	public SimplePojoClp(
			Class<? extends T> localImplementationClass,
			ClassLoader remoteClassLoader)
		throws ClassNotFoundException {

		this(
			localImplementationClass, remoteClassLoader,
			localImplementationClass.getName());
	}

	public SimplePojoClp(
			Class<? extends T> localImplementationClass,
			ClassLoader remoteClassLoader, String remoteImplementationClassName)
		throws ClassNotFoundException {

		_localImplementationClass = localImplementationClass;
		_remoteClassLoader = remoteClassLoader;
		_remoteImplementationClass = _remoteClassLoader.loadClass(
			remoteImplementationClassName);
	}

	public T createLocalObject(Object remoteInstance)
		throws IllegalAccessException, InstantiationException {

		T localInstance = _localImplementationClass.newInstance();

		BeanPropertiesUtil.copyProperties(
			remoteInstance, localInstance, _localImplementationClass);

		return localInstance;
	}

	public Object createRemoteObject(T localInstance)
		throws IllegalAccessException, InstantiationException {

		Object remoteInstance = _remoteImplementationClass.newInstance();

		BeanPropertiesUtil.copyProperties(
			localInstance, remoteInstance, _remoteImplementationClass);

		return remoteInstance;
	}

	private Class<? extends T> _localImplementationClass;
	private ClassLoader _remoteClassLoader;
	private Class<?> _remoteImplementationClass;

}