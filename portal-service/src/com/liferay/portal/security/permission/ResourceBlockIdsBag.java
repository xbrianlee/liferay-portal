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

package com.liferay.portal.security.permission;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a list resource block IDs and the actions that can be performed on
 * the resources in each.
 *
 * @author Connor McKay
 */
public class ResourceBlockIdsBag implements Serializable {

	public void addResourceBlockId(long resourceBlockId, long actionIdsLong) {
		actionIdsLong |= getActionIds(resourceBlockId);

		_permissions.put(resourceBlockId, actionIdsLong);
	}

	public long getActionIds(long resourceBlockId) {
		Long oldActionIdsLong = _permissions.get(resourceBlockId);

		if (oldActionIdsLong == null) {
			oldActionIdsLong = 0L;
		}

		return oldActionIdsLong;
	}

	public List<Long> getResourceBlockIds(long actionIdsLong) {
		List<Long> resourceBlockIds = new ArrayList<Long>();

		for (Map.Entry<Long, Long> permission : _permissions.entrySet()) {
			if ((permission.getValue() & actionIdsLong) == actionIdsLong) {
				resourceBlockIds.add(permission.getKey());
			}
		}

		return resourceBlockIds;
	}

	public boolean hasResourceBlockId(
		long resourceBlockId, long actionIdsLong) {

		if ((getActionIds(resourceBlockId) & actionIdsLong) == actionIdsLong) {
			return true;
		}
		else {
			return false;
		}
	}

	private Map<Long, Long> _permissions = new HashMap<Long, Long>();

}