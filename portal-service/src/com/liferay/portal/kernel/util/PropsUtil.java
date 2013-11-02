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

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public class PropsUtil {

	public static boolean contains(String key) {
		return getProps().contains(key);
	}

	public static String get(String key) {
		return getProps().get(key);
	}

	public static String get(String key, Filter filter) {
		return getProps().get(key, filter);
	}

	public static String[] getArray(String key) {
		return getProps().getArray(key);
	}

	public static String[] getArray(String key, Filter filter) {
		return getProps().getArray(key, filter);
	}

	public static Properties getProperties() {
		return getProps().getProperties();
	}

	public static Properties getProperties(
		String prefix, boolean removePrefix) {

		return getProps().getProperties(prefix, removePrefix);
	}

	public static Props getProps() {
		PortalRuntimePermission.checkGetBeanProperty(PropsUtil.class);

		return _props;
	}

	public static void setProps(Props props) {
		PortalRuntimePermission.checkSetBeanProperty(PropsUtil.class);

		_props = props;
	}

	private static Props _props;

}