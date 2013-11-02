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

import com.liferay.portal.kernel.util.StringBundler;

/**
 * Abstract class containing common methods for all devices
 *
 * @author Milen Dyankov
 * @author Michael C. Han
 */
public abstract class AbstractDevice implements Device {

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #getScreenResolution()}
	 */
	@Override
	public Dimensions getScreenSize() {
		return getScreenResolution();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{brand=");
		sb.append(getBrand());
		sb.append(", browser=");
		sb.append(getBrowser());
		sb.append(", browserVersion=");
		sb.append(getBrowserVersion());
		sb.append(", capabilities=");
		sb.append(getCapabilities());
		sb.append(", model=");
		sb.append(getModel());
		sb.append(", os=");
		sb.append(getOS());
		sb.append(", osVersion=");
		sb.append(getOSVersion());
		sb.append(", pointingMethod=");
		sb.append(getPointingMethod());
		sb.append(", qwertyKeyboard=");
		sb.append(hasQwertyKeyboard());
		sb.append(", screenPhysicalSize=");
		sb.append(getScreenPhysicalSize());
		sb.append(", screenResolution=");
		sb.append(getScreenResolution());
		sb.append(", tablet=");
		sb.append(isTablet());
		sb.append("}");

		return sb.toString();
	}

}