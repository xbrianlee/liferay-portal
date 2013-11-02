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

/**
 * @author Brian Wing Shun Chan
 */
public class WindowStateFactory_IW {
	public static WindowStateFactory_IW getInstance() {
		return _instance;
	}

	public javax.portlet.WindowState getWindowState(java.lang.String name) {
		return WindowStateFactory.getWindowState(name);
	}

	private WindowStateFactory_IW() {
	}

	private static WindowStateFactory_IW _instance = new WindowStateFactory_IW();
}