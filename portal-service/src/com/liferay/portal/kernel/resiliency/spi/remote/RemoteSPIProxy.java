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

package com.liferay.portal.kernel.resiliency.spi.remote;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.resiliency.PortalResiliencyException;
import com.liferay.portal.kernel.resiliency.mpi.MPI;
import com.liferay.portal.kernel.resiliency.mpi.MPIHelperUtil;
import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.resiliency.spi.SPIConfiguration;
import com.liferay.portal.kernel.resiliency.spi.agent.SPIAgent;
import com.liferay.portal.kernel.resiliency.spi.agent.SPIAgentFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.rmi.RemoteException;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Shuyang Zhou
 */
public class RemoteSPIProxy implements SPI {

	public RemoteSPIProxy(
		SPI spi, SPIConfiguration spiConfiguration, String spiProviderName,
		Future<SPI> cancelHandlerFuture,
		RegistrationReference registrationReference) {

		_spi = spi;
		_spiConfiguration = spiConfiguration;
		_spiProviderName = spiProviderName;
		_cancelHandlerFuture = cancelHandlerFuture;
		_registrationReference = registrationReference;

		_mpi = MPIHelperUtil.getMPI();
		_spiAgent = SPIAgentFactoryUtil.createSPIAgent(
			spiConfiguration, registrationReference);
	}

	@Override
	public void addServlet(
			String contextPath, String docBasePath, String mappingPattern,
			String servletClassName)
		throws RemoteException {

		_spi.addServlet(
			contextPath, docBasePath, mappingPattern, servletClassName);
	}

	@Override
	public void addWebapp(String contextPath, String docBasePath)
		throws RemoteException {

		_spi.addWebapp(contextPath, docBasePath);
	}

	@Override
	public void destroy() {
		try {
			_spi.destroy();

			_cancelHandlerFuture.get(
				_spiConfiguration.getShutdownTimeout(), TimeUnit.MILLISECONDS);
		}
		catch (Exception e) {
			_cancelHandlerFuture.cancel(true);

			if (_log.isWarnEnabled()) {
				_log.warn("Forcibly destroyed SPI " + _spiConfiguration, e);
			}
		}
		finally {
			MPIHelperUtil.unregisterSPI(this);
		}

		_spiAgent.destroy();
	}

	@Override
	public MPI getMPI() {
		return _mpi;
	}

	@Override
	public RegistrationReference getRegistrationReference() {
		return _registrationReference;
	}

	@Override
	public SPIAgent getSPIAgent() {
		return _spiAgent;
	}

	@Override
	public SPIConfiguration getSPIConfiguration() {
		return _spiConfiguration;
	}

	@Override
	public String getSPIProviderName() {
		return _spiProviderName;
	}

	@Override
	public void init() throws RemoteException {
		_spi.init();

		try {
			_spiAgent.init(this);
		}
		catch (PortalResiliencyException pre) {
			throw new RemoteException("Unable to initialize SPI agent", pre);
		}
	}

	@Override
	public boolean isAlive() throws RemoteException {
		try {
			return _spi.isAlive();
		}
		catch (RemoteException re) {
			try {
				_cancelHandlerFuture.get();
			}
			catch (Exception e) {
				throw new RemoteException(
					"SPI " + toString() + " died unexpectedly", e);
			}

			return false;
		}
	}

	@Override
	public void start() throws RemoteException {
		_spi.start();
	}

	@Override
	public void stop() throws RemoteException {
		_spi.stop();
	}

	@Override
	public String toString() {
		return _spiProviderName.concat(StringPool.POUND).concat(
			_spiConfiguration.toString());
	}

	private static Log _log = LogFactoryUtil.getLog(RemoteSPIProxy.class);

	private final Future<SPI> _cancelHandlerFuture;
	private final MPI _mpi;
	private final RegistrationReference _registrationReference;
	private final SPI _spi;
	private final SPIAgent _spiAgent;
	private final SPIConfiguration _spiConfiguration;
	private final String _spiProviderName;

}