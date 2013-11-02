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

package com.liferay.portal.resiliency.spi;

import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.resiliency.mpi.MPI;
import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.resiliency.spi.SPIConfiguration;
import com.liferay.portal.kernel.resiliency.spi.agent.SPIAgent;
import com.liferay.portal.resiliency.spi.agent.ErrorSPIAgent;

/**
 * @author Shuyang Zhou
 */
public class ErrorSPI implements SPI {

	@Override
	public void addServlet(
		String contextPath, String docBasePath, String mappingPattern,
		String servletClassName) {
	}

	@Override
	public void addWebapp(String contextPath, String docBasePath) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public MPI getMPI() {
		return null;
	}

	@Override
	public RegistrationReference getRegistrationReference() {
		return null;
	}

	@Override
	public SPIAgent getSPIAgent() {
		return _spiAgent;
	}

	@Override
	public SPIConfiguration getSPIConfiguration() {
		return null;
	}

	@Override
	public String getSPIProviderName() {
		return null;
	}

	@Override
	public void init() {
	}

	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
	}

	private static SPIAgent _spiAgent = new ErrorSPIAgent();

}