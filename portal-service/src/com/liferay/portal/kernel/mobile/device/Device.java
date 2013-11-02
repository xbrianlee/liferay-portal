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

import java.io.Serializable;

import java.util.Map;

/**
 * @author Milen Dyankov
 * @author Michael C. Han
 */
public interface Device extends Serializable {

	public String getBrand();

	public String getBrowser();

	public String getBrowserVersion();

	public Map<String, Capability> getCapabilities();

	public String getCapability(String name);

	public String getModel();

	public String getOS();

	public String getOSVersion();

	public String getPointingMethod();

	public Dimensions getScreenPhysicalSize();

	public Dimensions getScreenResolution();

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #getScreenResolution()}
	 */
	public Dimensions getScreenSize();

	public boolean hasQwertyKeyboard();

	public boolean isTablet();

}