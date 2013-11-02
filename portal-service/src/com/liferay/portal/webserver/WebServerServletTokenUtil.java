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

package com.liferay.portal.webserver;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 * @since  6.1, replaced com.liferay.portal.kernel.servlet.ImageServletTokenUtil
 */
public class WebServerServletTokenUtil {

	public static String getToken(long imageId) {
		return getWebServerServletToken().getToken(imageId);
	}

	public static WebServerServletToken getWebServerServletToken() {
		PortalRuntimePermission.checkGetBeanProperty(
			WebServerServletTokenUtil.class);

		return _webServerServletToken;
	}

	public static void resetToken(long imageId) {
		getWebServerServletToken().resetToken(imageId);
	}

	public void setWebServerServletToken(
		WebServerServletToken webServerServletToken) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_webServerServletToken = webServerServletToken;
	}

	private static WebServerServletToken _webServerServletToken;

}