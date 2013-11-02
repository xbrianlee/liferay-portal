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

package com.liferay.portal.model.impl;

import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class OrgGroupRoleImpl extends OrgGroupRoleBaseImpl {

	public OrgGroupRoleImpl() {
	}

	@Override
	public boolean containsGroup(List<Group> groups) {
		if (groups == null) {
			return false;
		}

		for (Group group : groups) {
			if (group.getGroupId() == getGroupId()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean containsOrganization(List<Organization> organizations) {
		if (organizations == null) {
			return false;
		}

		for (Organization organization : organizations) {
			if (organization.getOrganizationId() == getOrganizationId()) {
				return true;
			}
		}

		return false;
	}

}