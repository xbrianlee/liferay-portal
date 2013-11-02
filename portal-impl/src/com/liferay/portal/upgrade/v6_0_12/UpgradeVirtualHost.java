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

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Alexander Chow
 */
public class UpgradeVirtualHost extends UpgradeProcess {

	protected void addVirtualHost(
			long virtualHostId, long companyId, long layoutSetId,
			String hostname)
		throws Exception {

		runSQL(
			"insert into VirtualHost (virtualHostId, companyId, layoutSetId, " +
				"hostname) values (" + virtualHostId + ", " + companyId +
					", " + layoutSetId + ", '" + hostname + "')");
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateCompany();
		updateLayoutSet();
	}

	protected void updateCompany() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select companyId, virtualHost from Company where " +
					"virtualHost != ? and virtualHost is not null");

			ps.setString(1, StringPool.BLANK);

			rs = ps.executeQuery();

			while (rs.next()) {
				long companyId = rs.getLong("companyId");
				String hostname = rs.getString("virtualHost");

				long virtualHostId = increment();

				addVirtualHost(virtualHostId, companyId, 0, hostname);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		runSQL("alter table Company drop column virtualHost");
	}

	protected void updateLayoutSet() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select layoutSetId, companyId, virtualHost from LayoutSet " +
					"where virtualHost != ? and virtualHost is not null");

			ps.setString(1, StringPool.BLANK);

			rs = ps.executeQuery();

			while (rs.next()) {
				long layoutSetId = rs.getLong("layoutSetId");
				long companyId = rs.getLong("companyId");
				String hostname = rs.getString("virtualHost");

				long virtualHostId = increment();

				addVirtualHost(virtualHostId, companyId, layoutSetId, hostname);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		runSQL("alter table LayoutSet drop column virtualHost");
	}

}