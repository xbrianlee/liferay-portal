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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.util.InitialThreadLocal;

/**
 * @author Tina Tian
 */
public class PluginContextLifecycleThreadLocal {

	public static boolean isDestroying() {
		return _destroying.get();
	}

	public static boolean isInitializing() {
		return _initializing.get();
	}

	public static void setDestroying(boolean destroying) {
		_destroying.set(destroying);
	}

	public static void setInitializing(boolean initializing) {
		_initializing.set(initializing);
	}

	private static ThreadLocal<Boolean> _destroying =
		new InitialThreadLocal<Boolean>(
			PluginContextLifecycleThreadLocal.class + "._destroying",
			Boolean.FALSE);
	private static ThreadLocal<Boolean> _initializing =
		new InitialThreadLocal<Boolean>(
			PluginContextLifecycleThreadLocal.class + "._initializing",
			Boolean.FALSE);

}