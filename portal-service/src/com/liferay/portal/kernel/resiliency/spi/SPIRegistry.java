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

import com.liferay.portal.kernel.resiliency.PortalResiliencyException;

import java.rmi.RemoteException;

import java.util.Set;

/**
 * @author Shuyang Zhou
 */
public interface SPIRegistry {

	public void addExcludedPortletId(String portletId);

	public SPI getErrorSPI();

	public Set<String> getExcludedPortletIds();

	public SPI getPortletSPI(String portletId) throws PortalResiliencyException;

	public SPI getServletContextSPI(String servletContextName)
		throws PortalResiliencyException;

	public void registerSPI(SPI spi) throws RemoteException;

	public void removeExcludedPortletId(String portletId);

	public void setSPIRegistryValidator(
		SPIRegistryValidator spiRegistryValidator);

	public void unregisterSPI(SPI spi);

}