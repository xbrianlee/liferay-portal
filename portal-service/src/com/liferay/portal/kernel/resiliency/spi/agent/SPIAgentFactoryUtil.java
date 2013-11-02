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

package com.liferay.portal.kernel.resiliency.spi.agent;

import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.resiliency.spi.SPIConfiguration;

import java.lang.reflect.Constructor;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shuyang Zhou
 */
public class SPIAgentFactoryUtil {

	public static SPIAgent createSPIAgent(
		SPIConfiguration spiConfiguration,
		RegistrationReference registrationReference) {

		String spiAgentClassName = spiConfiguration.getSPIAgentClassName();

		if (spiAgentClassName == null) {
			throw new NullPointerException("Missing SPI agent class name");
		}

		Class<? extends SPIAgent> spiAgentClass = _spiAgentClasses.get(
			spiAgentClassName);

		if (spiAgentClass == null) {
			throw new IllegalArgumentException(
				"Unkown SPI agent class name " + spiAgentClassName);
		}

		try {
			Constructor<? extends SPIAgent> constructor =
				spiAgentClass.getConstructor(
					SPIConfiguration.class, RegistrationReference.class);

			return constructor.newInstance(
				spiConfiguration, registrationReference);
		}
		catch (Exception e) {
			throw new RuntimeException(
				"Unable to instantiate " + spiAgentClass, e);
		}
	}

	public static Set<String> getSPIAgentClassNames() {
		return _spiAgentClasses.keySet();
	}

	public static Class<? extends SPIAgent> registerSPIAgentClass(
		Class<? extends SPIAgent> spiAgentClass) {

		return _spiAgentClasses.put(spiAgentClass.getName(), spiAgentClass);
	}

	public static Class<? extends SPIAgent> unregisterSPIAgentClass(
		String spiAgentClassName) {

		return _spiAgentClasses.remove(spiAgentClassName);
	}

	public void setSPIAgentClasses(Set<String> spiAgentClassNames)
		throws ClassNotFoundException {

		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		for (String spiAgentClassName : spiAgentClassNames) {
			Class<? extends SPIAgent> agentClass =
				(Class<? extends SPIAgent>)classLoader.loadClass(
					spiAgentClassName);

			_spiAgentClasses.put(spiAgentClassName, agentClass);
		}
	}

	private static final Map<String, Class<? extends SPIAgent>>
		_spiAgentClasses =
			new ConcurrentHashMap<String, Class<? extends SPIAgent>>();

}