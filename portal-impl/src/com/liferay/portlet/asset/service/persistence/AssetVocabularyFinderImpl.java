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

package com.liferay.portlet.asset.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.model.impl.AssetVocabularyImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Juan Fernández
 */
public class AssetVocabularyFinderImpl
	extends BasePersistenceImpl<AssetVocabulary>
	implements AssetVocabularyFinder {

	public static final String COUNT_BY_G_N =
		AssetVocabularyFinder.class.getName() + ".countByG_N";

	public static final String FIND_BY_G_N =
		AssetVocabularyFinder.class.getName() + ".findByG_N";

	@Override
	public int countByG_N(long groupId, String name) throws SystemException {
		return doCountByG_N(groupId, name, false);
	}

	@Override
	public int filterCountByG_N(long groupId, String name)
		throws SystemException {

		return doCountByG_N(groupId, name, true);
	}

	@Override
	public List<AssetVocabulary> filterFindByG_N(
			long groupId, String name, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return doFindByG_N(groupId, name, start, end, obc, true);
	}

	@Override
	public List<AssetVocabulary> findByG_N(
			long groupId, String name, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return doFindByG_N(groupId, name, start, end, obc, false);
	}

	protected int doCountByG_N(
			long groupId, String name, boolean inlineSQLHelper)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_G_N);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, AssetVocabulary.class.getName(),
					"AssetVocabulary.vocabularyId", groupId);
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(name);
			qPos.add(name);

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

	protected List<AssetVocabulary> doFindByG_N(
			long groupId, String name, int start, int end,
			OrderByComparator obc, boolean inlineSQLHelper)
		throws SystemException {

		name = StringUtil.toLowerCase(name.trim());

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_G_N);

			sql = CustomSQLUtil.replaceOrderBy(sql, obc);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, AssetVocabulary.class.getName(),
					"AssetVocabulary.vocabularyId", groupId);
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("AssetVocabulary", AssetVocabularyImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(name);
			qPos.add(name);

			return (List<AssetVocabulary>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}