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

package com.liferay.portal.dao.jdbc.spring;

import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;

/**
 * @author Brian Wing Shun Chan
 */
public class MappingSqlQueryImpl<T>
	extends org.springframework.jdbc.object.MappingSqlQuery<T>
	implements MappingSqlQuery<T> {

	public MappingSqlQueryImpl(
		DataSource dataSource, String sql, int[] types,
		RowMapper<T> rowMapper) {

		super(dataSource, sql);

		for (int type : types) {
			declareParameter(new SqlParameter(type));
		}

		_rowMapper = rowMapper;

		compile();
	}

	@Override
	protected T mapRow(ResultSet rs, int rowNumber) throws SQLException {
		return _rowMapper.mapRow(rs, rowNumber);
	}

	private RowMapper<T> _rowMapper;

}