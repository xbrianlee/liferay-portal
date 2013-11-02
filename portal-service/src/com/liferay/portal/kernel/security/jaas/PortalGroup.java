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

package com.liferay.portal.kernel.security.jaas;

import java.io.Serializable;

import java.security.Principal;
import java.security.acl.Group;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalGroup
	extends PortalPrincipal implements Group, Serializable {

	public PortalGroup(String groupName) {
		super(groupName);
	}

	@Override
	public boolean addMember(Principal user) {
		if (!_members.containsKey(user)) {
			_members.put(user, user);

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isMember(Principal member) {
		if (_members.containsKey(member)) {
			return true;
		}

		for (Principal principal : _members.values()) {
			if (principal instanceof Group) {
				Group group = (Group)principal;

				if (group.isMember(member)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public Enumeration<Principal> members() {
		return Collections.enumeration(_members.values());
	}

	@Override
	public boolean removeMember(Principal user) {
		Principal principal = _members.remove(user);

		if (principal != null) {
			return true;
		}
		else {
			return false;
		}
	}

	private Map<Principal, Principal> _members =
		new HashMap<Principal, Principal>();

}