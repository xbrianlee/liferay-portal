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

package com.liferay.util.axis;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.axis.AxisEngine;
import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;

/**
 * @author Brian Wing Shun Chan
 */
public class ServletUtil {

	public static HttpServletRequest getRequest() {
		MessageContext messageContext = AxisEngine.getCurrentMessageContext();

		return (HttpServletRequest)messageContext.getProperty(
			HTTPConstants.MC_HTTP_SERVLETREQUEST);
	}

	public static HttpServlet getServlet() {
		MessageContext messageContext = AxisEngine.getCurrentMessageContext();

		return (HttpServlet)messageContext.getProperty(
			HTTPConstants.MC_HTTP_SERVLET);
	}

	public static ServletContext getServletContext() {
		return getServlet().getServletContext();
	}

	public static HttpSession getSession() {
		HttpServletRequest request = getRequest();

		return request.getSession();
	}

}