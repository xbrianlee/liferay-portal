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

package com.liferay.portal.ccpp;

import com.sun.ccpp.ProfileFactoryImpl;

import javax.ccpp.Profile;
import javax.ccpp.ProfileFactory;
import javax.ccpp.ValidationMode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalProfileFactory {

	public static Profile getCCPPProfile(HttpServletRequest request) {
		ProfileFactory profileFactory = ProfileFactory.getInstance();

		if (profileFactory == null) {
			profileFactory = ProfileFactoryImpl.getInstance();

			ProfileFactory.setInstance(profileFactory);
		}

		Profile profile = profileFactory.newProfile(
			request, ValidationMode.VALIDATIONMODE_NONE);

		if (profile == null) {
			profile = _profile;
		}

		return profile;
	}

	private static final Profile _profile = new EmptyProfile();

}