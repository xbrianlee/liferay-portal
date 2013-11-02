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
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.QName;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletQNameUtil {

	public static String getKey(QName qName) {
		return getPortletQName().getKey(qName);
	}

	public static String getKey(String uri, String localPart) {
		return getPortletQName().getKey(uri, localPart);
	}

	public static PortletQName getPortletQName() {
		PortalRuntimePermission.checkGetBeanProperty(PortletQNameUtil.class);

		return _portletQName;
	}

	public static String getPublicRenderParameterIdentifier(
		String publicRenderParameterName) {

		return getPortletQName().getPublicRenderParameterIdentifier(
			publicRenderParameterName);
	}

	public static String getPublicRenderParameterName(QName qName) {
		return getPortletQName().getPublicRenderParameterName(qName);
	}

	public static QName getQName(
		Element qNameEl, Element nameEl, String defaultNamespace) {

		return getPortletQName().getQName(qNameEl, nameEl, defaultNamespace);
	}

	public static QName getQName(String publicRenderParameterName) {
		return getPortletQName().getQName(publicRenderParameterName);
	}

	public static String getRemovePublicRenderParameterName(QName qName) {
		return getPortletQName().getRemovePublicRenderParameterName(qName);
	}

	public static void setPublicRenderParameterIdentifier(
		String publicRenderParameterName, String identifier) {

		getPortletQName().setPublicRenderParameterIdentifier(
			publicRenderParameterName, identifier);
	}

	public void setPortletQName(PortletQName portletQName) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_portletQName = portletQName;
	}

	private static PortletQName _portletQName;

}