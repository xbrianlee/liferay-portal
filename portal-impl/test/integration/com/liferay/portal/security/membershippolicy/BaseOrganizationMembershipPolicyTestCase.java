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
import com.liferay.portal.util.OrganizationTestUtil;
import com.liferay.portal.util.RoleTestUtil;

import org.junit.After;
import org.junit.Before;

/**
 * @author Roberto Díaz
 */
public abstract class BaseOrganizationMembershipPolicyTestCase
	extends BaseMembersipPolicyTestCase {

	public static long[] getForbiddenOrganizationIds() {
		return _forbiddenOrganizationIds;
	}

	public static long[] getForbiddenRoleIds() {
		return _forbiddenRoleIds;
	}

	public static long[] getRequiredOrganizationIds() {
		return _requiredOrganizationIds;
	}

	public static long[] getRequiredRoleIds() {
		return _requiredRoleIds;
	}

	public static long[] getStandardOrganizationIds() {
		return _standardOrganizationIds;
	}

	public static long[] getStandardRoleIds() {
		return _standardRoleIds;
	}

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		organization = OrganizationTestUtil.addOrganization();
	}

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		organization = null;

		_forbiddenOrganizationIds = new long[2];
		_forbiddenRoleIds = new long[2];
		_requiredOrganizationIds = new long[2];
		_requiredRoleIds = new long[2];
		_standardOrganizationIds = new long[2];
		_standardRoleIds = new long[2];
	}

	protected long[] addForbiddenOrganizations() throws Exception {
		Organization forbiddenOrganization1 =
			OrganizationTestUtil.addOrganization();

		_forbiddenOrganizationIds[0] =
			forbiddenOrganization1.getOrganizationId();

		Organization forbiddenOrganization2 =
			OrganizationTestUtil.addOrganization();

		_forbiddenOrganizationIds[1] =
			forbiddenOrganization2.getOrganizationId();

		return _forbiddenOrganizationIds;
	}

	protected long[] addForbiddenRoles() throws Exception {
		_forbiddenRoleIds[0] = RoleTestUtil.addOrganizationRole(
			group.getGroupId());
		_forbiddenRoleIds[1] = RoleTestUtil.addOrganizationRole(
			group.getGroupId());

		return _forbiddenRoleIds;
	}

	protected long[] addRequiredOrganizations() throws Exception {
		Organization requiredOrganization1 =
			OrganizationTestUtil.addOrganization();

		_requiredOrganizationIds[0] = requiredOrganization1.getOrganizationId();

		Organization requiredOrganization2 =
			OrganizationTestUtil.addOrganization();

		_requiredOrganizationIds[1] = requiredOrganization2.getOrganizationId();

		return _requiredOrganizationIds;
	}

	protected long[] addRequiredRoles() throws Exception {
		_requiredRoleIds[0] = RoleTestUtil.addOrganizationRole(
			group.getGroupId());
		_requiredRoleIds[1] = RoleTestUtil.addOrganizationRole(
			group.getGroupId());

		return _requiredRoleIds;
	}

	protected long[] addStandardOrganizations() throws Exception {
		Organization standardOrganization1 =
			OrganizationTestUtil.addOrganization();

		_standardOrganizationIds[0] = standardOrganization1.getOrganizationId();

		Organization standardOrganization2 =
			OrganizationTestUtil.addOrganization();

		_standardOrganizationIds[1] = standardOrganization2.getOrganizationId();

		return _standardOrganizationIds;
	}

	protected long[] addStandardRoles() throws Exception {
		_standardRoleIds[0] = RoleTestUtil.addOrganizationRole(
			group.getGroupId());
		_standardRoleIds[1] = RoleTestUtil.addOrganizationRole(
			group.getGroupId());

		return _standardRoleIds;
	}

	protected Organization organization;

	private static long[] _forbiddenOrganizationIds = new long[2];
	private static long[] _forbiddenRoleIds = new long[2];
	private static long[] _requiredOrganizationIds = new long[2];
	private static long[] _requiredRoleIds = new long[2];
	private static long[] _standardOrganizationIds = new long[2];
	private static long[] _standardRoleIds = new long[2];

}