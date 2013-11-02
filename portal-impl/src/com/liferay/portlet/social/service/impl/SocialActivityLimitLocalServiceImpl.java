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

package com.liferay.portlet.social.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.model.User;
import com.liferay.portlet.social.model.SocialActivityLimit;
import com.liferay.portlet.social.service.base.SocialActivityLimitLocalServiceBaseImpl;

/**
 * @author Zsolt Berentey
 */
public class SocialActivityLimitLocalServiceImpl
	extends SocialActivityLimitLocalServiceBaseImpl {

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public SocialActivityLimit addActivityLimit(
			long userId, long groupId, long classNameId, long classPK,
			int activityType, String activityCounterName, int limitPeriod)
		throws PortalException, SystemException {

		SocialActivityLimit activityLimit =
			socialActivityLimitPersistence.fetchByG_U_C_C_A_A(
				groupId, userId, classNameId, classPK, activityType,
				activityCounterName, false);

		if (activityLimit != null) {
			return activityLimit;
		}

		User user = userPersistence.findByPrimaryKey(userId);

		long activityLimitId = counterLocalService.increment();

		activityLimit = socialActivityLimitPersistence.create(activityLimitId);

		activityLimit.setGroupId(groupId);
		activityLimit.setCompanyId(user.getCompanyId());
		activityLimit.setUserId(userId);
		activityLimit.setClassNameId(classNameId);
		activityLimit.setClassPK(classPK);
		activityLimit.setActivityType(activityType);
		activityLimit.setActivityCounterName(activityCounterName);
		activityLimit.setCount(limitPeriod, 0);

		socialActivityLimitPersistence.update(activityLimit);

		return activityLimit;
	}

	@Override
	public SocialActivityLimit fetchActivityLimit(
			long groupId, long userId, long classNameId, long classPK,
			int activityType, String activityCounterName)
		throws SystemException {

		return socialActivityLimitPersistence.fetchByG_U_C_C_A_A(
			groupId, userId, classNameId, classPK, activityType,
			activityCounterName);
	}

}