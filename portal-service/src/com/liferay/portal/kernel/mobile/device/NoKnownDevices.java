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

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class represents unknown device
 *
 * @author Milen Dyankov
 * @author Michael C. Han
 */
public class NoKnownDevices implements KnownDevices {

	public static NoKnownDevices getInstance() {
		return _instance;
	}

	@Override
	public Set<VersionableName> getBrands() {
		return _brands;
	}

	@Override
	public Set<VersionableName> getBrowsers() {
		return _browsers;
	}

	@Override
	public Map<Capability, Set<String>> getDeviceIds() {
		return Collections.emptyMap();
	}

	@Override
	public Set<VersionableName> getOperatingSystems() {
		return _operatingSystems;
	}

	@Override
	public Set<String> getPointingMethods() {
		return _pointingMethods;
	}

	@Override
	public void reload() {
	}

	private NoKnownDevices() {
		_brands.add(VersionableName.UNKNOWN);

		_brands = Collections.unmodifiableSet(_brands);

		_browsers.add(VersionableName.UNKNOWN);

		_browsers = Collections.unmodifiableSet(_browsers);

		_operatingSystems.add(VersionableName.UNKNOWN);

		_operatingSystems = Collections.unmodifiableSet(_operatingSystems);

		_pointingMethods.add(VersionableName.UNKNOWN.getName());

		_pointingMethods = Collections.unmodifiableSet(_pointingMethods);
	}

	private static NoKnownDevices _instance = new NoKnownDevices();

	private Set<VersionableName> _brands = new HashSet<VersionableName>();
	private Set<VersionableName> _browsers = new HashSet<VersionableName>();
	private Set<VersionableName> _operatingSystems =
		new HashSet<VersionableName>();
	private Set<String> _pointingMethods = new HashSet<String>();

}