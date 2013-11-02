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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

/**
 * Provides a wrapper for {@link WebsiteService}.
 *
 * @author Brian Wing Shun Chan
 * @see WebsiteService
 * @generated
 */
@ProviderType
public class WebsiteServiceWrapper implements WebsiteService,
	ServiceWrapper<WebsiteService> {
	public WebsiteServiceWrapper(WebsiteService websiteService) {
		_websiteService = websiteService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _websiteService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_websiteService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* @deprecated As of 6.2.0, replaced by {@link #addWebsite( String, long,
	String, int, boolean, ServiceContext)}
	*/
	@Override
	public com.liferay.portal.model.Website addWebsite(
		java.lang.String className, long classPK, java.lang.String url,
		int typeId, boolean primary)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _websiteService.addWebsite(className, classPK, url, typeId,
			primary);
	}

	@Override
	public com.liferay.portal.model.Website addWebsite(
		java.lang.String className, long classPK, java.lang.String url,
		int typeId, boolean primary,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _websiteService.addWebsite(className, classPK, url, typeId,
			primary, serviceContext);
	}

	@Override
	public void deleteWebsite(long websiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_websiteService.deleteWebsite(websiteId);
	}

	@Override
	public com.liferay.portal.model.Website getWebsite(long websiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _websiteService.getWebsite(websiteId);
	}

	@Override
	public java.util.List<com.liferay.portal.model.Website> getWebsites(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _websiteService.getWebsites(className, classPK);
	}

	@Override
	public com.liferay.portal.model.Website updateWebsite(long websiteId,
		java.lang.String url, int typeId, boolean primary)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _websiteService.updateWebsite(websiteId, url, typeId, primary);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public WebsiteService getWrappedWebsiteService() {
		return _websiteService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedWebsiteService(WebsiteService websiteService) {
		_websiteService = websiteService;
	}

	@Override
	public WebsiteService getWrappedService() {
		return _websiteService;
	}

	@Override
	public void setWrappedService(WebsiteService websiteService) {
		_websiteService = websiteService;
	}

	private WebsiteService _websiteService;
}