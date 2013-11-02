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

package com.liferay.portal.mobile.device.messaging;

import com.liferay.portal.kernel.messaging.BaseDestinationEventListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.proxy.ProxyMessageListener;
import com.liferay.portal.kernel.mobile.device.DeviceDetectionUtil;
import com.liferay.portal.kernel.mobile.device.DeviceRecognitionProvider;

/**
 * @author Milen Dyankov
 * @author Michael C. Han
 * @author Shuyang Zhou
 */
public class DeviceRecognitionProviderDestinationEventListener
	extends BaseDestinationEventListener {

	@Override
	public void messageListenerRegistered(
		String destinationName, MessageListener messageListener) {

		if (!isProceed(destinationName, messageListener)) {
			return;
		}

		DeviceDetectionUtil deviceDetectionUtil = new DeviceDetectionUtil();

		deviceDetectionUtil.setDeviceRecognitionProvider(
			_proxyDeviceRecognitionProvider);
	}

	@Override
	public void messageListenerUnregistered(
		String destinationName, MessageListener messageListener) {

		if (!isProceed(destinationName, messageListener)) {
			return;
		}

		DeviceDetectionUtil deviceDetectionUtil = new DeviceDetectionUtil();

		deviceDetectionUtil.setDeviceRecognitionProvider(
			_directDeviceRecognitionProvider);
	}

	public void setDirectDeviceRecognitionProvider(
		DeviceRecognitionProvider directDeviceRecognitionProvider) {

		_directDeviceRecognitionProvider = directDeviceRecognitionProvider;
	}

	public void setProxyDeviceRecognitionProvider(
		DeviceRecognitionProvider proxyDeviceRecognitionProvider) {

		_proxyDeviceRecognitionProvider = proxyDeviceRecognitionProvider;
	}

	protected boolean isProceed(
		String destinationName, MessageListener messageListener) {

		if (!destinationName.equals(
				DestinationNames.DEVICE_RECOGNITION_PROVIDER) ||
			!(messageListener instanceof ProxyMessageListener)) {

			return false;
		}
		else {
			return true;
		}
	}

	private DeviceRecognitionProvider _directDeviceRecognitionProvider;
	private DeviceRecognitionProvider _proxyDeviceRecognitionProvider;

}