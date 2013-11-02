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

import java.io.Serializable;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Shuyang Zhou
 */
public interface SPI extends Remote, Serializable {

	public static final String SPI_INSTANCE_PUBLICATION_KEY =
		"SPI_INSTANCE_PUBLICATION_KEY";

	public void addServlet(
			String contextPath, String docBasePath, String mappingPattern,
			String servletClassName)
		throws RemoteException;

	public void addWebapp(String contextPath, String docBasePath)
		throws RemoteException;

	public void destroy() throws RemoteException;

	public MPI getMPI() throws RemoteException;

	public RegistrationReference getRegistrationReference()
		throws RemoteException;

	public SPIAgent getSPIAgent() throws RemoteException;

	public SPIConfiguration getSPIConfiguration() throws RemoteException;

	public String getSPIProviderName() throws RemoteException;

	public void init() throws RemoteException;

	public boolean isAlive() throws RemoteException;

	public void start() throws RemoteException;

	public void stop() throws RemoteException;

}