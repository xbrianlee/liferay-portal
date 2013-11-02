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

package com.liferay.portal.kernel.googleapps;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class GoogleAppsFactoryUtil {

	public static GEmailSettingsManager getGEmailSettingsManager(
		long companyId) {

		return getGoogleAppsFactory().getGEmailSettingsManager(companyId);
	}

	public static GGroupManager getGGroupManager(long companyId) {
		return getGoogleAppsFactory().getGGroupManager(companyId);
	}

	public static GNicknameManager getGNicknameManager(long companyId) {
		return getGoogleAppsFactory().getGNicknameManager(companyId);
	}

	public static GoogleAppsFactory getGoogleAppsFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			GoogleAppsFactoryUtil.class);

		return _googleAppsFactory;
	}

	public static GUserManager getGUserManager(long companyId) {
		return getGoogleAppsFactory().getGUserManager(companyId);
	}

	public void setGoogleAppsFactory(GoogleAppsFactory googleAppsFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_googleAppsFactory = googleAppsFactory;
	}

	private static GoogleAppsFactory _googleAppsFactory;

}