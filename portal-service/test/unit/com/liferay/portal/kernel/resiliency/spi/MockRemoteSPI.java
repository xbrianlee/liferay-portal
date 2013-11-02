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

import com.liferay.portal.kernel.resiliency.spi.remote.RemoteSPI;

import java.rmi.RemoteException;

/**
 * @author Shuyang Zhou
 */
public class MockRemoteSPI extends RemoteSPI {

	public MockRemoteSPI(SPIConfiguration spiConfiguration) {
		super(spiConfiguration);
	}

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
		if (_failOnDestroy) {
			throw new RemoteException();
		}
	}

	@Override
	public String getSPIProviderName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void init() {
		throw new UnsupportedOperationException();
	}

	public void setFailOnDestroy(boolean failOnDestroy) {
		_failOnDestroy = failOnDestroy;
	}

	public void setFailOnStop(boolean failOnStop) {
		_failOnStop = failOnStop;
	}

	@Override
	public void start() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void stop() throws RemoteException {
		if (_failOnStop) {
			throw new RemoteException();
		}
	}

	private boolean _failOnDestroy;
	private boolean _failOnStop;

}