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

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public interface SocialActivitySetFinder {
	public int countByRelation(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByRelationType(long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByUser(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByUserGroups(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByRelation(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByRelationType(
		long userId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByUser(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.social.model.SocialActivitySet> findByUserGroups(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}