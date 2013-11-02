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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.servlet.ServletVersionDetector;

import javax.portlet.PortletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor Spasic
 * @author Brian Wing Shun Chan
 */
public class ContextPathUtil {

	public static String getContextPath(HttpServletRequest request) {
		return getContextPath(request.getContextPath());
	}

	public static String getContextPath(PortletRequest portletRequest) {
		return getContextPath(portletRequest.getContextPath());
	}

	public static String getContextPath(ServletContext servletContext) {
		String contextPath = null;

		if (ServletVersionDetector.is2_5()) {
			contextPath = servletContext.getContextPath();
		}
		else {
			contextPath = (String)servletContext.getAttribute(WebKeys.CTX_PATH);

			if (contextPath == null) {
				contextPath = servletContext.getServletContextName();
			}
		}

		return getContextPath(contextPath);
	}

	public static String getContextPath(String contextPath) {
		contextPath = GetterUtil.getString(contextPath);

		if ((contextPath.length() == 0) ||
			contextPath.equals(StringPool.SLASH)) {

			contextPath = StringPool.BLANK;
		}
		else if (!contextPath.startsWith(StringPool.SLASH)) {
			contextPath = StringPool.SLASH.concat(contextPath);
		}

		return contextPath;
	}

}