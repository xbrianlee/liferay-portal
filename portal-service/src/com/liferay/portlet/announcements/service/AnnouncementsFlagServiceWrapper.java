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

package com.liferay.portlet.announcements.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AnnouncementsFlagService}.
 *
 * @author Brian Wing Shun Chan
 * @see AnnouncementsFlagService
 * @generated
 */
@ProviderType
public class AnnouncementsFlagServiceWrapper implements AnnouncementsFlagService,
	ServiceWrapper<AnnouncementsFlagService> {
	public AnnouncementsFlagServiceWrapper(
		AnnouncementsFlagService announcementsFlagService) {
		_announcementsFlagService = announcementsFlagService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _announcementsFlagService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_announcementsFlagService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public void addFlag(long entryId, int value)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_announcementsFlagService.addFlag(entryId, value);
	}

	@Override
	public void deleteFlag(long flagId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_announcementsFlagService.deleteFlag(flagId);
	}

	@Override
	public com.liferay.portlet.announcements.model.AnnouncementsFlag getFlag(
		long entryId, int value)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _announcementsFlagService.getFlag(entryId, value);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public AnnouncementsFlagService getWrappedAnnouncementsFlagService() {
		return _announcementsFlagService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedAnnouncementsFlagService(
		AnnouncementsFlagService announcementsFlagService) {
		_announcementsFlagService = announcementsFlagService;
	}

	@Override
	public AnnouncementsFlagService getWrappedService() {
		return _announcementsFlagService;
	}

	@Override
	public void setWrappedService(
		AnnouncementsFlagService announcementsFlagService) {
		_announcementsFlagService = announcementsFlagService;
	}

	private AnnouncementsFlagService _announcementsFlagService;
}