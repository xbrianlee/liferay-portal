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
 * Provides a wrapper for {@link SocialActivityInterpreterLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityInterpreterLocalService
 * @generated
 */
@ProviderType
public class SocialActivityInterpreterLocalServiceWrapper
	implements SocialActivityInterpreterLocalService,
		ServiceWrapper<SocialActivityInterpreterLocalService> {
	public SocialActivityInterpreterLocalServiceWrapper(
		SocialActivityInterpreterLocalService socialActivityInterpreterLocalService) {
		_socialActivityInterpreterLocalService = socialActivityInterpreterLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _socialActivityInterpreterLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_socialActivityInterpreterLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Adds the activity interpreter to the list of available interpreters.
	*
	* @param activityInterpreter the activity interpreter
	*/
	@Override
	public void addActivityInterpreter(
		com.liferay.portlet.social.model.SocialActivityInterpreter activityInterpreter) {
		_socialActivityInterpreterLocalService.addActivityInterpreter(activityInterpreter);
	}

	/**
	* Removes the activity interpreter from the list of available interpreters.
	*
	* @param activityInterpreter the activity interpreter
	*/
	@Override
	public void deleteActivityInterpreter(
		com.liferay.portlet.social.model.SocialActivityInterpreter activityInterpreter) {
		_socialActivityInterpreterLocalService.deleteActivityInterpreter(activityInterpreter);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<com.liferay.portlet.social.model.SocialActivityInterpreter>> getActivityInterpreters() {
		return _socialActivityInterpreterLocalService.getActivityInterpreters();
	}

	@Override
	public java.util.List<com.liferay.portlet.social.model.SocialActivityInterpreter> getActivityInterpreters(
		java.lang.String selector) {
		return _socialActivityInterpreterLocalService.getActivityInterpreters(selector);
	}

	/**
	* @deprecated As of 6.2.0, replaced by {@link #interpret(String,
	SocialActivity, ServiceContext)}
	*/
	@Override
	public com.liferay.portlet.social.model.SocialActivityFeedEntry interpret(
		com.liferay.portlet.social.model.SocialActivity activity,
		com.liferay.portal.theme.ThemeDisplay themeDisplay) {
		return _socialActivityInterpreterLocalService.interpret(activity,
			themeDisplay);
	}

	/**
	* Creates a human readable activity feed entry for the activity using an
	* available compatible activity interpreter.
	*
	* <p>
	* This method finds the appropriate interpreter for the activity by going
	* through the available interpreters and asking them if they can handle the
	* asset type of the activity.
	* </p>
	*
	* @param activity the activity to be translated to human readable form
	* @return the activity feed that is a human readable form of the activity
	record or <code>null</code> if a compatible interpreter is not
	found
	*/
	@Override
	public com.liferay.portlet.social.model.SocialActivityFeedEntry interpret(
		java.lang.String selector,
		com.liferay.portlet.social.model.SocialActivity activity,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _socialActivityInterpreterLocalService.interpret(selector,
			activity, serviceContext);
	}

	@Override
	public com.liferay.portlet.social.model.SocialActivityFeedEntry interpret(
		java.lang.String selector,
		com.liferay.portlet.social.model.SocialActivitySet activitySet,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _socialActivityInterpreterLocalService.interpret(selector,
			activitySet, serviceContext);
	}

	@Override
	public void updateActivitySet(long activityId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_socialActivityInterpreterLocalService.updateActivitySet(activityId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SocialActivityInterpreterLocalService getWrappedSocialActivityInterpreterLocalService() {
		return _socialActivityInterpreterLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSocialActivityInterpreterLocalService(
		SocialActivityInterpreterLocalService socialActivityInterpreterLocalService) {
		_socialActivityInterpreterLocalService = socialActivityInterpreterLocalService;
	}

	@Override
	public SocialActivityInterpreterLocalService getWrappedService() {
		return _socialActivityInterpreterLocalService;
	}

	@Override
	public void setWrappedService(
		SocialActivityInterpreterLocalService socialActivityInterpreterLocalService) {
		_socialActivityInterpreterLocalService = socialActivityInterpreterLocalService;
	}

	private SocialActivityInterpreterLocalService _socialActivityInterpreterLocalService;
}