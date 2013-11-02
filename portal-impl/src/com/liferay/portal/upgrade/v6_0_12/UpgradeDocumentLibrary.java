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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Alexander Chow
 * @author Amos Fong
 */
public class UpgradeDocumentLibrary extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateFileEntries();
		updateFileVersions();
		updateLocks();
	}

	protected long getFileEntryId(long groupId, long folderId, String name)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select fileEntryId from DLFileEntry where groupId = ? and " +
					"folderId = ? and name = ?");

			ps.setLong(1, groupId);
			ps.setLong(2, folderId);
			ps.setString(3, name);

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getLong("fileEntryId");
			}

			return 0;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateFileEntries() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select fileEntryId, extension from DLFileEntry");

			rs = ps.executeQuery();

			while (rs.next()) {
				long fileEntryId = rs.getLong("fileEntryId");
				String extension = rs.getString("extension");

				String mimeType = MimeTypesUtil.getContentType(
					"A." + extension);

				runSQL(
					"update DLFileEntry set mimeType = '" + mimeType +
						"' where fileEntryId = " + fileEntryId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateFileVersions() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select fileVersionId, extension from DLFileVersion");

			rs = ps.executeQuery();

			while (rs.next()) {
				long fileVersionId = rs.getLong("fileVersionId");
				String extension = rs.getString("extension");

				String mimeType = MimeTypesUtil.getContentType(
					"A." + extension);

				runSQL(
					"update DLFileVersion set mimeType = '" + mimeType +
						"' where fileVersionId = " + fileVersionId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateLocks() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select lockId, key_ from Lock_ where className = ?");

			ps.setString(1, DLFileEntry.class.getName());

			rs = ps.executeQuery();

			while (rs.next()) {
				long lockId = rs.getLong("lockId");
				String key = rs.getString("key_");

				String[] keyArray = StringUtil.split(key, StringPool.POUND);

				if (keyArray.length != 3) {
					continue;
				}

				long groupId = GetterUtil.getLong(keyArray[0]);
				long folderId = GetterUtil.getLong(keyArray[1]);
				String name = keyArray[2];

				long fileEntryId = getFileEntryId(groupId, folderId, name);

				if (fileEntryId > 0) {
					runSQL(
						"update Lock_ set key_ = '" + fileEntryId +
							"' where lockId = " + lockId);
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}