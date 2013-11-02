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
 * Provides a wrapper for {@link ResourceBlockService}.
 *
 * @author Brian Wing Shun Chan
 * @see ResourceBlockService
 * @generated
 */
@ProviderType
public class ResourceBlockServiceWrapper implements ResourceBlockService,
	ServiceWrapper<ResourceBlockService> {
	public ResourceBlockServiceWrapper(
		ResourceBlockService resourceBlockService) {
		_resourceBlockService = resourceBlockService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _resourceBlockService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_resourceBlockService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public void addCompanyScopePermission(long scopeGroupId, long companyId,
		java.lang.String name, long roleId, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.addCompanyScopePermission(scopeGroupId,
			companyId, name, roleId, actionId);
	}

	@Override
	public void addGroupScopePermission(long scopeGroupId, long companyId,
		long groupId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.addGroupScopePermission(scopeGroupId, companyId,
			groupId, name, roleId, actionId);
	}

	@Override
	public void addIndividualScopePermission(long companyId, long groupId,
		java.lang.String name, long primKey, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.addIndividualScopePermission(companyId, groupId,
			name, primKey, roleId, actionId);
	}

	@Override
	public void removeAllGroupScopePermissions(long scopeGroupId,
		long companyId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.removeAllGroupScopePermissions(scopeGroupId,
			companyId, name, roleId, actionId);
	}

	@Override
	public void removeCompanyScopePermission(long scopeGroupId, long companyId,
		java.lang.String name, long roleId, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.removeCompanyScopePermission(scopeGroupId,
			companyId, name, roleId, actionId);
	}

	@Override
	public void removeGroupScopePermission(long scopeGroupId, long companyId,
		long groupId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.removeGroupScopePermission(scopeGroupId,
			companyId, groupId, name, roleId, actionId);
	}

	@Override
	public void removeIndividualScopePermission(long companyId, long groupId,
		java.lang.String name, long primKey, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.removeIndividualScopePermission(companyId,
			groupId, name, primKey, roleId, actionId);
	}

	@Override
	public void setCompanyScopePermissions(long scopeGroupId, long companyId,
		java.lang.String name, long roleId,
		java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.setCompanyScopePermissions(scopeGroupId,
			companyId, name, roleId, actionIds);
	}

	@Override
	public void setGroupScopePermissions(long scopeGroupId, long companyId,
		long groupId, java.lang.String name, long roleId,
		java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.setGroupScopePermissions(scopeGroupId, companyId,
			groupId, name, roleId, actionIds);
	}

	@Override
	public void setIndividualScopePermissions(long companyId, long groupId,
		java.lang.String name, long primKey, long roleId,
		java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.setIndividualScopePermissions(companyId, groupId,
			name, primKey, roleId, actionIds);
	}

	@Override
	public void setIndividualScopePermissions(long companyId, long groupId,
		java.lang.String name, long primKey,
		java.util.Map<java.lang.Long, java.lang.String[]> roleIdsToActionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_resourceBlockService.setIndividualScopePermissions(companyId, groupId,
			name, primKey, roleIdsToActionIds);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ResourceBlockService getWrappedResourceBlockService() {
		return _resourceBlockService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedResourceBlockService(
		ResourceBlockService resourceBlockService) {
		_resourceBlockService = resourceBlockService;
	}

	@Override
	public ResourceBlockService getWrappedService() {
		return _resourceBlockService;
	}

	@Override
	public void setWrappedService(ResourceBlockService resourceBlockService) {
		_resourceBlockService = resourceBlockService;
	}

	private ResourceBlockService _resourceBlockService;
}