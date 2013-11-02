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

import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletURLFactoryUtil {

	public static LiferayPortletURL create(
		HttpServletRequest request, String portletId, long plid,
		String lifecycle) {

		return getPortletURLFactory().create(
			request, portletId, plid, lifecycle);
	}

	public static LiferayPortletURL create(
		PortletRequest portletRequest, String portletId, long plid,
		String lifecycle) {

		return getPortletURLFactory().create(
			portletRequest, portletId, plid, lifecycle);
	}

	public static PortletURLFactory getPortletURLFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			PortletURLFactoryUtil.class);

		return _portletURLFactory;
	}

	public void setPortletURLFactory(PortletURLFactory portletURLFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_portletURLFactory = portletURLFactory;
	}

	private static PortletURLFactory _portletURLFactory;

}