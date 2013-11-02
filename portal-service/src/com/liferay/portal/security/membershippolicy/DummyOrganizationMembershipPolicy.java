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

import com.liferay.portal.model.Organization;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetTag;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * @author Roberto Díaz
 * @author Sergio González
 */
public class DummyOrganizationMembershipPolicy
	extends BaseOrganizationMembershipPolicy {

	@Override
	public void checkMembership(
		long[] userIds, long[] addOrganizationIds,
		long[] removeOrganizationIds) {
	}

	@Override
	public boolean isMembershipAllowed(long userId, long groupId) {
		return true;
	}

	@Override
	public boolean isMembershipRequired(long userId, long groupId) {
		return false;
	}

	@Override
	public boolean isRoleAllowed(long userId, long groupId, long roleId) {
		return true;
	}

	@Override
	public boolean isRoleRequired(long userId, long groupId, long roleId) {
		return false;
	}

	@Override
	public void propagateMembership(
		long[] userIds, long[] addOrganizationIds,
		long[] removeOrganizationIds) {
	}

	@Override
	public void verifyPolicy(Organization organization) {
	}

	@Override
	public void verifyPolicy(
		Organization organization, Organization oldOrganization,
		List<AssetCategory> oldAssetCategories, List<AssetTag> oldAssetTags,
		Map<String, Serializable> oldExpandoAttributes) {
	}

}