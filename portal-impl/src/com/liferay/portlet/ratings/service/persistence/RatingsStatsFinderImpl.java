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

package com.liferay.portlet.ratings.service.persistence;

import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.ratings.model.RatingsStats;
import com.liferay.portlet.ratings.model.impl.RatingsStatsImpl;
import com.liferay.portlet.ratings.model.impl.RatingsStatsModelImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class RatingsStatsFinderImpl
	extends BasePersistenceImpl<RatingsStats> implements RatingsStatsFinder {

	public static final String FIND_BY_C_C =
		RatingsStatsFinder.class.getName() + ".findByC_C";

	public static final FinderPath FINDER_PATH_FIND_BY_C_C = new FinderPath(
		RatingsStatsModelImpl.ENTITY_CACHE_ENABLED,
		RatingsStatsModelImpl.FINDER_CACHE_ENABLED, RatingsStatsImpl.class,
		RatingsStatsPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
		"findByC_C",
		new String[] {Long.class.getName(), List.class.getName()});

	@Override
	public List<RatingsStats> findByC_C(long classNameId, List<Long> classPKs)
		throws SystemException {

		Object[] finderArgs = new Object[] {
			classNameId,
			StringUtil.merge(classPKs.toArray(new Long[classPKs.size()]))
		};

		List<RatingsStats> list = (List<RatingsStats>)FinderCacheUtil.getResult(
			FINDER_PATH_FIND_BY_C_C, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (RatingsStats ratingsStats : list) {
				if ((classNameId != ratingsStats.getClassNameId()) ||
					!classPKs.contains(ratingsStats.getClassPK())) {

					list = null;

					break;
				}
			}
		}

		if (list != null) {
			return list;
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_C);

			sql = StringUtil.replace(
				sql, "[$CLASS_PKS$]", StringUtil.merge(classPKs));

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("RatingsStats", RatingsStatsImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(classNameId);

			list = q.list(true);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			if (list == null) {
				FinderCacheUtil.removeResult(
					FINDER_PATH_FIND_BY_C_C, finderArgs);
			}
			else {
				FinderCacheUtil.putResult(
					FINDER_PATH_FIND_BY_C_C, finderArgs, list);
			}

			closeSession(session);
		}

		return list;
	}

}