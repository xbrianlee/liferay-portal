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

package com.liferay.portal.kernel.portlet;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.WindowState;

/**
 * @author Brian Wing Shun Chan
 */
public class WindowStateFactory {

	public static WindowState getWindowState(String name) {
		return _instance._getWindowState(name);
	}

	private WindowStateFactory() {
		_windowStates = new HashMap<String, WindowState>();

		_windowStates.put(_NORMAL, LiferayWindowState.NORMAL);
		_windowStates.put(_MAXIMIZED, LiferayWindowState.MAXIMIZED);
		_windowStates.put(_MINIMIZED, LiferayWindowState.MINIMIZED);
		_windowStates.put(_EXCLUSIVE, LiferayWindowState.EXCLUSIVE);
		_windowStates.put(_POP_UP, LiferayWindowState.POP_UP);
	}

	private WindowState _getWindowState(String name) {
		WindowState windowState = _windowStates.get(name);

		if (windowState == null) {
			windowState = new WindowState(name);
		}

		return windowState;
	}

	private static final String _EXCLUSIVE =
		LiferayWindowState.EXCLUSIVE.toString();

	private static final String _MAXIMIZED = WindowState.MAXIMIZED.toString();

	private static final String _MINIMIZED = WindowState.MINIMIZED.toString();

	private static final String _NORMAL = WindowState.NORMAL.toString();

	private static final String _POP_UP = LiferayWindowState.POP_UP.toString();

	private static WindowStateFactory _instance = new WindowStateFactory();

	private Map<String, WindowState> _windowStates;

}