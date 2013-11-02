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

package com.liferay.portal.kernel.resiliency.spi.agent;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.resiliency.spi.SPIUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Shuyang Zhou
 */
public class AcceptorServlet extends HttpServlet {

	protected void doService(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		PortalUtil.setPortalPort(request);

		ServletContext servletContext = getServletContext();

		String uriPath = PortalUtil.getPathContext();

		if (uriPath.isEmpty()) {
			uriPath = StringPool.SLASH;
		}

		ServletContext portalServletContext = servletContext.getContext(
			uriPath);

		RequestDispatcher requestDispatcher =
			portalServletContext.getRequestDispatcher("/c/portal/resiliency");

		SPI spi = SPIUtil.getSPI();

		SPIAgent spiAgent = spi.getSPIAgent();

		HttpServletRequest spiAgentHttpServletRequest = spiAgent.prepareRequest(
			request);

		HttpServletResponse spiAgentHttpServletResponse =
			spiAgent.prepareResponse(request, response);

		Exception exception = null;

		try {
			requestDispatcher.forward(
				spiAgentHttpServletRequest, spiAgentHttpServletResponse);
		}
		catch (Exception e) {
			exception = e;
		}

		spiAgent.transferResponse(
			spiAgentHttpServletRequest, spiAgentHttpServletResponse, exception);

		HttpSession session = spiAgentHttpServletRequest.getSession();

		session.invalidate();
	}

	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		try {
			doService(request, response);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw ioe;
		}
		catch (RuntimeException re) {
			_log.error(re, re);

			throw re;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AcceptorServlet.class);

}