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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for SocialRequestInterpreter. This utility wraps
 * {@link com.liferay.portlet.social.service.impl.SocialRequestInterpreterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SocialRequestInterpreterLocalService
 * @see com.liferay.portlet.social.service.base.SocialRequestInterpreterLocalServiceBaseImpl
 * @see com.liferay.portlet.social.service.impl.SocialRequestInterpreterLocalServiceImpl
 * @generated
 */
@ProviderType
public class SocialRequestInterpreterLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.social.service.impl.SocialRequestInterpreterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	/**
	* Adds the social request interpreter to the list of available
	* interpreters.
	*
	* @param requestInterpreter the social request interpreter
	*/
	public static void addRequestInterpreter(
		com.liferay.portlet.social.model.SocialRequestInterpreter requestInterpreter) {
		getService().addRequestInterpreter(requestInterpreter);
	}

	/**
	* Removes the social request interpreter from the list of available
	* interpreters.
	*
	* @param requestInterpreter the social request interpreter
	*/
	public static void deleteRequestInterpreter(
		com.liferay.portlet.social.model.SocialRequestInterpreter requestInterpreter) {
		getService().deleteRequestInterpreter(requestInterpreter);
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
	public static com.liferay.portlet.social.model.SocialRequestFeedEntry interpret(
		com.liferay.portlet.social.model.SocialRequest request,
		com.liferay.portal.theme.ThemeDisplay themeDisplay) {
		return getService().interpret(request, themeDisplay);
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
	public static void processConfirmation(
		com.liferay.portlet.social.model.SocialRequest request,
		com.liferay.portal.theme.ThemeDisplay themeDisplay) {
		getService().processConfirmation(request, themeDisplay);
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
	public static void processRejection(
		com.liferay.portlet.social.model.SocialRequest request,
		com.liferay.portal.theme.ThemeDisplay themeDisplay) {
		getService().processRejection(request, themeDisplay);
	}

	public static SocialRequestInterpreterLocalService getService() {
		if (_service == null) {
			_service = (SocialRequestInterpreterLocalService)PortalBeanLocatorUtil.locate(SocialRequestInterpreterLocalService.class.getName());

			ReferenceRegistry.registerReference(SocialRequestInterpreterLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SocialRequestInterpreterLocalService service) {
	}

	private static SocialRequestInterpreterLocalService _service;
}