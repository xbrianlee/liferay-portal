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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.service.ClassNameLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Shinn Lok
 */
public class VerifyWorkflow extends VerifyProcess {

	protected void deleteOrphanedWorkflowDefinitionLinks() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select distinct classNameId from WorkflowDefinitionLink");

			rs = ps.executeQuery();

			while (rs.next()) {
				long classNameId = rs.getLong("classNameId");

				ClassName className = ClassNameLocalServiceUtil.fetchClassName(
					classNameId);

				if (className == null) {
					continue;
				}

				String classNameValue = className.getValue();

				for (String[] orphanedAttachedModel :
						getOrphanedAttachedModels()) {

					String orphanedClassName = orphanedAttachedModel[0];

					if (classNameValue.equals(orphanedClassName)) {
						String orphanedTableName = orphanedAttachedModel[1];
						String orphanedColumnName = orphanedAttachedModel[2];

						deleteOrphanedWorkflowDefinitionLinks(
							orphanedTableName, orphanedColumnName);
					}
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void deleteOrphanedWorkflowDefinitionLinks(
			String tableName, String columnName)
		throws Exception {

		StringBundler sb = new StringBundler(6);

		sb.append("delete from WorkflowDefinitionLink where classPK not ");
		sb.append("in (select ");
		sb.append(columnName);
		sb.append(" from ");
		sb.append(tableName);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		runSQL(sb.toString());
	}

	@Override
	protected void doVerify() throws Exception {
		deleteOrphanedWorkflowDefinitionLinks();
	}

	protected String[][] getOrphanedAttachedModels() {
		return _ORPHANED_ATTACHED_MODELS;
	}

	private static final String[][] _ORPHANED_ATTACHED_MODELS = new String[][] {
		new String[] {
			"com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess",
			"KaleoProcess", "kaleoProcessId"
		}
	};

}