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
public class UserGroupMembershipPolicyFactoryImpl
	implements UserGroupMembershipPolicyFactory {

	public void afterPropertiesSet() throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug(
				"Instantiate " + PropsValues.MEMBERSHIP_POLICY_USER_GROUPS);
		}

		ClassLoader classLoader = ClassLoaderUtil.getPortalClassLoader();

		_originalUserGroupMembershipPolicy =
			(UserGroupMembershipPolicy)InstanceFactory.newInstance(
				classLoader, PropsValues.MEMBERSHIP_POLICY_USER_GROUPS);

		_userGroupMembershipPolicy = _originalUserGroupMembershipPolicy;
	}

	@Override
	public UserGroupMembershipPolicy getUserGroupMembershipPolicy() {
		return _userGroupMembershipPolicy;
	}

	public void setUserGroupMembershipPolicy(
		UserGroupMembershipPolicy userGroupMembershipPolicy) {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Set " + ClassUtil.getClassName(userGroupMembershipPolicy));
		}

		if (userGroupMembershipPolicy == null) {
			_userGroupMembershipPolicy = _originalUserGroupMembershipPolicy;
		}
		else {
			_userGroupMembershipPolicy = userGroupMembershipPolicy;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		UserGroupMembershipPolicyFactoryImpl.class);

	private static UserGroupMembershipPolicy _originalUserGroupMembershipPolicy;
	private static volatile UserGroupMembershipPolicy
		_userGroupMembershipPolicy;

}