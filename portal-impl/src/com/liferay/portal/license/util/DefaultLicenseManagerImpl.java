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

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.license.LicenseInfo;
import com.liferay.portal.util.PortalUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Amos Fong
 */
@DoPrivileged
public class DefaultLicenseManagerImpl
	implements com.liferay.portal.license.util.LicenseManager {

	@Override
	public void checkLicense(String productId) {
	}

	@Override
	public List<Map<String, String>> getClusterLicenseProperties(
		String clusterNodeId) {

		return null;
	}

	@Override
	public String getHostName() {
		return PortalUtil.getComputerName();
	}

	@Override
	public Set<String> getIpAddresses() {
		return LicenseUtil.getIpAddresses();
	}

	@Override
	public LicenseInfo getLicenseInfo(String productId) {
		return null;
	}

	@Override
	public List<Map<String, String>> getLicenseProperties() {
		return null;
	}

	@Override
	public Map<String, String> getLicenseProperties(String productId) {
		return null;
	}

	@Override
	public int getLicenseState(Map<String, String> licenseProperties) {
		String productId = licenseProperties.get("productId");

		if (Validator.isNull(productId)) {
			return 0;
		}

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			byte[] serverIdBytes = LicenseUtil.getServerIdBytes();

			jsonObject.put(Constants.CMD, "GET_LICENSE_STATE");

			jsonObject.put("hostName", getHostName());
			jsonObject.put("ipAddresses", StringUtil.merge(getIpAddresses()));
			jsonObject.put("macAddresses", StringUtil.merge(getMacAddresses()));
			jsonObject.put("productId", productId);

			String productVersion = licenseProperties.get("productVersion");

			jsonObject.put("productVersion", productVersion);

			String randomUuid = PortalUUIDUtil.generate();

			jsonObject.put("randomUuid", randomUuid);

			jsonObject.put("serverId", Arrays.toString(serverIdBytes));

			String userCount = licenseProperties.get("userCount");

			jsonObject.put("userCount", userCount);

			jsonObject.put("version", 2);

			String response = LicenseUtil.sendRequest(jsonObject.toString());

			JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject(
				response);

			String errorMessage = responseJSONObject.getString("errorMessage");

			if (Validator.isNotNull(errorMessage)) {
				throw new Exception(errorMessage);
			}

			String responseRandomUuid = responseJSONObject.getString(
				"randomUuid");

			if (responseRandomUuid.equals(randomUuid)) {
				int licenseState = responseJSONObject.getInt("licenseState");

				return licenseState;
			}
		}
		catch (Exception e) {
			_log.error(e.getMessage());
		}

		return 0;
	}

	@Override
	public int getLicenseState(String productId) {
		Map<String, String> licenseProperties = new HashMap<String, String>();

		licenseProperties.put("productId", productId);

		return getLicenseState(licenseProperties);
	}

	@Override
	public Set<String> getMacAddresses() {
		return LicenseUtil.getMacAddresses();
	}

	@Override
	public void registerLicense(JSONObject jsonObject) throws Exception {
		String serverId = jsonObject.getString("serverId");

		if (serverId.length() <= 2) {
			return;
		}

		serverId = serverId.substring(1, serverId.length() - 1);

		String[] serverIdArray = StringUtil.split(serverId);

		byte[] bytes = new byte[serverIdArray.length];

		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = Byte.valueOf(serverIdArray[i].trim());
		}

		LicenseUtil.writeServerProperties(bytes);
	}

	private static Log _log = LogFactoryUtil.getLog(
		DefaultLicenseManagerImpl.class);

}