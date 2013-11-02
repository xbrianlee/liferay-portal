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

package com.liferay.portal.resiliency.spi.cache;

import com.liferay.portal.kernel.cache.PortalCacheManager;
import com.liferay.portal.kernel.nio.intraband.cache.IntrabandPortalCacheManager;
import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.resiliency.spi.SPIUtil;

import java.io.Serializable;

import java.rmi.RemoteException;

/**
 * @author Shuyang Zhou
 */
public class SPIPortalCacheManagerConfigurator {

	public static <K extends Serializable, V extends Serializable>
		PortalCacheManager<K, V> createSPIPortalCacheManager(
			PortalCacheManager<K, V> portalCacheManager)
		throws RemoteException {

		if (SPIUtil.isSPI()) {
			SPI spi = SPIUtil.getSPI();

			portalCacheManager = new IntrabandPortalCacheManager<K, V>(
				spi.getRegistrationReference());
		}

		IntrabandPortalCacheManager.setPortalCacheManager(portalCacheManager);

		return portalCacheManager;
	}

}