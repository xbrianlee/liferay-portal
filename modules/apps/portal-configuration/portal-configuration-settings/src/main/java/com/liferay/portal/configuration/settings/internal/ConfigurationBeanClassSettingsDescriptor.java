/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.configuration.settings.internal;

import com.liferay.portal.kernel.settings.SettingsDescriptor;

import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Iván Zaera
 */
public class ConfigurationBeanClassSettingsDescriptor
	implements SettingsDescriptor {

	public ConfigurationBeanClassSettingsDescriptor(
		Class<?> configurationBeanClass) {

		_configurationBeanClass = configurationBeanClass;

		Method[] methods = _configurationBeanClass.getMethods();

		Stream<Method> stream = Arrays.stream(methods);

		_allKeys = Collections.unmodifiableSet(
			stream.map(
				method -> method.getName()
			).collect(
				Collectors.toSet()
			));

		stream = Arrays.stream(methods);

		_multiValuedKeys = Collections.unmodifiableSet(
			stream.filter(
				propertyMethod ->
					propertyMethod.getReturnType() == String[].class
			).map(
				method -> method.getName()
			).collect(
				Collectors.toSet()
			));
	}

	@Override
	public Set<String> getAllKeys() {
		return _allKeys;
	}

	@Override
	public Set<String> getMultiValuedKeys() {
		return _multiValuedKeys;
	}

	private final Set<String> _allKeys;
	private final Class<?> _configurationBeanClass;
	private final Set<String> _multiValuedKeys;

}