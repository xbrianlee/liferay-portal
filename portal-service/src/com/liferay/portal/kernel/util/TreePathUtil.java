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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.TreeModel;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Shinn Lok
 */
public class TreePathUtil {

	public static void rebuildTree(
			long companyId, long defaultParentPrimaryKey,
			TreeModelFinder<?> treeModelFinder)
		throws SystemException {

		int size = GetterUtil.getInteger(
			PropsUtil.get(
				PropsKeys.MODEL_TREE_REBUILD_QUERY_RESULTS_BATCH_SIZE));

		Deque<Object[]> traces = new LinkedList<Object[]>();

		traces.push(
			new Object[] {defaultParentPrimaryKey, StringPool.SLASH, 0L});

		Object[] trace = null;

		while ((trace = traces.poll()) != null) {
			Long parentPrimaryKey = (Long)trace[0];
			String parentPath = (String)trace[1];
			Long previousPrimaryKey = (Long)trace[2];

			List<? extends TreeModel> treeModels =
				treeModelFinder.findTreeModels(
					previousPrimaryKey, companyId, parentPrimaryKey, size);

			if (treeModels.isEmpty()) {
				continue;
			}

			if (treeModels.size() == size) {
				TreeModel treeModel = treeModels.get(treeModels.size() - 1);

				trace[2] = treeModel.getPrimaryKeyObj();

				traces.push(trace);
			}

			for (TreeModel treeModel : treeModels) {
				String treePath = parentPath.concat(
					String.valueOf(treeModel.getPrimaryKeyObj())).concat(
						StringPool.SLASH);

				treeModel.updateTreePath(treePath);

				traces.push(
					new Object[] {treeModel.getPrimaryKeyObj(), treePath, 0L});
			}
		}
	}

	public static void rebuildTree(
		Session session, long companyId, String tableName,
		String parentTableName, String parentPrimaryKeyColumnName,
		boolean statusColumn) {

		rebuildTree(
			session, companyId, tableName, parentTableName,
			parentPrimaryKeyColumnName, statusColumn, false);
		rebuildTree(
			session, companyId, tableName, parentTableName,
			parentPrimaryKeyColumnName, statusColumn, true);
	}

	protected static void rebuildTree(
		Session session, long companyId, String tableName,
		String parentTableName, String parentPrimaryKeyColumnName,
		boolean statusColumn, boolean rootParent) {

		StringBundler sb = new StringBundler(26);

		sb.append("update ");
		sb.append(tableName);
		sb.append(" set ");

		if (rootParent) {
			sb.append("treePath = \"/0/\" ");
		}
		else {
			sb.append("treePath = (select ");
			sb.append(parentTableName);
			sb.append(".treePath from ");
			sb.append(parentTableName);
			sb.append(" where ");
			sb.append(parentTableName);
			sb.append(".");
			sb.append(parentPrimaryKeyColumnName);
			sb.append(" = ");
			sb.append(tableName);
			sb.append(".");
			sb.append(parentPrimaryKeyColumnName);
			sb.append(") ");
		}

		sb.append("where (");
		sb.append(tableName);
		sb.append(".companyId = ?) and (");
		sb.append(tableName);
		sb.append(".");
		sb.append(parentPrimaryKeyColumnName);

		if (rootParent) {
			sb.append(" = 0)");
		}
		else {
			sb.append(" != 0)");
		}

		if (statusColumn) {
			sb.append(" and (");
			sb.append(tableName);
			sb.append(".status != ?)");
		}

		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());

		QueryPos qPos = QueryPos.getInstance(sqlQuery);

		qPos.add(companyId);

		if (statusColumn) {
			qPos.add(WorkflowConstants.STATUS_IN_TRASH);
		}

		sqlQuery.executeUpdate();
	}

}