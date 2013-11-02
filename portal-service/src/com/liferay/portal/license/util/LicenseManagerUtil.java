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
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.license.LicenseInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Amos Fong
 */
public class LicenseManagerUtil {

	/**
	 * @deprecated As of 6.2.0, replaced by {@link LicenseManager#STATE_ABSENT}
	 */
	public static final int STATE_ABSENT = LicenseManager.STATE_ABSENT;

	/**
	 * @deprecated As of 6.2.0, replaced by {@link LicenseManager#STATE_EXPIRED}
	 */
	public static final int STATE_EXPIRED = LicenseManager.STATE_EXPIRED;

	/**
	 * @deprecated As of 6.2.0, replaced by {@link LicenseManager#STATE_GOOD}
	 */
	public static final int STATE_GOOD = LicenseManager.STATE_GOOD;

	/**
	 * @deprecated As of 6.2.0, replaced by {@link
	 *             LicenseManager#STATE_INACTIVE}
	 */
	public static final int STATE_INACTIVE = LicenseManager.STATE_INACTIVE;

	/**
	 * @deprecated As of 6.2.0, replaced by {@link LicenseManager#STATE_INVALID}
	 */
	public static final int STATE_INVALID = LicenseManager.STATE_INVALID;

	/**
	 * @deprecated As of 6.2.0, replaced by {@link
	 *             LicenseManager#STATE_OVERLOAD}
	 */
	public static final int STATE_OVERLOAD = LicenseManager.STATE_OVERLOAD;

	public static void checkLicense(String productId) {
		getLicenseManager().checkLicense(productId);
	}

	public static List<Map<String, String>> getClusterLicenseProperties(
		String clusterNodeId) {

		return getLicenseManager().getClusterLicenseProperties(clusterNodeId);
	}

	public static String getHostName() {
		return getLicenseManager().getHostName();
	}

	public static Set<String> getIpAddresses() {
		return getLicenseManager().getIpAddresses();
	}

	public static LicenseInfo getLicenseInfo(String productId) {
		return getLicenseManager().getLicenseInfo(productId);
	}

	public static LicenseManager getLicenseManager() {
		PortalRuntimePermission.checkGetBeanProperty(LicenseManagerUtil.class);

		return _licenseManager;
	}

	public static List<Map<String, String>> getLicenseProperties() {
		return getLicenseManager().getLicenseProperties();
	}

	public static Map<String, String> getLicenseProperties(String productId) {
		return getLicenseManager().getLicenseProperties(productId);
	}

	public static int getLicenseState(Map<String, String> licenseProperties) {
		return getLicenseManager().getLicenseState(licenseProperties);
	}

	public static int getLicenseState(String productId) {
		return getLicenseManager().getLicenseState(productId);
	}

	public static Set<String> getMacAddresses() {
		return getLicenseManager().getMacAddresses();
	}

	public static void registerLicense(JSONObject jsonObject) throws Exception {
		getLicenseManager().registerLicense(jsonObject);
	}

	public void setLicenseManager(LicenseManager licenseManager) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_licenseManager = licenseManager;
	}

	private static LicenseManager _licenseManager;

}