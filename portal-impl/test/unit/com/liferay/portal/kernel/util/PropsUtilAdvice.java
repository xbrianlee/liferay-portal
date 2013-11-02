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

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author Shuyang Zhou
 */
@Aspect
public class PropsUtilAdvice {

	public static Map<String, String> getPropsMap() {
		return _propsMap;
	}

	public static void setProps(String name, String value) {
		_propsMap.put(name, value);
	}

	public static void setPropsMap(Map<String, String> propsMap) {
		_propsMap = propsMap;
	}

	@Around(
		"execution(public static String com.liferay.portal.kernel.util." +
			"PropsUtil.get(String)) && args(key)")
	public String get(String key) {
		return _propsMap.get(key);
	}

	private static Map<String, String> _propsMap =
		new HashMap<String, String>();

}