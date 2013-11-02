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

package com.liferay.portal.upgrade.v6_0_5;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Julio Camarero
 * @author Brett Swaim
 */
public class UpgradeLayout extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select groupId, liveGroupId from Group_ where liveGroupId " +
					"!= 0");

			rs = ps.executeQuery();

			while (rs.next()) {
				long groupId = rs.getLong("groupId");
				long liveGroupId = rs.getLong("liveGroupId");

				updateUUID(groupId, liveGroupId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateUUID(long groupId, long liveGroupId) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select plid, privateLayout, layoutId, friendlyURL from " +
					"Layout where groupId = ?");

			ps.setLong(1, groupId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long plid = rs.getLong("plid");
				boolean privateLayout = rs.getBoolean("privateLayout");
				long layoutId = rs.getLong("layoutId");
				String friendlyURL = rs.getString("friendlyURL");

				updateUUID(
					plid, liveGroupId, privateLayout, layoutId, friendlyURL);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateUUID(
			long plid, long groupId, boolean privateLayout, long layoutId,
			String friendlyURL)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select uuid_ from Layout where groupId = ? and friendlyURL " +
					"= ?");

			ps.setLong(1, groupId);
			ps.setString(2, friendlyURL);

			rs = ps.executeQuery();

			if (!rs.next()) {
				ps = con.prepareStatement(
					"select uuid_ from Layout where groupId = ? and " +
						"privateLayout = ? and layoutId = ?");

				ps.setLong(1, groupId);
				ps.setBoolean(2, privateLayout);
				ps.setLong(3, layoutId);

				rs = ps.executeQuery();

				if (!rs.next()) {
					return;
				}
			}

			String uuid = rs.getString("uuid_");

			runSQL(
				"update Layout set uuid_ = '" + uuid + "' where plid = " +
					plid);
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}