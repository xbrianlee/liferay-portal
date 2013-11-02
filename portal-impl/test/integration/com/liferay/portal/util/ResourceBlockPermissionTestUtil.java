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
import com.liferay.portal.model.ResourceBlockPermission;
import com.liferay.portal.service.ResourceBlockPermissionLocalServiceUtil;

/**
 * @author Alberto Chaparro
 */
public class ResourceBlockPermissionTestUtil {

	public static ResourceBlockPermission addResourceBlockPermission(
			long resourceBlockId, long roleId, long actionIds)
		throws Exception {

		long resourceBlockPermissionId = CounterLocalServiceUtil.increment(
			ResourceBlockPermission.class.getName());

		ResourceBlockPermission resourceBlockPermission =
			ResourceBlockPermissionLocalServiceUtil.
				createResourceBlockPermission(resourceBlockPermissionId);

		resourceBlockPermission.setResourceBlockId(resourceBlockId);
		resourceBlockPermission.setRoleId(roleId);
		resourceBlockPermission.setActionIds(actionIds);

		return ResourceBlockPermissionLocalServiceUtil.
			addResourceBlockPermission(resourceBlockPermission);
	}

}