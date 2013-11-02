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
import com.liferay.portal.model.Role;
import com.liferay.portal.service.persistence.RoleActionableDynamicQuery;

/**
 * @author Roberto Díaz
 * @author Sergio González
 */
public abstract class BaseRoleMembershipPolicy implements RoleMembershipPolicy {

	@Override
	@SuppressWarnings("unused")
	public boolean isRoleAllowed(long userId, long roleId)
		throws PortalException, SystemException {

		try {
			checkRoles(new long[] {userId}, new long[] {roleId}, null);
		}
		catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	@SuppressWarnings("unused")
	public boolean isRoleRequired(long userId, long roleId)
		throws PortalException, SystemException {

		try {
			checkRoles(new long[] {userId}, null, new long[] {roleId});
		}
		catch (Exception e) {
			return true;
		}

		return false;
	}

	@Override
	public void verifyPolicy() throws PortalException, SystemException {
		ActionableDynamicQuery actionableDynamicQuery =
			new RoleActionableDynamicQuery() {

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				Role role = (Role)object;

				verifyPolicy(role);
			}

		};

		actionableDynamicQuery.performActions();
	}

	@Override
	public void verifyPolicy(Role role)
		throws PortalException, SystemException {

		verifyPolicy(role, null, null);
	}

}