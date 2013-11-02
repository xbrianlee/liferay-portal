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

package com.liferay.portlet;

import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.ReleaseInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.portlet.PortalContext;
import javax.portlet.PortletMode;
import javax.portlet.WindowState;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalContextImpl implements PortalContext {

	static Properties properties = new Properties();
	static List<PortletMode> portletModes = new ArrayList<PortletMode>();
	static List<WindowState> windowStates = new ArrayList<WindowState>();

	static {
		properties.setProperty(
			MARKUP_HEAD_ELEMENT_SUPPORT, Boolean.TRUE.toString());

		portletModes.add(PortletMode.EDIT);
		portletModes.add(PortletMode.HELP);
		portletModes.add(PortletMode.VIEW);
		portletModes.add(LiferayPortletMode.ABOUT);
		portletModes.add(LiferayPortletMode.CONFIG);
		portletModes.add(LiferayPortletMode.EDIT_DEFAULTS);
		portletModes.add(LiferayPortletMode.PREVIEW);
		portletModes.add(LiferayPortletMode.PRINT);

		windowStates.add(WindowState.MAXIMIZED);
		windowStates.add(WindowState.MINIMIZED);
		windowStates.add(WindowState.NORMAL);
		windowStates.add(LiferayWindowState.EXCLUSIVE);
		windowStates.add(LiferayWindowState.POP_UP);
	}

	public static boolean isSupportedPortletMode(PortletMode portletMode) {
		return portletModes.contains(portletMode);
	}

	public static boolean isSupportedWindowState(WindowState windowState) {
		return windowStates.contains(windowState);
	}

	@Override
	public String getPortalInfo() {
		return ReleaseInfo.getReleaseInfo();
	}

	@Override
	public String getProperty(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		return properties.getProperty(name);
	}

	@Override
	public Enumeration<String> getPropertyNames() {
		return (Enumeration<String>)properties.propertyNames();
	}

	@Override
	public Enumeration<PortletMode> getSupportedPortletModes() {
		return Collections.enumeration(portletModes);
	}

	@Override
	public Enumeration<WindowState> getSupportedWindowStates() {
		return Collections.enumeration(windowStates);
	}

}