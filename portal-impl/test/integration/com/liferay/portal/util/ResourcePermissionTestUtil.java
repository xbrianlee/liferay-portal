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

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.model.ResourcePermission;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.ServiceTestUtil;

/**
 * @author Alberto Chaparro
 */
public class ResourcePermissionTestUtil {

	public static ResourcePermission addResourcePermission(
			long actionIds, String name, long roleId)
		throws Exception {

		return addResourcePermission(
			actionIds, name, ServiceTestUtil.randomString(), roleId,
			ServiceTestUtil.nextInt());
	}

	public static ResourcePermission addResourcePermission(
			long actionIds, String name, String primKey, int scope)
		throws Exception {

		return addResourcePermission(
			actionIds, name, primKey, ServiceTestUtil.nextInt(), scope);
	}

	public static ResourcePermission addResourcePermission(
			long actionIds, String name, String primKey, long roleId, int scope)
		throws Exception {

		long resourcePermissionId = CounterLocalServiceUtil.increment(
			ResourcePermission.class.getName());

		ResourcePermission resourcePermission =
			ResourcePermissionLocalServiceUtil.createResourcePermission(
				resourcePermissionId);

		resourcePermission.setCompanyId(TestPropsValues.getCompanyId());
		resourcePermission.setName(name);
		resourcePermission.setScope(scope);
		resourcePermission.setPrimKey(primKey);
		resourcePermission.setRoleId(roleId);
		resourcePermission.setActionIds(actionIds);

		return ResourcePermissionLocalServiceUtil.addResourcePermission(
			resourcePermission);
	}

}