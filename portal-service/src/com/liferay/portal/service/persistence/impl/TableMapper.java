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
public interface TableMapper<L extends BaseModel<L>, R extends BaseModel<R>> {

	public boolean addTableMapping(long leftPrimaryKey, long rightPrimaryKey)
		throws SystemException;

	public boolean containsTableMapping(
			long leftPrimaryKey, long rightPrimaryKey)
		throws SystemException;

	public int deleteLeftPrimaryKeyTableMappings(long leftPrimaryKey)
		throws SystemException;

	public int deleteRightPrimaryKeyTableMappings(long rightPrimaryKey)
		throws SystemException;

	public boolean deleteTableMapping(long leftPrimaryKey, long rightPrimaryKey)
		throws SystemException;

	public List<L> getLeftBaseModels(
			long rightPrimaryKey, int start, int end, OrderByComparator obc)
		throws SystemException;

	public long[] getLeftPrimaryKeys(long rightPrimaryKey)
		throws SystemException;

	public TableMapper<R, L> getReverseTableMapper();

	public List<R> getRightBaseModels(
			long leftPrimaryKey, int start, int end, OrderByComparator obc)
		throws SystemException;

	public long[] getRightPrimaryKeys(long leftPrimaryKey)
		throws SystemException;

	public boolean matches(String leftColumnName, String rightColumnName);

}