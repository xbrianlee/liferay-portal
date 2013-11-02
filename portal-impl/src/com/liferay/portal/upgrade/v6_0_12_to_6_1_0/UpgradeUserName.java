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

package com.liferay.portal.upgrade.v6_0_12_to_6_1_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.auth.FullNameGenerator;
import com.liferay.portal.security.auth.FullNameGeneratorFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Hugo Huijser
 */
public class UpgradeUserName extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateTable("PollsVote", true);
	}

	protected void updateTable(String tableName, boolean setCompanyId)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(11);

			sb.append("select distinct User_.companyId, User_.userId, ");
			sb.append("User_.firstName, User_.middleName, User_.lastName ");
			sb.append("from User_ inner join ");
			sb.append(tableName);
			sb.append(" on ");
			sb.append(tableName);
			sb.append(".userId = User_.userId where ");
			sb.append(tableName);
			sb.append(".userName is null or ");
			sb.append(tableName);
			sb.append(".userName = ''");

			String sql = sb.toString();

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String firstName = rs.getString("firstName");
				String middleName = rs.getString("middleName");
				String lastName = rs.getString("lastName");

				FullNameGenerator fullNameGenerator =
					FullNameGeneratorFactory.getInstance();

				String fullName = fullNameGenerator.getFullName(
					firstName, middleName, lastName);

				fullName = fullName.replace(
					StringPool.APOSTROPHE, StringPool.DOUBLE_APOSTROPHE);

				if (setCompanyId) {
					sb = new StringBundler(8);

					sb.append("update ");
					sb.append(tableName);
					sb.append(" set companyId = ");
					sb.append(companyId);
					sb.append(", userName = '");
					sb.append(fullName);
					sb.append("' where userId = ");
					sb.append(userId);
				}
				else {
					sb = new StringBundler(6);

					sb.append("update ");
					sb.append(tableName);
					sb.append(" set userName = '");
					sb.append(fullName);
					sb.append("' where userId = ");
					sb.append(userId);
				}

				runSQL(sb.toString());
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}