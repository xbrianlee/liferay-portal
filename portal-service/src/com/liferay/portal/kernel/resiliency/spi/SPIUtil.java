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

import com.liferay.portal.kernel.process.ProcessExecutor;

import java.util.concurrent.ConcurrentMap;

/**
 * @author Shuyang Zhou
 */
public class SPIUtil {

	public static SPI getSPI() {
		if (_spi == null) {
			throw new IllegalStateException(
				"Current process is not an SPI instance");
		}

		return _spi;
	}

	public static boolean isSPI() {
		if (_spi == null) {
			return false;
		}
		else {
			return true;
		}
	}

	private static final SPI _spi;

	static {
		ConcurrentMap<String, Object> attributes =
			ProcessExecutor.ProcessContext.getAttributes();

		_spi = (SPI)attributes.remove(SPI.SPI_INSTANCE_PUBLICATION_KEY);
	}

}