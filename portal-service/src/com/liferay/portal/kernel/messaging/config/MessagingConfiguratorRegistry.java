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

package com.liferay.portal.kernel.messaging.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shuyang Zhou
 */
public class MessagingConfiguratorRegistry {

	public static List<MessagingConfigurator> getMessagingConfigurators(
		String servletContextName) {

		return _messagingConfigurators.get(servletContextName);
	}

	public static void registerMessagingConfigurator(
		String servletContextName,
		MessagingConfigurator messagingConfigurator) {

		List<MessagingConfigurator> messagingConfigurators =
			_messagingConfigurators.get(servletContextName);

		if (messagingConfigurators == null) {
			messagingConfigurators = new ArrayList<MessagingConfigurator>();

			_messagingConfigurators.put(
				servletContextName, messagingConfigurators);
		}

		messagingConfigurators.add(messagingConfigurator);
	}

	public static void unregisterMessagingConfigurator(
		String servletContextName,
		MessagingConfigurator messagingConfigurator) {

		List<MessagingConfigurator> messagingConfigurators =
			_messagingConfigurators.get(servletContextName);

		if (messagingConfigurators != null) {
			messagingConfigurators.remove(messagingConfigurator);

			if (messagingConfigurators.isEmpty()) {
				_messagingConfigurators.remove(servletContextName);
			}
		}
	}

	private static final Map<String, List<MessagingConfigurator>>
		_messagingConfigurators =
			new ConcurrentHashMap<String, List<MessagingConfigurator>>();

}