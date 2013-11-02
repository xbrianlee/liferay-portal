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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.util.PortalInstances;
import com.liferay.portal.util.PropsValues;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Juan Fernández
 */
public class UpgradeSubscription extends UpgradeProcess {

	protected void addSubscription(
			long subscriptionId, long companyId, long userId, String userName,
			Timestamp createDate, Timestamp modifiedDate, long classNameId,
			long classPK, String frequency)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(4);

			sb.append("insert into Subscription (subscriptionId, companyId, ");
			sb.append("userId, userName, createDate, modifiedDate, ");
			sb.append("classNameId, classPK, frequency) values (?, ?, ?, ?, ");
			sb.append("?, ?, ?, ?, ?)");

			String sql = sb.toString();

			ps = con.prepareStatement(sql);

			ps.setLong(1, subscriptionId);
			ps.setLong(2, companyId);
			ps.setLong(3, userId);
			ps.setString(4, userName);
			ps.setTimestamp(5, createDate);
			ps.setTimestamp(6, modifiedDate);
			ps.setLong(7, classNameId);
			ps.setLong(8, classPK);
			ps.setString(9, frequency);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!PropsValues.DISCUSSION_SUBSCRIBE_BY_DEFAULT) {
			return;
		}

		long[] companyIds = PortalInstances.getCompanyIdsBySQL();

		for (long companyId : companyIds) {
			updateMBMessages(companyId);
		}
	}

	protected boolean hasSubscription(
			long companyId, long userId, long classNameId, long classPK)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select count(*) from Subscription where companyId = ? and " +
					"userId = ? and classNameId = ? and classPK = ?");

			ps.setLong(1, companyId);
			ps.setLong(2, userId);
			ps.setLong(3, classNameId);
			ps.setLong(4, classPK);

			rs = ps.executeQuery();

			while (rs.next()) {
				int count = rs.getInt(1);

				if (count > 0) {
					return true;
				}
			}

			return false;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateMBMessages(long companyId) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(8);

			sb.append("select userId, MIN(userName) as userName, ");
			sb.append("classNameId, classPK, MIN(createDate) as createDate, ");
			sb.append("MIN(modifiedDate) as modifiedDate from MBMessage ");
			sb.append("where (companyId = ");
			sb.append(companyId);
			sb.append(") and ");
			sb.append("(classNameId != 0) and (parentMessageId != 0) ");
			sb.append("group by userId, classNameId, classPK");

			String sql = sb.toString();

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long userId = rs.getLong("userId");
				String userName = rs.getString("userName");
				Timestamp createDate = rs.getTimestamp("createDate");
				Timestamp modifiedDate = rs.getTimestamp("modifiedDate");
				long classNameId = rs.getLong("classNameId");
				long classPK = rs.getLong("classPK");

				if (hasSubscription(companyId, userId, classNameId, classPK)) {
					continue;
				}

				long subscriptionId = increment();
				String frequency = "instant";

				addSubscription(
					subscriptionId, companyId, userId, userName, createDate,
					modifiedDate, classNameId, classPK, frequency);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}