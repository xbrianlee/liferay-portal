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

import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.resiliency.spi.SPIRegistryUtil;
import com.liferay.portal.kernel.resiliency.spi.SPIRegistryValidator;

/**
 * @author Shuyang Zhou
 */
public class MockSPIRegistryValidator implements SPIRegistryValidator {

	@Override
	public SPI validatePortletSPI(String portletId, SPI spi) {
		if (spi != null) {
			spi = SPIRegistryUtil.getErrorSPI();
		}

		return spi;
	}

	@Override
	public SPI validateServletContextSPI(String servletContextName, SPI spi) {
		if (spi != null) {
			spi = SPIRegistryUtil.getErrorSPI();
		}

		return spi;
	}

}