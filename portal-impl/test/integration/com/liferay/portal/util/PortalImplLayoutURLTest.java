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

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.theme.ThemeDisplay;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vilmos Papp
 * @author Akos Thurzo
 */
public class PortalImplLayoutURLTest extends PortalImplBaseURLTestCase {

	@Test
	public void testFromControlPanel() throws Exception {
		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, layout, VIRTUAL_HOSTNAME);

		String virtualHostnameFriendlyURL = PortalUtil.getLayoutURL(
			layout, themeDisplay, true);

		themeDisplay = initThemeDisplay(
			company, group, controlPanelLayout, VIRTUAL_HOSTNAME);

		String controlPanelFriendlyURL = PortalUtil.getLayoutURL(
			layout, themeDisplay, true);

		Assert.assertEquals(
			virtualHostnameFriendlyURL, controlPanelFriendlyURL);
	}

	@Test
	public void testNotPreserveParameters() throws Exception {
		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, layout, VIRTUAL_HOSTNAME);

		themeDisplay.setDoAsUserId("impersonated");

		String virtualHostnameFriendlyURL = PortalUtil.getLayoutURL(
			layout, themeDisplay, false);

		Assert.assertEquals(
			StringPool.BLANK,
			HttpUtil.getParameter(virtualHostnameFriendlyURL, "doAsUserId"));
	}

	@Test
	public void testPreserveParameters() throws Exception {
		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, layout, VIRTUAL_HOSTNAME);

		themeDisplay.setDoAsUserId("impersonated");

		String virtualHostnameFriendlyURL = PortalUtil.getLayoutURL(
			layout, themeDisplay, true);

		Assert.assertEquals(
			"impersonated",
			HttpUtil.getParameter(virtualHostnameFriendlyURL, "doAsUserId"));
	}

	@Test
	public void testUsingLocalhost() throws Exception {
		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, layout, VIRTUAL_HOSTNAME);

		String virtualHostnameFriendlyURL = PortalUtil.getLayoutURL(
			layout, themeDisplay, true);

		themeDisplay.setServerName(LOCALHOST);

		String localhostFriendlyURL = PortalUtil.getLayoutURL(
			layout, themeDisplay, true);

		Assert.assertEquals(localhostFriendlyURL, virtualHostnameFriendlyURL);
	}

	@Test
	public void testUsingLocalhostFromControlPanel() throws Exception {
		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, controlPanelLayout, VIRTUAL_HOSTNAME);

		String virtualHostnameFriendlyURL = PortalUtil.getLayoutURL(
			layout, themeDisplay, true);

		themeDisplay.setServerName(LOCALHOST);

		String localhostFriendlyURL = PortalUtil.getLayoutURL(
			layout, themeDisplay, true);

		Assert.assertEquals(localhostFriendlyURL, virtualHostnameFriendlyURL);
	}

	@Test
	public void testUsingLocalhostFromControlPanelOnly() throws Exception {
		ThemeDisplay themeDisplay = initThemeDisplay(
			company, group, layout, VIRTUAL_HOSTNAME);

		String virtualHostnameFriendlyURL = PortalUtil.getLayoutURL(
			layout, themeDisplay, true);

		themeDisplay = initThemeDisplay(
			company, group, controlPanelLayout, VIRTUAL_HOSTNAME);

		themeDisplay.setServerName(LOCALHOST);

		String controlPanelFriendlyURL = PortalUtil.getLayoutURL(
			layout, themeDisplay, true);

		Assert.assertEquals(
			virtualHostnameFriendlyURL, controlPanelFriendlyURL);
	}

}