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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.model.MBDiscussion;
import com.liferay.portlet.messageboards.model.MBMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Shuyang Zhou
 * @author Kalman Vincze
 */
public class UpgradeMessageBoards extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateMessage();
		updateRatings();
		updateThread();
	}

	protected void updateMessage() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select messageId, body from MBMessage where (body like " +
					"'%<3%') or (body like '%>_>%') or (body like '%<_<%')");

			rs = ps.executeQuery();

			while (rs.next()) {
				long messageId = rs.getLong("messageId");
				String body = rs.getString("body");

				body = StringUtil.replace(
					body, new String[] {"<3", ">_>", "<_<"},
					new String[] {":love:", ":glare:", ":dry:"});

				updateMessage(messageId, body);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateMessage(long messageId, String body) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"update MBMessage set body = ? where messageId = " + messageId);

			ps.setString(1, body);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	protected void updateRatings() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select MBMessage.messageId from MBMessage where " +
					"MBMessage.categoryId = " +
						MBCategoryConstants.DISCUSSION_CATEGORY_ID);

			rs = ps.executeQuery();

			while (rs.next()) {
				long messageId = rs.getLong("messageId");

				updateRatings(messageId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateRatings(long classPK) throws Exception {
		long discussionClassNameId = PortalUtil.getClassNameId(
			MBDiscussion.class);
		long messageClassNameId = PortalUtil.getClassNameId(MBMessage.class);

		runSQL(
			"update RatingsStats set classNameId = " + discussionClassNameId +
				" where classNameId = " + messageClassNameId +
					" and classPK = " + classPK);

		runSQL(
			"update RatingsEntry set classNameId = " + discussionClassNameId +
				" where classNameId = " + messageClassNameId +
					" and classPK = " + classPK);
	}

	protected void updateThread() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select MBThread.threadId, MBMessage.companyId, " +
					"MBMessage.userId from MBThread inner join MBMessage on " +
						"MBThread.rootMessageId = MBMessage.messageId");

			rs = ps.executeQuery();

			while (rs.next()) {
				long threadId = rs.getLong("threadId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");

				runSQL(
					"update MBThread set companyId = " + companyId +
						", rootMessageUserId = " + userId +
							" where threadId = " + threadId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}