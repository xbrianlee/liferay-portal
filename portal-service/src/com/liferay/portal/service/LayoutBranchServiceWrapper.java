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
 * Provides a wrapper for {@link LayoutBranchService}.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutBranchService
 * @generated
 */
@ProviderType
public class LayoutBranchServiceWrapper implements LayoutBranchService,
	ServiceWrapper<LayoutBranchService> {
	public LayoutBranchServiceWrapper(LayoutBranchService layoutBranchService) {
		_layoutBranchService = layoutBranchService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _layoutBranchService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_layoutBranchService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.portal.model.LayoutBranch addLayoutBranch(
		long layoutRevisionId, java.lang.String name,
		java.lang.String description, boolean master,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutBranchService.addLayoutBranch(layoutRevisionId, name,
			description, master, serviceContext);
	}

	@Override
	public void deleteLayoutBranch(long layoutBranchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutBranchService.deleteLayoutBranch(layoutBranchId);
	}

	@Override
	public com.liferay.portal.model.LayoutBranch updateLayoutBranch(
		long layoutBranchId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutBranchService.updateLayoutBranch(layoutBranchId, name,
			description, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public LayoutBranchService getWrappedLayoutBranchService() {
		return _layoutBranchService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedLayoutBranchService(
		LayoutBranchService layoutBranchService) {
		_layoutBranchService = layoutBranchService;
	}

	@Override
	public LayoutBranchService getWrappedService() {
		return _layoutBranchService;
	}

	@Override
	public void setWrappedService(LayoutBranchService layoutBranchService) {
		_layoutBranchService = layoutBranchService;
	}

	private LayoutBranchService _layoutBranchService;
}