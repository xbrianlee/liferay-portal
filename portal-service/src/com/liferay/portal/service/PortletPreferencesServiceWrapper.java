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
 * Provides a wrapper for {@link PortletPreferencesService}.
 *
 * @author Brian Wing Shun Chan
 * @see PortletPreferencesService
 * @generated
 */
@ProviderType
public class PortletPreferencesServiceWrapper
	implements PortletPreferencesService,
		ServiceWrapper<PortletPreferencesService> {
	public PortletPreferencesServiceWrapper(
		PortletPreferencesService portletPreferencesService) {
		_portletPreferencesService = portletPreferencesService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _portletPreferencesService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_portletPreferencesService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public void deleteArchivedPreferences(long portletItemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_portletPreferencesService.deleteArchivedPreferences(portletItemId);
	}

	@Override
	public void restoreArchivedPreferences(long groupId,
		com.liferay.portal.model.Layout layout, java.lang.String portletId,
		long portletItemId, javax.portlet.PortletPreferences preferences)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_portletPreferencesService.restoreArchivedPreferences(groupId, layout,
			portletId, portletItemId, preferences);
	}

	@Override
	public void restoreArchivedPreferences(long groupId,
		com.liferay.portal.model.Layout layout, java.lang.String portletId,
		com.liferay.portal.model.PortletItem portletItem,
		javax.portlet.PortletPreferences preferences)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_portletPreferencesService.restoreArchivedPreferences(groupId, layout,
			portletId, portletItem, preferences);
	}

	@Override
	public void restoreArchivedPreferences(long groupId, java.lang.String name,
		com.liferay.portal.model.Layout layout, java.lang.String portletId,
		javax.portlet.PortletPreferences preferences)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_portletPreferencesService.restoreArchivedPreferences(groupId, name,
			layout, portletId, preferences);
	}

	@Override
	public void updateArchivePreferences(long userId, long groupId,
		java.lang.String name, java.lang.String portletId,
		javax.portlet.PortletPreferences preferences)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_portletPreferencesService.updateArchivePreferences(userId, groupId,
			name, portletId, preferences);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public PortletPreferencesService getWrappedPortletPreferencesService() {
		return _portletPreferencesService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedPortletPreferencesService(
		PortletPreferencesService portletPreferencesService) {
		_portletPreferencesService = portletPreferencesService;
	}

	@Override
	public PortletPreferencesService getWrappedService() {
		return _portletPreferencesService;
	}

	@Override
	public void setWrappedService(
		PortletPreferencesService portletPreferencesService) {
		_portletPreferencesService = portletPreferencesService;
	}

	private PortletPreferencesService _portletPreferencesService;
}