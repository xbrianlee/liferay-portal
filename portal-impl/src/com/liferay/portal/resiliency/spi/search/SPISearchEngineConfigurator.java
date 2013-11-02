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

package com.liferay.portal.resiliency.spi.search;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.nio.intraband.messaging.IntrabandBridgeMessageListener;
import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.resiliency.spi.SPIUtil;
import com.liferay.portal.kernel.search.SearchEngineUtil;

import java.rmi.RemoteException;

import java.util.Set;

/**
 * @author Shuyang Zhou
 */
public class SPISearchEngineConfigurator {

	public void afterPropertiesSet() throws RemoteException {
		if (!SPIUtil.isSPI()) {
			return;
		}

		Set<String> searchEngineIds = SearchEngineUtil.getSearchEngineIds();

		for (String searchEngineId : searchEngineIds) {
			String destinationName =
				SearchEngineUtil.getSearchWriterDestinationName(searchEngineId);

			Destination destination = _messageBus.getDestination(
				destinationName);

			destination.unregisterMessageListeners();

			SPI spi = SPIUtil.getSPI();

			destination.register(
				new IntrabandBridgeMessageListener(
					spi.getRegistrationReference()));
		}
	}

	public void setMessageBus(MessageBus messageBus) {
		_messageBus = messageBus;
	}

	private MessageBus _messageBus;

}