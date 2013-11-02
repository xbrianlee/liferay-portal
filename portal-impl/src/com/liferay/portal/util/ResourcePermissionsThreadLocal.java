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

import com.liferay.portal.kernel.util.InitialThreadLocal;
import com.liferay.portal.model.ResourcePermission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class ResourcePermissionsThreadLocal {

	public static Map<Long, ResourcePermission> getResourcePermissions() {
		return _resourcePermissions.get();
	}

	public static void setResourcePermissions(
		List<ResourcePermission> resourcePermissions) {

		if (resourcePermissions != null) {
			Map<Long, ResourcePermission> resourcePermissionMap =
				new HashMap<Long, ResourcePermission>();

			for (ResourcePermission resourcePermission : resourcePermissions) {
				resourcePermissionMap.put(
					resourcePermission.getRoleId(), resourcePermission);
			}
		}
		else {
			_resourcePermissions.remove();
		}
	}

	private static ThreadLocal<Map<Long, ResourcePermission>>
		_resourcePermissions = new InitialThreadLocal
			<Map<Long, ResourcePermission>>(
				ResourcePermissionsThreadLocal.class + "._resourcePermissions",
				null);

}