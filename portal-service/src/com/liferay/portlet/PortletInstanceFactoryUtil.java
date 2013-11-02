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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.Portlet;

import javax.portlet.PortletException;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletInstanceFactoryUtil {

	public static void clear(Portlet portlet) {
		getPortletInstanceFactory().clear(portlet);
	}

	public static void clear(Portlet portlet, boolean resetRemotePortletBag) {
		getPortletInstanceFactory().clear(portlet, resetRemotePortletBag);
	}

	public static InvokerPortlet create(
			Portlet portlet, ServletContext servletContext)
		throws PortletException {

		return getPortletInstanceFactory().create(portlet, servletContext);
	}

	public static void delete(Portlet portlet) {
		getPortletInstanceFactory().delete(portlet);
	}

	public static void destroy(Portlet portlet) {
		getPortletInstanceFactory().destroy(portlet);
	}

	public static PortletInstanceFactory getPortletInstanceFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			PortletInstanceFactoryUtil.class);

		return _portletInstanceFactory;
	}

	public void destroy() {

		// LPS-10473

	}

	public void setPortletInstanceFactory(
		PortletInstanceFactory portletInstanceFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_portletInstanceFactory = portletInstanceFactory;
	}

	private static PortletInstanceFactory _portletInstanceFactory;

}