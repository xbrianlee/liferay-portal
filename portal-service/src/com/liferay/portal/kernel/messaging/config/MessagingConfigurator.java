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

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationEventListener;
import com.liferay.portal.kernel.messaging.MessageListener;

import java.util.List;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public interface MessagingConfigurator {

	public void connect();

	public void destroy();

	public void disconnect();

	public void setDestinations(List<Destination> destinations);

	public void setGlobalDestinationEventListeners(
		List<DestinationEventListener> globalDestinationEventListeners);

	public void setMessageListeners(
		Map<String, List<MessageListener>> messageListeners);

	public void setReplacementDestinations(
		List<Destination> replacementDestinations);

	public void setSpecificDestinationEventListener(
		Map<String, List<DestinationEventListener>>
			specificDestinationEventListeners);

}