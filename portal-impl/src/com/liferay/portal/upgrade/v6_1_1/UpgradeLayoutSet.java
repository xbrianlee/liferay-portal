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

package com.liferay.portal.upgrade.v6_1_1;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jorge Ferrer
 * @author Julio Camarero
 */
public class UpgradeLayoutSet extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(4);

			sb.append("select Group_.groupId, Group_.liveGroupId, ");
			sb.append("LayoutSet.layoutSetId from LayoutSet inner join ");
			sb.append("Group_ on (LayoutSet.groupId = Group_.groupId and ");
			sb.append("Group_.liveGroupId > 0 and LayoutSet.logo = ?)");

			ps = con.prepareStatement(sb.toString());

			ps.setBoolean(1, true);

			rs = ps.executeQuery();

			while (rs.next()) {
				long groupId = rs.getLong("Group_.groupId");
				long layoutSetId = rs.getLong("LayoutSet.layoutSetId");

				runSQL(
					"update LayoutSet set logoId = 0 where groupId = " +
						groupId + " and layoutSetId = " + layoutSetId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}