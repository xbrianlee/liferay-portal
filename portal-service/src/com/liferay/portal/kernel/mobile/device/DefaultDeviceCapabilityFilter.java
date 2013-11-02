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

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public class DefaultDeviceCapabilityFilter implements DeviceCapabilityFilter {

	@Override
	public boolean accept(String capabilityName) {
		if (_acceptableCapabilityNames.isEmpty() ||
			_acceptableCapabilityNames.contains(capabilityName)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean accept(String capabilityName, String capabilityValue) {
		if (Validator.isNull(capabilityValue)) {
			return false;
		}

		capabilityValue = StringUtil.toLowerCase(capabilityValue);

		if (capabilityValue.equals("false")) {
			return false;
		}

		if (!accept(capabilityName)) {
			return false;
		}

		return true;
	}

	public void setAcceptableCapabilityNames(
		Set<String> acceptableCapabilityNames) {

		_acceptableCapabilityNames.addAll(acceptableCapabilityNames);
	}

	private Set<String> _acceptableCapabilityNames = new HashSet<String>();

}