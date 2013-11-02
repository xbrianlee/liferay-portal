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

package com.liferay.portal.kernel.resiliency.spi;

import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.resiliency.mpi.MPI;
import com.liferay.portal.kernel.resiliency.spi.agent.SPIAgent;

import java.rmi.RemoteException;

/**
 * @author Shuyang Zhou
 */
public class MockSPI implements SPI {

	@Override
	public void addServlet(
		String contextPath, String docBasePath, String mappingPattern,
		String servletClassName) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void addWebapp(String contextPath, String docBasePath) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void destroy() throws RemoteException {
		if (failOnDestroy) {
			throw new RemoteException();
		}
	}

	@Override
	public MPI getMPI() {
		return mpi;
	}

	@Override
	public RegistrationReference getRegistrationReference() {
		throw new UnsupportedOperationException();
	}

	@Override
	public SPIAgent getSPIAgent() {
		throw new UnsupportedOperationException();
	}

	@Override
	public SPIConfiguration getSPIConfiguration() throws RemoteException {
		if (failOnGetConfiguration) {
			throw new RemoteException();
		}

		return spiConfiguration;
	}

	@Override
	public String getSPIProviderName() {
		return spiProviderName;
	}

	@Override
	public void init() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isAlive() throws RemoteException {
		if (failOnIsAlive) {
			throw new RemoteException();
		}

		return true;
	}

	@Override
	public void start() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void stop() {
		throw new UnsupportedOperationException();
	}

	public boolean failOnDestroy;
	public boolean failOnGetConfiguration;
	public boolean failOnIsAlive;
	public MPI mpi;
	public SPIConfiguration spiConfiguration;
	public String spiProviderName;

}