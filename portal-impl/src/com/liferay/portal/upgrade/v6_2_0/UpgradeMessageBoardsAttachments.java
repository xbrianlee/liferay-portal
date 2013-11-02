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

package com.liferay.portal.upgrade.v6_2_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.v6_2_0.BaseUpgradeAttachments;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.messageboards.model.MBMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Eudaldo Alonso
 */
public class UpgradeMessageBoardsAttachments extends BaseUpgradeAttachments {

	@Override
	protected String getClassName() {
		return MBMessage.class.getName();
	}

	@Override
	protected long getContainerModelFolderId(
			long groupId, long companyId, long resourcePrimKey,
			long containerModelId, long userId, String userName,
			Timestamp createDate)
		throws Exception {

		long repositoryId = getRepositoryId(
			groupId, companyId, userId, userName, createDate, getClassNameId(),
			getPortletId());

		long repositoryFolderId = getFolderId(
			groupId, companyId, userId, userName, createDate, repositoryId,
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, getPortletId(), false);

		long threadFolderId = getFolderId(
			groupId, companyId, userId, userName, createDate, repositoryId,
			repositoryFolderId, String.valueOf(containerModelId), false);

		long messageFolderId = getFolderId(
			groupId, companyId, userId, userName, createDate, repositoryId,
			threadFolderId, String.valueOf(resourcePrimKey), false);

		return messageFolderId;
	}

	@Override
	protected String getDirName(long containerModelId, long resourcePrimKey) {
		return "messageboards/" + containerModelId + "/" + resourcePrimKey;
	}

	@Override
	protected String getPortletId() {
		return PortletKeys.MESSAGE_BOARDS;
	}

	@Override
	protected void updateAttachments() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select messageId, groupId, companyId, userId, userName, " +
					"threadId from MBMessage where classNameId = 0 and " +
						"classPK = 0");

			rs = ps.executeQuery();

			while (rs.next()) {
				long messageId = rs.getLong("messageId");
				long groupId = rs.getLong("groupId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String userName = rs.getString("userName");
				long threadId = rs.getLong("threadId");

				updateEntryAttachments(
					companyId, groupId, messageId, threadId, userId, userName);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}