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

package com.liferay.portal.googleapps;

import com.liferay.portal.kernel.googleapps.GEmailSettingsManager;
import com.liferay.portal.kernel.googleapps.GGroupManager;
import com.liferay.portal.kernel.googleapps.GNicknameManager;
import com.liferay.portal.kernel.googleapps.GUserManager;
import com.liferay.portal.kernel.googleapps.GoogleAppsFactory;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class GoogleAppsFactoryImpl implements GoogleAppsFactory {

	@Override
	public GEmailSettingsManager getGEmailSettingsManager(long companyId) {
		return getGoogleApps(companyId).getGEmailSettingsManager();
	}

	@Override
	public GGroupManager getGGroupManager(long companyId) {
		return getGoogleApps(companyId).getGGroupManager();
	}

	@Override
	public GNicknameManager getGNicknameManager(long companyId) {
		return getGoogleApps(companyId).getGNicknameManager();
	}

	@Override
	public GUserManager getGUserManager(long companyId) {
		return getGoogleApps(companyId).getGUserManager();
	}

	protected GoogleApps getGoogleApps(long companyId) {
		GoogleApps googleApps = _googleAppsMap.get(companyId);

		if (googleApps == null) {
			googleApps = new GoogleApps(companyId);

			_googleAppsMap.put(companyId, googleApps);
		}

		return googleApps;
	}

	private static Map<Long, GoogleApps> _googleAppsMap =
		new ConcurrentHashMap<Long, GoogleApps>();

}