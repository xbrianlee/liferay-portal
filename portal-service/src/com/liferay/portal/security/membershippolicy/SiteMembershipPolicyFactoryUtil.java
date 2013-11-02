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

package com.liferay.portal.security.membershippolicy;

/**
 * @author Brian Wing Shun Chan
 */
public class SiteMembershipPolicyFactoryUtil {

	public static SiteMembershipPolicy getSiteMembershipPolicy() {
		return getSiteMembershipPolicyFactory().getSiteMembershipPolicy();
	}

	public static SiteMembershipPolicyFactory getSiteMembershipPolicyFactory() {
		return _siteMembershipPolicyFactory;
	}

	public void setSiteMembershipPolicyFactory(
		SiteMembershipPolicyFactory siteMembershipPolicyFactory) {

		_siteMembershipPolicyFactory = siteMembershipPolicyFactory;
	}

	private static SiteMembershipPolicyFactory _siteMembershipPolicyFactory;

}