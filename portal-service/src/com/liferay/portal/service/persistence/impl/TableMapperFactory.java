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

package com.liferay.portal.service.persistence.impl;

import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.persistence.BasePersistence;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shuyang Zhou
 */
public class TableMapperFactory {

	public static
		<L extends BaseModel<L>, R extends BaseModel<R>> TableMapper<L, R>
			getTableMapper(
				String tableName, String leftColumnName, String rightColumnName,
				BasePersistence<L> leftPersistence,
				BasePersistence<R> rightPersistence) {

		TableMapper<?, ?> tableMapper = tableMappers.get(tableName);

		if (tableMapper == null) {
			TableMapperImpl<L, R> tableMapperImpl =
				new TableMapperImpl<L, R>(
					tableName, leftColumnName, rightColumnName, leftPersistence,
					rightPersistence);

			tableMapperImpl.setReverseTableMapper(
				new ReverseTableMapper<R, L>(tableMapperImpl));

			tableMapper = tableMapperImpl;

			tableMappers.put(tableName, tableMapper);
		}
		else if (!tableMapper.matches(leftColumnName, rightColumnName)) {
			tableMapper = tableMapper.getReverseTableMapper();
		}

		return (TableMapper<L, R>)tableMapper;
	}

	protected static Map<String, TableMapper<?, ?>> tableMappers =
		new ConcurrentHashMap<String, TableMapper<?, ?>>();

}