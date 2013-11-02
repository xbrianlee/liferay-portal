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
 * Provides a wrapper for {@link PermissionService}.
 *
 * @author Brian Wing Shun Chan
 * @see PermissionService
 * @generated
 */
@ProviderType
public class PermissionServiceWrapper implements PermissionService,
	ServiceWrapper<PermissionService> {
	public PermissionServiceWrapper(PermissionService permissionService) {
		_permissionService = permissionService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _permissionService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_permissionService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Checks to see if the group has permission to the service.
	*
	* @param groupId the primary key of the group
	* @param name the service name
	* @param primKey the primary key of the service
	* @throws PortalException if the group did not have permission to the
	service, if a group with the primary key could not be found or if
	the permission information was invalid
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void checkPermission(long groupId, java.lang.String name,
		long primKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_permissionService.checkPermission(groupId, name, primKey);
	}

	/**
	* Checks to see if the group has permission to the service.
	*
	* @param groupId the primary key of the group
	* @param name the service name
	* @param primKey the primary key of the service
	* @throws PortalException if the group did not have permission to the
	service, if a group with the primary key could not be found or if
	the permission information was invalid
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public void checkPermission(long groupId, java.lang.String name,
		java.lang.String primKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_permissionService.checkPermission(groupId, name, primKey);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public PermissionService getWrappedPermissionService() {
		return _permissionService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedPermissionService(PermissionService permissionService) {
		_permissionService = permissionService;
	}

	@Override
	public PermissionService getWrappedService() {
		return _permissionService;
	}

	@Override
	public void setWrappedService(PermissionService permissionService) {
		_permissionService = permissionService;
	}

	private PermissionService _permissionService;
}