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

package com.liferay.portal.dao.orm.hibernate;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Shepherd Ching
 * @author Jian Cao
 * @author Laszlo Csontos
 */
public class DB2Dialect extends org.hibernate.dialect.DB2Dialect {

	public DB2Dialect() {
		super();

		registerKeyword("for");
		registerKeyword("optimize");
	}

	@Override
	public String getForUpdateString() {
		return " for read only with rs use and keep exclusive locks";
	}

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		boolean hasOffset = false;

		if ((offset > 0) || forceLimitUsage()) {
			hasOffset = true;
		}

		StringBundler sb = null;

		if (hasOffset) {
			sb = new StringBundler(11);
		}
		else {
			sb = new StringBundler(5);
		}

		if (!hasOffset) {
			addQueryForLimitedRows(sb, sql, limit);
			addOptimizeForLimitedRows(sb, limit);

			return sb.toString();
		}

		// Outer query

		sb.append("SELECT outerQuery.* FROM (");

		// Inner query

		sb.append("SELECT innerQuery.*, ");
		sb.append("ROW_NUMBER() OVER() AS rowNumber_ FROM (");

		addQueryForLimitedRows(sb, sql, limit);

		sb.append(") AS innerQuery");

		// Offset

		sb.append(") AS outerQuery WHERE rowNumber_ > ");
		sb.append(offset);

		addOptimizeForLimitedRows(sb, limit);

		return sb.toString();
	}

	@Override
	public boolean supportsVariableLimit() {
		return _SUPPORTS_VARIABLE_LIMIT;
	}

	protected void addOptimizeForLimitedRows(StringBundler sb, int limit) {
		sb.append(StringPool.SPACE);
		sb.append(
			StringUtil.replace(
				_SQL_OPTIMIZE_FOR_LIMITED_ROWS, "[$LIMIT$]",
				String.valueOf(limit)));
	}

	protected void addQueryForLimitedRows(
		StringBundler sb, String sql, int limit) {

		sb.append(sql);
		sb.append(StringPool.SPACE);
		sb.append(
			StringUtil.replace(
				_SQL_FETCH_FIRST_LIMITED_ROWS_ONLY, "[$LIMIT$]",
				String.valueOf(limit)));
	}

	private static final String _SQL_FETCH_FIRST_LIMITED_ROWS_ONLY =
		"FETCH FIRST [$LIMIT$] ROWS ONLY";

	private static final String _SQL_OPTIMIZE_FOR_LIMITED_ROWS =
		"OPTIMIZE FOR [$LIMIT$] ROWS";

	private static final boolean _SUPPORTS_VARIABLE_LIMIT = false;

}