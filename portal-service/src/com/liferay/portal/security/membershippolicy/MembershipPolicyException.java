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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class MembershipPolicyException extends PortalException {

	public static final int ORGANIZATION_MEMBERSHIP_NOT_ALLOWED = 1;

	public static final int ORGANIZATION_MEMBERSHIP_REQUIRED = 2;

	public static final int ROLE_MEMBERSHIP_NOT_ALLOWED = 3;

	public static final int ROLE_MEMBERSHIP_REQUIRED = 4;

	public static final int SITE_MEMBERSHIP_NOT_ALLOWED = 5;

	public static final int SITE_MEMBERSHIP_REQUIRED = 6;

	public static final int USER_GROUP_MEMBERSHIP_NOT_ALLOWED = 7;

	public static final int USER_GROUP_MEMBERSHIP_REQUIRED = 8;

	public MembershipPolicyException(int type) {
		_type = type;
	}

	public void addGroup(Group group) {
		_groups.add(group);
	}

	public void addOrganization(Organization organization) {
		_organizations.add(organization);
	}

	public void addRole(Role role) {
		_roles.add(role);
	}

	public void addUser(User user) {
		_users.add(user);
	}

	public void addUserGroup(UserGroup userGroup) {
		_userGroups.add(userGroup);
	}

	public List<Group> getGroups() {
		return _groups;
	}

	public List<Organization> getOrganizations() {
		return _organizations;
	}

	public List<Role> getRoles() {
		return _roles;
	}

	public int getType() {
		return _type;
	}

	public List<UserGroup> getUserGroups() {
		return _userGroups;
	}

	public List<User> getUsers() {
		return _users;
	}

	private List<Group> _groups = new ArrayList<Group>();
	private List<Organization> _organizations = new ArrayList<Organization>();
	private List<Role> _roles = new ArrayList<Role>();
	private int _type;
	private List<UserGroup> _userGroups = new ArrayList<UserGroup>();
	private List<User> _users = new ArrayList<User>();

}