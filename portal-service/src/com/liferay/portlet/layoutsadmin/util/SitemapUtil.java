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

package com.liferay.portlet.layoutsadmin.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.theme.ThemeDisplay;

/**
 * @author Raymond Augé
 */
public class SitemapUtil {

	public static String encodeXML(String input) {
		return getSitemap().encodeXML(input);
	}

	public static Sitemap getSitemap() {
		PortalRuntimePermission.checkGetBeanProperty(SitemapUtil.class);

		return _sitemap;
	}

	public static String getSitemap(
			long groupId, boolean privateLayout, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		return getSitemap().getSitemap(groupId, privateLayout, themeDisplay);
	}

	public void setSitemap(Sitemap sitemap) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_sitemap = sitemap;
	}

	private static Sitemap _sitemap;

}