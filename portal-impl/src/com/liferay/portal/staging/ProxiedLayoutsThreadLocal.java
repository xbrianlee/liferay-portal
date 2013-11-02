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

package com.liferay.portal.staging;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.model.Layout;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergio Sánchez
 */
public class ProxiedLayoutsThreadLocal {

	public static void clearProxiedLayouts() {
		_proxiedLayouts.remove();
	}

	public static Map<Layout, Object> getProxiedLayouts() {
		return _proxiedLayouts.get();
	}

	private static ThreadLocal<Map<Layout, Object>> _proxiedLayouts =
		new AutoResetThreadLocal<Map<Layout, Object>>(
			ProxiedLayoutsThreadLocal.class + "._proxiedLayouts",
			new HashMap<Layout, Object>());

}