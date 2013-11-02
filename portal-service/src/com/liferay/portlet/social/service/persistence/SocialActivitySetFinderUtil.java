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

package com.liferay.portlet.social.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class SocialActivitySetFinderUtil {
	public static int countByRelation(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByRelation(userId);
	}

	public static int countByRelationType(long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByRelationType(userId, type);
	}

	public static int countByUser(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByUser(userId);
	}

	public static int countByUserGroups(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByUserGroups(userId);
	}

	public static java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByRelation(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByRelation(userId, start, end);
	}

	public static java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByRelationType(
		long userId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByRelationType(userId, type, start, end);
	}

	public static java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByUser(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByUser(userId, start, end);
	}

	public static java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByUserGroups(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByUserGroups(userId, start, end);
	}

	public static SocialActivitySetFinder getFinder() {
		if (_finder == null) {
			_finder = (SocialActivitySetFinder)PortalBeanLocatorUtil.locate(SocialActivitySetFinder.class.getName());

			ReferenceRegistry.registerReference(SocialActivitySetFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SocialActivitySetFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SocialActivitySetFinderUtil.class,
			"_finder");
	}

	private static SocialActivitySetFinder _finder;
}