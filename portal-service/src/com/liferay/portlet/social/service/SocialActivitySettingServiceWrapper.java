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

package com.liferay.portlet.social.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SocialActivitySettingService}.
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivitySettingService
 * @generated
 */
@ProviderType
public class SocialActivitySettingServiceWrapper
	implements SocialActivitySettingService,
		ServiceWrapper<SocialActivitySettingService> {
	public SocialActivitySettingServiceWrapper(
		SocialActivitySettingService socialActivitySettingService) {
		_socialActivitySettingService = socialActivitySettingService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _socialActivitySettingService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_socialActivitySettingService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.portlet.social.model.SocialActivityDefinition getActivityDefinition(
		long groupId, java.lang.String className, int activityType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySettingService.getActivityDefinition(groupId,
			className, activityType);
	}

	@Override
	public java.util.List<com.liferay.portlet.social.model.SocialActivityDefinition> getActivityDefinitions(
		long groupId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySettingService.getActivityDefinitions(groupId,
			className);
	}

	@Override
	public java.util.List<com.liferay.portlet.social.model.SocialActivitySetting> getActivitySettings(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySettingService.getActivitySettings(groupId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getJSONActivityDefinitions(
		long groupId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySettingService.getJSONActivityDefinitions(groupId,
			className);
	}

	@Override
	public void updateActivitySetting(long groupId, java.lang.String className,
		boolean enabled)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_socialActivitySettingService.updateActivitySetting(groupId, className,
			enabled);
	}

	@Override
	public void updateActivitySetting(long groupId, java.lang.String className,
		int activityType,
		com.liferay.portlet.social.model.SocialActivityCounterDefinition activityCounterDefinition)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_socialActivitySettingService.updateActivitySetting(groupId, className,
			activityType, activityCounterDefinition);
	}

	@Override
	public void updateActivitySettings(long groupId,
		java.lang.String className, int activityType,
		java.util.List<com.liferay.portlet.social.model.SocialActivityCounterDefinition> activityCounterDefinitions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_socialActivitySettingService.updateActivitySettings(groupId,
			className, activityType, activityCounterDefinitions);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SocialActivitySettingService getWrappedSocialActivitySettingService() {
		return _socialActivitySettingService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSocialActivitySettingService(
		SocialActivitySettingService socialActivitySettingService) {
		_socialActivitySettingService = socialActivitySettingService;
	}

	@Override
	public SocialActivitySettingService getWrappedService() {
		return _socialActivitySettingService;
	}

	@Override
	public void setWrappedService(
		SocialActivitySettingService socialActivitySettingService) {
		_socialActivitySettingService = socialActivitySettingService;
	}

	private SocialActivitySettingService _socialActivitySettingService;
}