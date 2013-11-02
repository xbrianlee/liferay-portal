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

import com.liferay.portal.kernel.bean.ClassLoaderBeanHandler;

/**
 * @author Brian Wing Shun Chan
 */
public class ProxyFactory {

	public static Object newInstance(
			ClassLoader classLoader, Class<?> interfaceClass,
			String implClassName)
		throws Exception {

		return newInstance(
			classLoader, new Class[] {interfaceClass}, implClassName);
	}

	public static Object newInstance(
			ClassLoader classLoader, Class<?>[] interfaceClasses,
			String implClassName)
		throws Exception {

		Object instance = InstanceFactory.newInstance(
			classLoader, implClassName);

		return ProxyUtil.newProxyInstance(
			classLoader, interfaceClasses,
			new ClassLoaderBeanHandler(instance, classLoader));
	}

}