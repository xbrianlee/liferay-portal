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

import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.resiliency.spi.SPIConfiguration;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Shuyang Zhou
 */
public class MockSPIAgent implements SPIAgent {

	public MockSPIAgent(
		SPIConfiguration spiConfiguration,
		RegistrationReference registrationReference) {
	}

	@Override
	public void destroy() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void init(SPI spi) {
		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unused")
	public HttpServletRequest prepareRequest(HttpServletRequest request)
		throws IOException {

		throw new UnsupportedOperationException();
	}

	@Override
	public HttpServletResponse prepareResponse(
		HttpServletRequest request, HttpServletResponse response) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void service(
		HttpServletRequest request, HttpServletResponse response) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void transferResponse(
		HttpServletRequest request, HttpServletResponse response, Exception e) {

		throw new UnsupportedOperationException();
	}

}