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
import com.liferay.portal.model.ResourceTypePermission;
import com.liferay.portal.service.ResourceTypePermissionLocalServiceUtil;
import com.liferay.portal.service.ServiceTestUtil;

/**
 * @author Alberto Chaparro
 */
public class ResourceTypePermissionTestUtil {

	public static ResourceTypePermission addResourceTypePermission(
			long actionIds, long groupId, String name)
		throws Exception {

		return addResourceTypePermission(
			actionIds, groupId, name, ServiceTestUtil.nextLong());
	}

	public static ResourceTypePermission addResourceTypePermission(
			long actionIds, long groupId, String name, long roleId)
		throws Exception {

		long resourceTypePermissionId = CounterLocalServiceUtil.increment(
				ResourceTypePermission.class.getName());

		ResourceTypePermission resourceTypePermission =
			ResourceTypePermissionLocalServiceUtil.createResourceTypePermission(
				resourceTypePermissionId);

		resourceTypePermission.setCompanyId(TestPropsValues.getCompanyId());
		resourceTypePermission.setGroupId(groupId);
		resourceTypePermission.setName(name);
		resourceTypePermission.setRoleId(roleId);
		resourceTypePermission.setActionIds(actionIds);

		return ResourceTypePermissionLocalServiceUtil.addResourceTypePermission(
			resourceTypePermission);
	}

}