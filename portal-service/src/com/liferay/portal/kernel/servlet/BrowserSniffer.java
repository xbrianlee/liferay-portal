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

import javax.servlet.http.HttpServletRequest;

/**
 * See http://www.zytrax.com/tech/web/browser_ids.htm for examples.
 *
 * @author Brian Wing Shun Chan
 */
public interface BrowserSniffer {

	public static final String BROWSER_ID_FIREFOX = "firefox";

	public static final String BROWSER_ID_IE = "ie";

	public static final String BROWSER_ID_OTHER = "other";

	public boolean acceptsGzip(HttpServletRequest request);

	public String getBrowserId(HttpServletRequest request);

	public float getMajorVersion(HttpServletRequest request);

	public String getRevision(HttpServletRequest request);

	public String getVersion(HttpServletRequest request);

	public boolean isAir(HttpServletRequest request);

	public boolean isAndroid(HttpServletRequest request);

	public boolean isChrome(HttpServletRequest request);

	public boolean isFirefox(HttpServletRequest request);

	public boolean isGecko(HttpServletRequest request);

	public boolean isIe(HttpServletRequest request);

	public boolean isIeOnWin32(HttpServletRequest request);

	public boolean isIeOnWin64(HttpServletRequest request);

	public boolean isIphone(HttpServletRequest request);

	public boolean isLinux(HttpServletRequest request);

	public boolean isMac(HttpServletRequest request);

	public boolean isMobile(HttpServletRequest request);

	public boolean isMozilla(HttpServletRequest request);

	public boolean isOpera(HttpServletRequest request);

	public boolean isRtf(HttpServletRequest request);

	public boolean isSafari(HttpServletRequest request);

	public boolean isSun(HttpServletRequest request);

	public boolean isWap(HttpServletRequest request);

	public boolean isWapXhtml(HttpServletRequest request);

	public boolean isWebKit(HttpServletRequest request);

	public boolean isWindows(HttpServletRequest request);

	public boolean isWml(HttpServletRequest request);

}