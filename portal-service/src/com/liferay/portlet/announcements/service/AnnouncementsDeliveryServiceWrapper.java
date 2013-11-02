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
 * Provides a wrapper for {@link AnnouncementsDeliveryService}.
 *
 * @author Brian Wing Shun Chan
 * @see AnnouncementsDeliveryService
 * @generated
 */
@ProviderType
public class AnnouncementsDeliveryServiceWrapper
	implements AnnouncementsDeliveryService,
		ServiceWrapper<AnnouncementsDeliveryService> {
	public AnnouncementsDeliveryServiceWrapper(
		AnnouncementsDeliveryService announcementsDeliveryService) {
		_announcementsDeliveryService = announcementsDeliveryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _announcementsDeliveryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_announcementsDeliveryService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.portlet.announcements.model.AnnouncementsDelivery updateDelivery(
		long userId, java.lang.String type, boolean email, boolean sms,
		boolean website)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _announcementsDeliveryService.updateDelivery(userId, type,
			email, sms, website);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public AnnouncementsDeliveryService getWrappedAnnouncementsDeliveryService() {
		return _announcementsDeliveryService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedAnnouncementsDeliveryService(
		AnnouncementsDeliveryService announcementsDeliveryService) {
		_announcementsDeliveryService = announcementsDeliveryService;
	}

	@Override
	public AnnouncementsDeliveryService getWrappedService() {
		return _announcementsDeliveryService;
	}

	@Override
	public void setWrappedService(
		AnnouncementsDeliveryService announcementsDeliveryService) {
		_announcementsDeliveryService = announcementsDeliveryService;
	}

	private AnnouncementsDeliveryService _announcementsDeliveryService;
}