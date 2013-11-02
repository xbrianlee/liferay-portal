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

package com.liferay.portal.kernel.xmlrpc;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class XmlRpcUtil {

	public static Fault createFault(int code, String description) {
		return getXmlRpc().createFault(code, description);
	}

	public static Success createSuccess(String description) {
		return getXmlRpc().createSuccess(description);
	}

	public static Response executeMethod(
			String url, String methodName, Object[] arguments)
		throws XmlRpcException {

		return getXmlRpc().executeMethod(url, methodName, arguments);
	}

	public static XmlRpc getXmlRpc() {
		PortalRuntimePermission.checkGetBeanProperty(XmlRpcUtil.class);

		return _xmlRpc;
	}

	public void setXmlRpc(XmlRpc xmlRpc) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_xmlRpc = xmlRpc;
	}

	private static XmlRpc _xmlRpc;

}