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

package com.liferay.portal.license.util;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.license.LicenseInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Amos Fong
 */
public interface LicenseManager {

	public static final int STATE_ABSENT = 1;

	public static final int STATE_EXPIRED = 2;

	public static final int STATE_GOOD = 3;

	public static final int STATE_INACTIVE = 4;

	public static final int STATE_INVALID = 5;

	public static final int STATE_OVERLOAD = 6;

	public void checkLicense(String productId);

	public List<Map<String, String>> getClusterLicenseProperties(
		String clusterNodeId);

	public String getHostName();

	public Set<String> getIpAddresses();

	public LicenseInfo getLicenseInfo(String productId);

	public List<Map<String, String>> getLicenseProperties();

	public Map<String, String> getLicenseProperties(String productId);

	public int getLicenseState(Map<String, String> licenseProperties);

	public int getLicenseState(String productId);

	public Set<String> getMacAddresses();

	public void registerLicense(JSONObject jsonObject) throws Exception;

}