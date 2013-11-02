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
public class PortletModeFactory_IW {
	public static PortletModeFactory_IW getInstance() {
		return _instance;
	}

	public javax.portlet.PortletMode getPortletMode(java.lang.String name) {
		return PortletModeFactory.getPortletMode(name);
	}

	private PortletModeFactory_IW() {
	}

	private static PortletModeFactory_IW _instance = new PortletModeFactory_IW();
}