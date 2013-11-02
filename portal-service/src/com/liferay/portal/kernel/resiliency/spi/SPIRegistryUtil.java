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
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.rmi.RemoteException;

import java.util.Set;

/**
 * @author Shuyang Zhou
 */
public class SPIRegistryUtil {

	public static void addExcludedPortletId(String portletId) {
		getSPIRegistry().addExcludedPortletId(portletId);
	}

	public static SPI getErrorSPI() {
		return getSPIRegistry().getErrorSPI();
	}

	public static Set<String> getExcludedPortletIds() {
		return getSPIRegistry().getExcludedPortletIds();
	}

	public static SPI getPortletSPI(String portletId)
		throws PortalResiliencyException {

		return getSPIRegistry().getPortletSPI(portletId);
	}

	public static SPI getServletContextSPI(String servletContextName)
		throws PortalResiliencyException {

		return getSPIRegistry().getServletContextSPI(servletContextName);
	}

	public static SPIRegistry getSPIRegistry() {
		PortalRuntimePermission.checkGetBeanProperty(SPIRegistryUtil.class);

		return _spiRegistry;
	}

	public static void registerSPI(SPI spi) throws RemoteException {
		getSPIRegistry().registerSPI(spi);
	}

	public static void removeExcludedPortletId(String portletId) {
		getSPIRegistry().removeExcludedPortletId(portletId);
	}

	public static void setSPIRegistryValidator(
		SPIRegistryValidator spiRegistryValidator) {

		getSPIRegistry().setSPIRegistryValidator(spiRegistryValidator);
	}

	public static void unregisterSPI(SPI spi) {
		getSPIRegistry().unregisterSPI(spi);
	}

	public void setSPIRegistry(SPIRegistry spiRegistry) {
		PortalRuntimePermission.checkSetBeanProperty(SPIRegistryUtil.class);

		_spiRegistry = spiRegistry;
	}

	private static SPIRegistry _spiRegistry;

}