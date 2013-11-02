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

package com.liferay.portal.kernel.upgrade.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Alexander Chow
 * @author Brian Wing Shun Chan
 */
public interface UpgradeTable {

	public void appendColumn(StringBuilder sb, Object value, boolean last)
		throws Exception;

	public void appendColumn(
			StringBuilder sb, ResultSet rs, String name, Integer type,
			boolean last)
		throws Exception;

	public String getCreateSQL() throws Exception;

	public String getDeleteSQL() throws Exception;

	public String[] getIndexesSQL() throws Exception;

	public String getInsertSQL() throws Exception;

	public String getSelectSQL() throws Exception;

	public String getTempFileName();

	public boolean isAllowUniqueIndexes() throws Exception;

	public boolean isDeleteTempFile();

	public void setAllowUniqueIndexes(boolean allowUniqueIndexes)
		throws Exception;

	public void setColumn(
			PreparedStatement ps, int index, Integer type, String value)
		throws Exception;

	public void setCreateSQL(String createSQL) throws Exception;

	public void setDeleteTempFile(boolean deleteTempFile);

	public void setIndexesSQL(String[] indexesSQL) throws Exception;

	public void updateTable() throws Exception;

}