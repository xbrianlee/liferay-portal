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

package com.liferay.portal.mobile.device;

import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.kernel.mobile.device.Device;
import com.liferay.portal.kernel.mobile.device.DeviceCapabilityFilter;
import com.liferay.portal.kernel.mobile.device.DeviceRecognitionProvider;
import com.liferay.portal.kernel.mobile.device.KnownDevices;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Michael C. Han
 */
public class DeviceRecognitionProviderProxyBean
	extends BaseProxyBean implements DeviceRecognitionProvider {

	@Override
	public Device detectDevice(HttpServletRequest request) {
		throw new UnsupportedOperationException();
	}

	@Override
	public KnownDevices getKnownDevices() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void reload() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setDeviceCapabilityFilter(
		DeviceCapabilityFilter deviceCapabilityFilter) {

		throw new UnsupportedOperationException();
	}

}