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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.wiki.model.WikiPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Eudaldo Alonso
 */
public class UpgradeWikiAttachments extends BaseUpgradeAttachments {

	@Override
	protected String getClassName() {
		return WikiPage.class.getName();
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

		long nodeFolderId = getFolderId(
			groupId, companyId, userId, userName, createDate, repositoryId,
			repositoryFolderId, String.valueOf(containerModelId), false);

		long pageFolderId = getFolderId(
			groupId, companyId, userId, userName, createDate, repositoryId,
			nodeFolderId, String.valueOf(resourcePrimKey), false);

		return pageFolderId;
	}

	@Override
	protected String getDirName(long containerModelId, long resourcePrimKey) {
		return "wiki/" + resourcePrimKey;
	}

	@Override
	protected String getPortletId() {
		return PortletKeys.WIKI;
	}

	@Override
	protected void updateAttachments() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(4);

			sb.append("select resourcePrimKey, groupId, companyId, ");
			sb.append("MIN(userId) as userId, MIN(userName) as userName, ");
			sb.append("nodeId from WikiPage group by resourcePrimKey, ");
			sb.append("groupId, companyId, nodeId");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long resourcePrimKey = rs.getLong("resourcePrimKey");
				long groupId = rs.getLong("groupId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String userName = rs.getString("userName");
				long nodeId = rs.getLong("nodeId");

				updateEntryAttachments(
					companyId, groupId, resourcePrimKey, nodeId, userId,
					userName);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}