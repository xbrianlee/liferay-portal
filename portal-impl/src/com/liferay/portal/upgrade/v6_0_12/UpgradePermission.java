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

package com.liferay.portal.upgrade.v6_0_12;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;

import java.util.List;

/**
 * @author Alexander Chow
 */
public class UpgradePermission extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {

		// PermissionLocalServiceUtil.setContainerResourcePermissions() requires
		// an updated Company and Role_ table

		runSQL("alter table Company add active_ BOOLEAN");
		runSQL("update Company set active_ = TRUE");

		runSQL(
			"update Role_ set name = 'Site Administrator' where name = " +
				"'Community Administrator'");
		runSQL(
			"update Role_ set name = 'Site Member' where name = 'Community " +
				"Member'");
		runSQL(
			"update Role_ set name = 'Site Owner' where name = 'Community " +
				"Owner'");
		runSQL(
			"update Role_ set name = 'Organization User' where name = " +
				"'Organization Member'");

		// LPS-14202 and LPS-17841

		RoleLocalServiceUtil.checkSystemRoles();

		updatePermissions("com.liferay.portlet.bookmarks", true, true);
		updatePermissions("com.liferay.portlet.documentlibrary", false, true);
		updatePermissions("com.liferay.portlet.imagegallery", true, true);
		updatePermissions("com.liferay.portlet.messageboards", true, true);
		updatePermissions("com.liferay.portlet.shopping", true, true);
	}

	protected void updatePermissions(
			String name, boolean community, boolean guest)
		throws Exception {

		List<String> modelActions = ResourceActionsUtil.getModelResourceActions(
			name);

		ResourceActionLocalServiceUtil.checkResourceActions(name, modelActions);

		int scope = ResourceConstants.SCOPE_INDIVIDUAL;
		long actionIdsLong = 1;

		if (community) {
			ResourcePermissionLocalServiceUtil.addResourcePermissions(
				name, RoleConstants.SITE_MEMBER, scope, actionIdsLong);
			ResourcePermissionLocalServiceUtil.addResourcePermissions(
				name, RoleConstants.ORGANIZATION_USER, scope, actionIdsLong);
		}

		if (guest) {
			ResourcePermissionLocalServiceUtil.addResourcePermissions(
				name, RoleConstants.GUEST, scope, actionIdsLong);
		}

		ResourcePermissionLocalServiceUtil.addResourcePermissions(
			name, RoleConstants.OWNER, scope, actionIdsLong);
	}

}