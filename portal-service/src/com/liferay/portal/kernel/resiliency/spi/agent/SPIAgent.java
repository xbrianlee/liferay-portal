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

import com.liferay.portal.kernel.resiliency.PortalResiliencyException;
import com.liferay.portal.kernel.resiliency.spi.SPI;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Shuyang Zhou
 */
public interface SPIAgent {

	public void destroy();

	public void init(SPI spi) throws PortalResiliencyException;

	public HttpServletRequest prepareRequest(HttpServletRequest request)
		throws IOException;

	public HttpServletResponse prepareResponse(
		HttpServletRequest request, HttpServletResponse response);

	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalResiliencyException;

	public void transferResponse(
			HttpServletRequest request, HttpServletResponse response,
			Exception e)
		throws IOException;

	public enum Lifecycle {

		ACTION("0"), EVENT("1"), RENDER("2"), RESOURCE("3");

		public String getValue() {
			return _value;
		}

		private Lifecycle(String value) {
			_value = value;
		}

		private String _value;

	}

}