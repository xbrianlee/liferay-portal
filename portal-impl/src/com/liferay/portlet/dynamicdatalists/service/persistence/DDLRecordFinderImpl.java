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

package com.liferay.portlet.dynamicdatalists.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.model.impl.DDLRecordImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class DDLRecordFinderImpl extends BasePersistenceImpl<DDLRecord>
	implements DDLRecordFinder {

	public static final String COUNT_BY_R_S =
		DDLRecordFinder.class.getName() + ".countByR_S";

	public static final String COUNT_BY_C_S_S =
		DDLRecordFinder.class.getName() + ".countByC_S_S";

	public static final String FIND_BY_R_S =
		DDLRecordFinder.class.getName() + ".findByR_S";

	public static final String FIND_BY_C_S_S =
		DDLRecordFinder.class.getName() + ".findByC_S_S";

	@Override
	public int countByR_S(long recordSetId, int status) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_R_S);

			if (status == WorkflowConstants.STATUS_ANY) {
				sql = StringUtil.replace(
					sql, "(DDLRecordVersion.status = ?) AND", StringPool.BLANK);
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (status != WorkflowConstants.STATUS_ANY) {
				qPos.add(status);
			}

			qPos.add(recordSetId);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public int countByC_S_S(long companyId, int status, int scope)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_S_S);

			if (status == WorkflowConstants.STATUS_ANY) {
				sql = StringUtil.replace(
					sql, "(DDLRecordVersion.status = ?) AND", StringPool.BLANK);
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (status != WorkflowConstants.STATUS_ANY) {
				qPos.add(status);
			}

			qPos.add(scope);
			qPos.add(companyId);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<DDLRecord> findByR_S(
			long recordSetId, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_R_S);

			if (status == WorkflowConstants.STATUS_ANY) {
				sql = StringUtil.replace(
					sql, "(DDLRecordVersion.status = ?) AND", StringPool.BLANK);
			}

			sql = CustomSQLUtil.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("DDLRecord", DDLRecordImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			if (status != WorkflowConstants.STATUS_ANY) {
				qPos.add(status);
			}

			qPos.add(recordSetId);

			return (List<DDLRecord>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<DDLRecord> findByC_S_S(
			long companyId, int status, int scope, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_S_S);

			if (status == WorkflowConstants.STATUS_ANY) {
				sql = StringUtil.replace(
					sql, "(DDLRecordVersion.status = ?) AND", StringPool.BLANK);
			}

			sql = CustomSQLUtil.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("DDLRecord", DDLRecordImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			if (status != WorkflowConstants.STATUS_ANY) {
				qPos.add(status);
			}

			qPos.add(scope);
			qPos.add(companyId);

			return (List<DDLRecord>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public Long[] findByC_S_S_MinAndMax(long companyId, int status, int scope)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_S_S);

			sql = StringUtil.replace(
				sql, "COUNT(DISTINCT DDLRecord.recordId) AS COUNT_VALUE",
				"MIN(DDLRecord.recordId) AS minRecordId, " +
					"MAX(DDLRecord.recordId) AS maxRecordId");

			if (status == WorkflowConstants.STATUS_ANY) {
				sql = StringUtil.replace(
					sql, "(DDLRecordVersion.status = ?) AND", StringPool.BLANK);
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("minRecordId", Type.LONG);
			q.addScalar("maxRecordId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (status != WorkflowConstants.STATUS_ANY) {
				qPos.add(status);
			}

			qPos.add(scope);
			qPos.add(companyId);

			Object[] array = (Object[])q.iterateNext();

			if (array == null) {
				return null;
			}

			return ArrayUtil.toLongArray(array);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<DDLRecord> findByC_S_S_MinAndMax(
			long companyId, int status, int scope, long minRecordId,
			long maxRecordId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_S_S);

			if (status == WorkflowConstants.STATUS_ANY) {
				sql = StringUtil.replace(
					sql, "(DDLRecordVersion.status = ?) AND", StringPool.BLANK);
			}

			sql = CustomSQLUtil.removeOrderBy(sql);

			sql +=
				" AND (DDLRecord.recordId >= ?) AND (DDLRecord.recordId < ?)";

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("DDLRecord", DDLRecordImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			if (status != WorkflowConstants.STATUS_ANY) {
				qPos.add(status);
			}

			qPos.add(scope);
			qPos.add(companyId);
			qPos.add(minRecordId);
			qPos.add(maxRecordId);

			return q.list();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}