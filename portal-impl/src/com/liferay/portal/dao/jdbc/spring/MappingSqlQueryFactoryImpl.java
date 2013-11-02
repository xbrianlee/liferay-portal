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
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactory;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;

import javax.sql.DataSource;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class MappingSqlQueryFactoryImpl implements MappingSqlQueryFactory {

	@Override
	public <T> MappingSqlQuery<T> getMappingSqlQuery(
		DataSource dataSource, String sql, int[] types,
		RowMapper<T> rowMapper) {

		return new MappingSqlQueryImpl<T>(dataSource, sql, types, rowMapper);
	}

}