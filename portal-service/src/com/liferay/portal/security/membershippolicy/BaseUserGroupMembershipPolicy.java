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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.persistence.UserGroupActionableDynamicQuery;

/**
 * @author Roberto Díaz
 * @author Sergio González
 */
public abstract class BaseUserGroupMembershipPolicy
	implements UserGroupMembershipPolicy {

	@Override
	@SuppressWarnings("unused")
	public boolean isMembershipAllowed(long userId, long userGroupId)
		throws PortalException, SystemException {

		try {
			checkMembership(
				new long[] {userId}, new long[] {userGroupId}, null);
		}
		catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	@SuppressWarnings("unused")
	public boolean isMembershipRequired(long userId, long userGroupId)
		throws PortalException, SystemException {

		try {
			checkMembership(
				new long[] {userId}, null, new long[] {userGroupId});
		}
		catch (Exception e) {
			return true;
		}

		return false;
	}

	@Override
	public void verifyPolicy() throws PortalException, SystemException {
		ActionableDynamicQuery actionableDynamicQuery =
			new UserGroupActionableDynamicQuery() {

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				UserGroup userGroup = (UserGroup)object;

				verifyPolicy(userGroup);
			}

		};

		actionableDynamicQuery.performActions();
	}

	@Override
	public void verifyPolicy(UserGroup userGroup)
		throws PortalException, SystemException {

		verifyPolicy(userGroup, null, null);
	}

}