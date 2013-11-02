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

/**
 * @author Minhchau Dang
 */
public class SQLServer2008Dialect
	extends org.hibernate.dialect.SQLServer2008Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		return SQLServerLimitStringUtil.getLimitString(sql, offset, limit);
	}

	@Override
	public boolean supportsLimitOffset() {
		return _SUPPORTS_LIMIT_OFFSET;
	}

	@Override
	public boolean supportsVariableLimit() {
		return _SUPPORTS_VARIABLE_LIMIT;
	}

	private static final boolean _SUPPORTS_LIMIT_OFFSET = true;

	private static final boolean _SUPPORTS_VARIABLE_LIMIT = false;

}