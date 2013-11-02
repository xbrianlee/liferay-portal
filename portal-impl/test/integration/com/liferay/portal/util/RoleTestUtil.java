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

package com.liferay.portal.util;

import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceTestUtil;

/**
 * @author Roberto Díaz
 */
public class RoleTestUtil {

	public static long addGroupRole(long groupId) throws Exception {
		Role role = ServiceTestUtil.addRole(
			ServiceTestUtil.randomString(), RoleConstants.TYPE_SITE);

		RoleLocalServiceUtil.addGroupRole(groupId, role.getRoleId());

		return role.getRoleId();
	}

	public static long addOrganizationRole(long groupId) throws Exception {
		Role role = ServiceTestUtil.addRole(
			ServiceTestUtil.randomString(), RoleConstants.TYPE_ORGANIZATION);

		RoleLocalServiceUtil.addGroupRole(groupId, role.getRoleId());

		return role.getRoleId();
	}

	public static long addRegularRole(long groupId) throws Exception {
		Role role = ServiceTestUtil.addRole(
			ServiceTestUtil.randomString(), RoleConstants.TYPE_REGULAR);

		RoleLocalServiceUtil.addGroupRole(groupId, role.getRoleId());

		return role.getRoleId();
	}

}