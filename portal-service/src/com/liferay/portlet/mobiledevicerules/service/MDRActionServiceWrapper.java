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

package com.liferay.portlet.mobiledevicerules.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MDRActionService}.
 *
 * @author Edward C. Han
 * @see MDRActionService
 * @generated
 */
@ProviderType
public class MDRActionServiceWrapper implements MDRActionService,
	ServiceWrapper<MDRActionService> {
	public MDRActionServiceWrapper(MDRActionService mdrActionService) {
		_mdrActionService = mdrActionService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _mdrActionService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_mdrActionService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.portlet.mobiledevicerules.model.MDRAction addAction(
		long ruleGroupInstanceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type, java.lang.String typeSettings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mdrActionService.addAction(ruleGroupInstanceId, nameMap,
			descriptionMap, type, typeSettings, serviceContext);
	}

	@Override
	public com.liferay.portlet.mobiledevicerules.model.MDRAction addAction(
		long ruleGroupInstanceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type,
		com.liferay.portal.kernel.util.UnicodeProperties typeSettingsProperties,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mdrActionService.addAction(ruleGroupInstanceId, nameMap,
			descriptionMap, type, typeSettingsProperties, serviceContext);
	}

	@Override
	public void deleteAction(long actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_mdrActionService.deleteAction(actionId);
	}

	@Override
	public com.liferay.portlet.mobiledevicerules.model.MDRAction fetchAction(
		long actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mdrActionService.fetchAction(actionId);
	}

	@Override
	public com.liferay.portlet.mobiledevicerules.model.MDRAction getAction(
		long actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mdrActionService.getAction(actionId);
	}

	@Override
	public com.liferay.portlet.mobiledevicerules.model.MDRAction updateAction(
		long actionId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type, java.lang.String typeSettings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mdrActionService.updateAction(actionId, nameMap,
			descriptionMap, type, typeSettings, serviceContext);
	}

	@Override
	public com.liferay.portlet.mobiledevicerules.model.MDRAction updateAction(
		long actionId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type,
		com.liferay.portal.kernel.util.UnicodeProperties typeSettingsProperties,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mdrActionService.updateAction(actionId, nameMap,
			descriptionMap, type, typeSettingsProperties, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public MDRActionService getWrappedMDRActionService() {
		return _mdrActionService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedMDRActionService(MDRActionService mdrActionService) {
		_mdrActionService = mdrActionService;
	}

	@Override
	public MDRActionService getWrappedService() {
		return _mdrActionService;
	}

	@Override
	public void setWrappedService(MDRActionService mdrActionService) {
		_mdrActionService = mdrActionService;
	}

	private MDRActionService _mdrActionService;
}