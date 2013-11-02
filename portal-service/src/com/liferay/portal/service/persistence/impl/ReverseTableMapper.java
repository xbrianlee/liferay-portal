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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.BaseModel;

import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class ReverseTableMapper<L extends BaseModel<L>, R extends BaseModel<R>>
	implements TableMapper<L, R> {

	public ReverseTableMapper(TableMapper<R, L> tableMapper) {
		_tableMapper = tableMapper;
	}

	@Override
	public boolean addTableMapping(long leftPrimaryKey, long rightPrimaryKey)
		throws SystemException {

		return _tableMapper.addTableMapping(rightPrimaryKey, leftPrimaryKey);
	}

	@Override
	public boolean containsTableMapping(
			long leftPrimaryKey, long rightPrimaryKey)
		throws SystemException {

		return _tableMapper.containsTableMapping(
			rightPrimaryKey, leftPrimaryKey);
	}

	@Override
	public int deleteLeftPrimaryKeyTableMappings(long leftPrimaryKey)
		throws SystemException {

		return _tableMapper.deleteRightPrimaryKeyTableMappings(leftPrimaryKey);
	}

	@Override
	public int deleteRightPrimaryKeyTableMappings(long rightPrimaryKey)
		throws SystemException {

		return _tableMapper.deleteLeftPrimaryKeyTableMappings(rightPrimaryKey);
	}

	@Override
	public boolean deleteTableMapping(long leftPrimaryKey, long rightPrimaryKey)
		throws SystemException {

		return _tableMapper.deleteTableMapping(rightPrimaryKey, leftPrimaryKey);
	}

	@Override
	public List<L> getLeftBaseModels(
			long rightPrimaryKey, int start, int end, OrderByComparator obc)
		throws SystemException {

		return _tableMapper.getRightBaseModels(
			rightPrimaryKey, start, end, obc);
	}

	@Override
	public long[] getLeftPrimaryKeys(long rightPrimaryKey)
		throws SystemException {

		return _tableMapper.getRightPrimaryKeys(rightPrimaryKey);
	}

	@Override
	public TableMapper<R, L> getReverseTableMapper() {
		return _tableMapper;
	}

	@Override
	public List<R> getRightBaseModels(
			long leftPrimaryKey, int start, int end, OrderByComparator obc)
		throws SystemException {

		return _tableMapper.getLeftBaseModels(leftPrimaryKey, start, end, obc);
	}

	@Override
	public long[] getRightPrimaryKeys(long leftPrimaryKey)
		throws SystemException {

		return _tableMapper.getLeftPrimaryKeys(leftPrimaryKey);
	}

	@Override
	public boolean matches(String leftColumnName, String rightColumnName) {
		return _tableMapper.matches(rightColumnName, leftColumnName);
	}

	private TableMapper<R, L> _tableMapper;

}