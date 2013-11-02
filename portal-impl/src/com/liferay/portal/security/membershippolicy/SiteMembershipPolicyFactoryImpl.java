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
 * @author Roberto Díaz
 */
public class SiteMembershipPolicyFactoryImpl
	implements SiteMembershipPolicyFactory {

	public void afterPropertiesSet() throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug("Instantiate " + PropsValues.MEMBERSHIP_POLICY_SITES);
		}

		ClassLoader classLoader = ClassLoaderUtil.getPortalClassLoader();

		_originalSiteMembershipPolicy =
			(SiteMembershipPolicy)InstanceFactory.newInstance(
				classLoader, PropsValues.MEMBERSHIP_POLICY_SITES);

		_siteMembershipPolicy = _originalSiteMembershipPolicy;
	}

	@Override
	public SiteMembershipPolicy getSiteMembershipPolicy() {
		return _siteMembershipPolicy;
	}

	public void setSiteMembershipPolicy(
		SiteMembershipPolicy siteMembershipPolicy) {

		if (_log.isDebugEnabled()) {
			_log.debug("Set " + ClassUtil.getClassName(siteMembershipPolicy));
		}

		if (siteMembershipPolicy == null) {
			_siteMembershipPolicy = _originalSiteMembershipPolicy;
		}
		else {
			_siteMembershipPolicy = siteMembershipPolicy;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SiteMembershipPolicyFactoryImpl.class);

	private static SiteMembershipPolicy _originalSiteMembershipPolicy;
	private static volatile SiteMembershipPolicy _siteMembershipPolicy;

}