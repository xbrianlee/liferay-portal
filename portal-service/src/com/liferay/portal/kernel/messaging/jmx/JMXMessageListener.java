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

package com.liferay.portal.kernel.messaging.jmx;

import com.liferay.portal.kernel.jmx.MBeanRegistry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseDestinationEventListener;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.MessageBus;

import java.util.Collection;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class JMXMessageListener extends BaseDestinationEventListener {

	public void afterPropertiesSet() throws Exception {
		if ((_mBeanRegistry == null) || (_messageBus == null)) {
			throw new IllegalStateException(
				"MBean server and message bus are not configured");
		}

		try {
			_mBeanRegistry.replace(
				_MESSAGE_BUS_MANAGER_OBJECT_NAME_CACHE_KEY,
				new MessageBusManager(_messageBus),
				MessageBusManager.createObjectName());
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to register message bus manager", e);
			}
		}

		Collection<Destination> destinations = _messageBus.getDestinations();

		for (Destination destination : destinations) {
			try {
				registerDestination(destination);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to register destination " +
							destination.getName(),
					e);
				}
			}
		}
	}

	@Override
	public void destinationAdded(Destination destination) {
		try {
			registerDestination(destination);
		}
		catch (Exception e) {
			_log.error(
				"Unable to register destination " + destination.getName(), e);
		}
	}

	@Override
	public void destinationRemoved(Destination destination) {
		try {
			unregisterDestination(destination);
		}
		catch (Exception e) {
			_log.error(
				"Unable to unregister destination " + destination.getName(), e);
		}
	}

	public void destroy() throws Exception {
		Collection<Destination> destinations = _messageBus.getDestinations();

		for (Destination destination : destinations) {
			try {
				unregisterDestination(destination);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to unregister destination " +
							destination.getName(),
						e);
				}
			}
		}

		try {
			_mBeanRegistry.unregister(
				_MESSAGE_BUS_MANAGER_OBJECT_NAME_CACHE_KEY,
				MessageBusManager.createObjectName());
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to unregister message bus manager", e);
			}
		}
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #afterPropertiesSet}
	 */
	public void init() throws Exception {
		afterPropertiesSet();
	}

	public void setMBeanRegistry(MBeanRegistry mBeanRegistry) {
		_mBeanRegistry = mBeanRegistry;
	}

	public void setMessageBus(MessageBus messageBus) {
		_messageBus = messageBus;
	}

	protected void registerDestination(Destination destination)
		throws Exception {

		String destinationName = destination.getName();

		_mBeanRegistry.replace(
			destinationName, new DestinationManager(destination),
			DestinationManager.createObjectName(destinationName));

		_mBeanRegistry.replace(
			_getStatisticsObjectNameCacheKey(destinationName),
			new DestinationStatisticsManager(destination),
			DestinationStatisticsManager.createObjectName(destinationName));
	}

	protected void unregisterDestination(Destination destination)
		throws Exception {

		String destinationName = destination.getName();

		_mBeanRegistry.unregister(
			destinationName,
			DestinationManager.createObjectName(destinationName));

		_mBeanRegistry.unregister(
			_getStatisticsObjectNameCacheKey(destinationName),
			DestinationStatisticsManager.createObjectName(destinationName));
	}

	private String _getStatisticsObjectNameCacheKey(String destinationName) {
		return destinationName + "statistics";
	}

	private static final String _MESSAGE_BUS_MANAGER_OBJECT_NAME_CACHE_KEY =
		"messageBusManager";

	private static Log _log = LogFactoryUtil.getLog(JMXMessageListener.class);

	private MBeanRegistry _mBeanRegistry;
	private MessageBus _messageBus;

}