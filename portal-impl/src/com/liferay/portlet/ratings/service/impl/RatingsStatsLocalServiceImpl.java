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

package com.liferay.portlet.ratings.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.ratings.NoSuchStatsException;
import com.liferay.portlet.ratings.model.RatingsStats;
import com.liferay.portlet.ratings.service.base.RatingsStatsLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class RatingsStatsLocalServiceImpl
	extends RatingsStatsLocalServiceBaseImpl {

	@Override
	public RatingsStats addStats(long classNameId, long classPK)
		throws SystemException {

		long statsId = counterLocalService.increment();

		RatingsStats stats = ratingsStatsPersistence.create(statsId);

		stats.setClassNameId(classNameId);
		stats.setClassPK(classPK);
		stats.setTotalEntries(0);
		stats.setTotalScore(0.0);
		stats.setAverageScore(0.0);

		try {
			ratingsStatsPersistence.update(stats);
		}
		catch (SystemException se) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Add failed, fetch {classNameId=" + classNameId +
						", classPK=" + classPK + "}");
			}

			stats = ratingsStatsPersistence.fetchByC_C(
				classNameId, classPK, false);

			if (stats == null) {
				throw se;
			}
		}

		return stats;
	}

	@Override
	public void deleteStats(String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		try {
			ratingsStatsPersistence.removeByC_C(classNameId, classPK);
		}
		catch (NoSuchStatsException nsse) {
			if (_log.isWarnEnabled()) {
				_log.warn(nsse);
			}
		}

		ratingsEntryPersistence.removeByC_C(classNameId, classPK);
	}

	@Override
	public RatingsStats getStats(long statsId)
		throws PortalException, SystemException {

		return ratingsStatsPersistence.findByPrimaryKey(statsId);
	}

	@Override
	public List<RatingsStats> getStats(String className, List<Long> classPKs)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return ratingsStatsFinder.findByC_C(classNameId, classPKs);
	}

	@Override
	public RatingsStats getStats(String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		RatingsStats stats = ratingsStatsPersistence.fetchByC_C(
			classNameId, classPK);

		if (stats == null) {
			stats = ratingsStatsLocalService.addStats(classNameId, classPK);
		}

		return stats;
	}

	private static Log _log = LogFactoryUtil.getLog(
		RatingsStatsLocalServiceImpl.class);

}