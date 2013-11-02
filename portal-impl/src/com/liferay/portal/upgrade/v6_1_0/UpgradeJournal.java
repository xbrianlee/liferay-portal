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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.upgrade.v6_1_0.util.JournalArticleTable;
import com.liferay.portal.upgrade.v6_1_0.util.JournalStructureTable;
import com.liferay.portal.upgrade.v6_1_0.util.JournalTemplateTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Juan Fernández
 * @author Sergio González
 */
public class UpgradeJournal extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type JournalArticle title STRING null");

			runSQL("alter_column_type JournalStructure name STRING null");
			runSQL(
				"alter_column_type JournalStructure description STRING null");

			runSQL("alter_column_type JournalTemplate name STRING null");
			runSQL("alter_column_type JournalTemplate description STRING null");
		}
		catch (SQLException sqle) {
			upgradeTable(
				JournalArticleTable.TABLE_NAME,
				JournalArticleTable.TABLE_COLUMNS,
				JournalArticleTable.TABLE_SQL_CREATE,
				JournalArticleTable.TABLE_SQL_ADD_INDEXES);

			upgradeTable(
				JournalStructureTable.TABLE_NAME,
				JournalStructureTable.TABLE_COLUMNS,
				JournalStructureTable.TABLE_SQL_CREATE,
				JournalStructureTable.TABLE_SQL_ADD_INDEXES);

			upgradeTable(
				JournalTemplateTable.TABLE_NAME,
				JournalTemplateTable.TABLE_COLUMNS,
				JournalTemplateTable.TABLE_SQL_CREATE,
				JournalTemplateTable.TABLE_SQL_ADD_INDEXES);
		}

		updateStructureXsd();
	}

	protected void updateStructureXsd() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			runSQL(
				"update JournalStructure set xsd = replace(xsd, " +
					"'image_gallery', 'document_library') where xsd like " +
						"'%image_gallery%'");
		}
		catch (Exception e) {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select id_, xsd from JournalStructure where xsd like " +
					"'%image_gallery%'");

			rs = ps.executeQuery();

			while (rs.next()) {
				long id = rs.getLong("id_");
				String xsd = rs.getString("xsd");

				xsd = StringUtil.replace(
					xsd, "image_gallery", "document_library");

				updateStructureXsd(id, xsd);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateStructureXsd(long id, String xsd) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"update JournalStructure set xsd = ? where id_ = ?");

			ps.setString(1, xsd);
			ps.setLong(2, id);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}