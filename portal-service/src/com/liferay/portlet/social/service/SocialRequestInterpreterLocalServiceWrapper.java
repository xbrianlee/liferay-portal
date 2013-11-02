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
 * Provides a wrapper for {@link SocialRequestInterpreterLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SocialRequestInterpreterLocalService
 * @generated
 */
@ProviderType
public class SocialRequestInterpreterLocalServiceWrapper
	implements SocialRequestInterpreterLocalService,
		ServiceWrapper<SocialRequestInterpreterLocalService> {
	public SocialRequestInterpreterLocalServiceWrapper(
		SocialRequestInterpreterLocalService socialRequestInterpreterLocalService) {
		_socialRequestInterpreterLocalService = socialRequestInterpreterLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _socialRequestInterpreterLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_socialRequestInterpreterLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Adds the social request interpreter to the list of available
	* interpreters.
	*
	* @param requestInterpreter the social request interpreter
	*/
	@Override
	public void addRequestInterpreter(
		com.liferay.portlet.social.model.SocialRequestInterpreter requestInterpreter) {
		_socialRequestInterpreterLocalService.addRequestInterpreter(requestInterpreter);
	}

	/**
	* Removes the social request interpreter from the list of available
	* interpreters.
	*
	* @param requestInterpreter the social request interpreter
	*/
	@Override
	public void deleteRequestInterpreter(
		com.liferay.portlet.social.model.SocialRequestInterpreter requestInterpreter) {
		_socialRequestInterpreterLocalService.deleteRequestInterpreter(requestInterpreter);
	}

	/**
	* Creates a human readable request feed entry for the social request using
	* an available compatible request interpreter.
	*
	* <p>
	* This method finds the appropriate interpreter for the request by going
	* through the available interpreters to find one that can handle the asset
	* type of the request.
	* </p>
	*
	* @param request the social request to be translated to human readable
	form
	* @param themeDisplay the theme display needed by interpreters to create
	links and get localized text fragments
	* @return the social request feed entry
	*/
	@Override
	public com.liferay.portlet.social.model.SocialRequestFeedEntry interpret(
		com.liferay.portlet.social.model.SocialRequest request,
		com.liferay.portal.theme.ThemeDisplay themeDisplay) {
		return _socialRequestInterpreterLocalService.interpret(request,
			themeDisplay);
	}

	/**
	* Processes the confirmation of the social request.
	*
	* <p>
	* Confirmations are handled by finding the appropriate social request
	* interpreter and calling its processConfirmation() method. To find the
	* appropriate interpreter this method goes through the available
	* interpreters to find one that can handle the asset type of the request.
	* </p>
	*
	* @param request the social request being confirmed
	* @param themeDisplay the theme display needed by interpreters to create
	links and get localized text fragments
	*/
	@Override
	public void processConfirmation(
		com.liferay.portlet.social.model.SocialRequest request,
		com.liferay.portal.theme.ThemeDisplay themeDisplay) {
		_socialRequestInterpreterLocalService.processConfirmation(request,
			themeDisplay);
	}

	/**
	* Processes the rejection of the social request.
	*
	* <p>
	* Rejections are handled by finding the appropriate social request
	* interpreters and calling their processRejection() methods. To find the
	* appropriate interpreters this method goes through the available
	* interpreters and asks them if they can handle the asset type of the
	* request.
	* </p>
	*
	* @param request the social request being rejected
	* @param themeDisplay the theme display needed by interpreters to create
	links and get localized text fragments
	*/
	@Override
	public void processRejection(
		com.liferay.portlet.social.model.SocialRequest request,
		com.liferay.portal.theme.ThemeDisplay themeDisplay) {
		_socialRequestInterpreterLocalService.processRejection(request,
			themeDisplay);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SocialRequestInterpreterLocalService getWrappedSocialRequestInterpreterLocalService() {
		return _socialRequestInterpreterLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSocialRequestInterpreterLocalService(
		SocialRequestInterpreterLocalService socialRequestInterpreterLocalService) {
		_socialRequestInterpreterLocalService = socialRequestInterpreterLocalService;
	}

	@Override
	public SocialRequestInterpreterLocalService getWrappedService() {
		return _socialRequestInterpreterLocalService;
	}

	@Override
	public void setWrappedService(
		SocialRequestInterpreterLocalService socialRequestInterpreterLocalService) {
		_socialRequestInterpreterLocalService = socialRequestInterpreterLocalService;
	}

	private SocialRequestInterpreterLocalService _socialRequestInterpreterLocalService;
}