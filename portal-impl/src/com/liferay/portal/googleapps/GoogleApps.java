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

/**
 * @author Brian Wing Shun Chan
 */
public class GoogleApps {

	public GoogleApps(long companyId) {
		_gAuthenticator = new GAuthenticator(companyId);

		init();
	}

	public GoogleApps(String domain, String userName, String password) {
		_gAuthenticator = new GAuthenticator(domain, userName, password);

		init();
	}

	public GAuthenticator getGAuthenticator() {
		return _gAuthenticator;
	}

	public GEmailSettingsManager getGEmailSettingsManager() {
		return _gEmailSettingsManager;
	}

	public GGroupManager getGGroupManager() {
		return _gGroupManager;
	}

	public GNicknameManager getGNicknameManager() {
		return _gNicknameManager;
	}

	public GUserManager getGUserManager() {
		return _gUserManager;
	}

	protected void init() {
		_gEmailSettingsManager = new GEmailSettingsManagerImpl(this);
		_gGroupManager = new GGroupManagerImpl(this);
		_gNicknameManager = new GNicknameManagerImpl(this);
		_gUserManager = new GUserManagerImpl(this);
	}

	private GAuthenticator _gAuthenticator;
	private GEmailSettingsManager _gEmailSettingsManager;
	private GGroupManager _gGroupManager;
	private GNicknameManager _gNicknameManager;
	private GUserManager _gUserManager;

}