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

import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsDescriptor;
import com.liferay.portal.kernel.util.StringUtil;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Iván Zaera
 */
public class AnnotatedSettingsDescriptor implements SettingsDescriptor {

	public AnnotatedSettingsDescriptor(Class<?> settingsClass) {
		_settingsClass = settingsClass;

		Method[] methods = _getPropertyMethods();

		Stream<Method> stream = Arrays.stream(methods);

		_allKeys = Collections.unmodifiableSet(
			stream.map(
				this::_getPropertyName
			).collect(
				Collectors.toSet()
			));

		stream = Arrays.stream(methods);

		_multiValuedKeys = Collections.unmodifiableSet(
			stream.filter(
				propertyMethod ->
					propertyMethod.getReturnType() == String[].class
			).map(
				this::_getPropertyName
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

	private Method[] _getPropertyMethods() {
		List<Method> propertyMethods = new ArrayList<>();

		Method[] methods = _settingsClass.getMethods();

		for (Method method : methods) {
			Settings.Property settingsProperty = method.getAnnotation(
				Settings.Property.class);

			if ((settingsProperty != null) && settingsProperty.ignore()) {
				continue;
			}

			String name = method.getName();

			if (name.equals("getClass")) {
				continue;
			}

			if (name.startsWith("get") || name.startsWith("is")) {
				propertyMethods.add(method);
			}
		}

		return propertyMethods.toArray(new Method[0]);
	}

	private String _getPropertyName(Method propertyMethod) {
		Settings.Property settingsProperty = propertyMethod.getAnnotation(
			Settings.Property.class);

		if (settingsProperty != null) {
			String name = settingsProperty.name();

			if (!name.isEmpty()) {
				return name;
			}
		}

		String name = propertyMethod.getName();

		if (name.startsWith("get")) {
			name = name.substring(3);
		}
		else if (name.startsWith("is")) {
			name = name.substring(2);
		}
		else {
			throw new IllegalArgumentException(
				"Invalid method name for getter " + propertyMethod.getName());
		}

		return StringUtil.toLowerCase(name.substring(0, 1)) + name.substring(1);
	}

	private final Set<String> _allKeys;
	private final Set<String> _multiValuedKeys;
	private final Class<?> _settingsClass;

}