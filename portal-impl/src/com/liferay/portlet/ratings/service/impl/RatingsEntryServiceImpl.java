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
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.ratings.model.RatingsEntry;
import com.liferay.portlet.ratings.service.base.RatingsEntryServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class RatingsEntryServiceImpl extends RatingsEntryServiceBaseImpl {

	@Override
	public void deleteEntry(String className, long classPK)
		throws PortalException, SystemException {

		ratingsEntryLocalService.deleteEntry(getUserId(), className, classPK);
	}

	@Override
	public RatingsEntry updateEntry(
			String className, long classPK, double score)
		throws PortalException, SystemException {

		return ratingsEntryLocalService.updateEntry(
			getUserId(), className, classPK, score, new ServiceContext());
	}

}