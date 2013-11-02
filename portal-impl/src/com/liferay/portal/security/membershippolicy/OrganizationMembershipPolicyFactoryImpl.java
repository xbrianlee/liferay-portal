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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.util.ClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Sergio González
 * @author Shuyang Zhou
 */
public class OrganizationMembershipPolicyFactoryImpl
	implements OrganizationMembershipPolicyFactory {

	public void afterPropertiesSet() throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug(
				"Instantiate " + PropsValues.MEMBERSHIP_POLICY_ORGANIZATIONS);
		}

		ClassLoader classLoader = ClassLoaderUtil.getPortalClassLoader();

		_originalOrganizationMembershipPolicy =
			(OrganizationMembershipPolicy)InstanceFactory.newInstance(
				classLoader, PropsValues.MEMBERSHIP_POLICY_ORGANIZATIONS);

		_organizationMembershipPolicy = _originalOrganizationMembershipPolicy;
	}

	@Override
	public OrganizationMembershipPolicy getOrganizationMembershipPolicy() {
		return _organizationMembershipPolicy;
	}

	public void setOrganizationMembershipPolicy(
		OrganizationMembershipPolicy organizationMembershipPolicy) {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Set " + ClassUtil.getClassName(organizationMembershipPolicy));
		}

		if (organizationMembershipPolicy == null) {
			_organizationMembershipPolicy =
				_originalOrganizationMembershipPolicy;
		}
		else {
			_organizationMembershipPolicy = organizationMembershipPolicy;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		OrganizationMembershipPolicyFactoryImpl.class);

	private static volatile OrganizationMembershipPolicy
		_organizationMembershipPolicy;
	private static OrganizationMembershipPolicy
		_originalOrganizationMembershipPolicy;

}