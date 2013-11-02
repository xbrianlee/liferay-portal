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

import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Zsolt Balogh
 */
public class LiferayWindowState extends WindowState {

	public static final WindowState EXCLUSIVE = new WindowState("exclusive");

	public static final WindowState POP_UP = new WindowState("pop_up");

	public static boolean isExclusive(HttpServletRequest request) {
		String state = _getWindowState(request);

		if ((state != null) && state.equals(EXCLUSIVE.toString())) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isMaximized(HttpServletRequest request) {
		String state = _getWindowState(request);

		if ((state != null) && state.equals(WindowState.MAXIMIZED.toString())) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isPopUp(HttpServletRequest request) {
		String state = _getWindowState(request);

		if ((state != null) && state.equals(POP_UP.toString())) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isWindowStatePreserved(
		WindowState oldWindowState, WindowState newWindowState) {

		// Changes to EXCLUSIVE are always preserved

		if ((newWindowState != null) &&
			newWindowState.equals(LiferayWindowState.EXCLUSIVE)) {

			return true;
		}

		// Some window states are automatically preserved

		if ((oldWindowState != null) &&
			oldWindowState.equals(LiferayWindowState.POP_UP)) {

			return false;
		}
		else {
			return true;
		}
	}

	public LiferayWindowState(String name) {
		super(name);
	}

	private static String _getWindowState(HttpServletRequest request) {
		WindowState windowState = (WindowState)request.getAttribute(
			WebKeys.WINDOW_STATE);

		if (windowState != null) {
			return windowState.toString();
		}

		return request.getParameter("p_p_state");
	}

}