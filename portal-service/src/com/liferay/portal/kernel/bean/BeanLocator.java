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

package com.liferay.portal.kernel.bean;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Miguel Pastor
 */
public interface BeanLocator {

	public ClassLoader getClassLoader();

	public String[] getNames();

	public Class<?> getType(String name) throws BeanLocatorException;

	public <T> Map<String, T> locate(Class<T> clazz)
		throws BeanLocatorException;

	public Object locate(String name) throws BeanLocatorException;

}