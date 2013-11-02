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

package com.liferay.portal.kernel.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Brian Wing Shun Chan
 */
public interface RowMapper<T> {

	public static final RowMapper<Integer> COUNT = new CountRowMapper();

	public static final RowMapper<Long> PRIMARY_KEY = new PrimaryKeyRowMapper();

	public T mapRow(ResultSet rs, int rowNumber) throws SQLException;

}