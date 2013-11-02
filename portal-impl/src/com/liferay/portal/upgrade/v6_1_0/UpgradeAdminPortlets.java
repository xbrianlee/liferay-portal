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

package com.liferay.portal.upgrade.v6_1_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.ResourcePermission;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.util.PortletKeys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Juan Fernández
 */
public class UpgradeAdminPortlets extends UpgradeProcess {

	protected void addResourcePermission(
			long resourcePermissionId, long companyId, String name, int scope,
			String primKey, long roleId, long actionIds)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"insert into ResourcePermission (resourcePermissionId, " +
					"companyId, name, scope, primKey, roleId, actionIds) " +
						"values (?, ?, ?, ?, ?, ?, ?)");

			ps.setLong(1, resourcePermissionId);
			ps.setLong(2, companyId);
			ps.setString(3, name);
			ps.setInt(4, scope);
			ps.setString(5, primKey);
			ps.setLong(6, roleId);
			ps.setLong(7, actionIds);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateAccessInControlPanelPermission(
			PortletKeys.BLOGS, PortletKeys.BLOGS_ADMIN);

		updateAccessInControlPanelPermission(
			PortletKeys.MESSAGE_BOARDS, PortletKeys.MESSAGE_BOARDS_ADMIN);
	}

	protected long getBitwiseValue(String name, String actionId)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select bitwiseValue from ResourceAction where name = ? and " +
					"actionId = ?");

			ps.setString(1, name);
			ps.setString(2, actionId);

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getLong("bitwiseValue");
			}

			return 0;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected long getControlPanelGroupId() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select groupId from Group_ where name = '" +
					GroupConstants.CONTROL_PANEL + "'");

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getLong("groupId");
			}

			return 0;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateAccessInControlPanelPermission(
			String portletFrom, String portletTo)
		throws Exception {

		long bitwiseValue = getBitwiseValue(
			portletFrom, ActionKeys.ACCESS_IN_CONTROL_PANEL);

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select * from ResourcePermission where name = ?");

			ps.setString(1, portletFrom);

			rs = ps.executeQuery();

			while (rs.next()) {
				long resourcePermissionId = rs.getLong("resourcePermissionId");
				long actionIds = rs.getLong("actionIds");

				if ((actionIds & bitwiseValue) != 0) {
					actionIds = actionIds & (~bitwiseValue);

					runSQL(
						"update ResourcePermission set actionIds = " +
							actionIds + " where resourcePermissionId = " +
								resourcePermissionId);

					resourcePermissionId = increment(
						ResourcePermission.class.getName());

					long companyId = rs.getLong("companyId");
					int scope = rs.getInt("scope");
					String primKey = rs.getString("primKey");
					long roleId = rs.getLong("roleId");

					actionIds = rs.getLong("actionIds");

					actionIds |= bitwiseValue;

					addResourcePermission(
						resourcePermissionId, companyId, portletTo, scope,
						primKey, roleId, actionIds);
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}