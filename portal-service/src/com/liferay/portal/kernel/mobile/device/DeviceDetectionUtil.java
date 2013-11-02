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

package com.liferay.portal.kernel.mobile.device;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Milen Dyankov
 * @author Raymond Augé
 */
public class DeviceDetectionUtil {

	public static Device detectDevice(HttpServletRequest request) {
		return getDeviceRecognitionProvider().detectDevice(request);
	}

	public static DeviceRecognitionProvider getDeviceRecognitionProvider() {
		PortalRuntimePermission.checkGetBeanProperty(DeviceDetectionUtil.class);

		return _deviceRecognitionProvider;
	}

	public static Set<VersionableName> getKnownBrands() {
		KnownDevices knownDevices =
			getDeviceRecognitionProvider().getKnownDevices();

		return knownDevices.getBrands();
	}

	public static Set<VersionableName> getKnownBrowsers() {
		KnownDevices knownDevices =
			getDeviceRecognitionProvider().getKnownDevices();

		return knownDevices.getBrowsers();
	}

	public static Set<String> getKnownDeviceIdsByCapability(
		Capability capability) {

		KnownDevices knownDevices =
			getDeviceRecognitionProvider().getKnownDevices();

		Map<Capability, Set<String>> deviceIds = knownDevices.getDeviceIds();

		return deviceIds.get(capability);
	}

	public static Set<VersionableName> getKnownOperatingSystems() {
		KnownDevices knownDevices =
			getDeviceRecognitionProvider().getKnownDevices();

		return knownDevices.getOperatingSystems();
	}

	public static Set<String> getKnownPointingMethods() {
		KnownDevices knownDevices =
			getDeviceRecognitionProvider().getKnownDevices();

		return knownDevices.getPointingMethods();
	}

	public void setDeviceRecognitionProvider(
		DeviceRecognitionProvider deviceRecognitionProvider) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_deviceRecognitionProvider = deviceRecognitionProvider;
	}

	private static volatile DeviceRecognitionProvider
		_deviceRecognitionProvider;

}