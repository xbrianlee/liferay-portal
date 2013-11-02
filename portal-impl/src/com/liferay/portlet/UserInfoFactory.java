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

package com.liferay.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class UserInfoFactory {

	public static LinkedHashMap<String, String> getUserInfo(
		HttpServletRequest request, Portlet portlet) {

		if (request.getRemoteUser() == null) {
			return null;
		}

		LinkedHashMap<String, String> userInfo =
			new LinkedHashMap<String, String>();

		try {
			User user = PortalUtil.getUser(request);

			userInfo = getUserInfo(user, userInfo, portlet);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return userInfo;
	}

	public static LinkedHashMap<String, String> getUserInfo(
		long userId, Portlet portlet) {

		if (userId <= 0) {
			return null;
		}

		LinkedHashMap<String, String> userInfo =
			new LinkedHashMap<String, String>();

		try {
			User user = UserLocalServiceUtil.getUserById(userId);

			userInfo = getUserInfo(user, userInfo, portlet);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return userInfo;
	}

	public static LinkedHashMap<String, String> getUserInfo(
		User user, LinkedHashMap<String, String> userInfo, Portlet portlet) {

		PortletApp portletApp = portlet.getPortletApp();

		// Liferay user attributes

		try {
			UserAttributes userAttributes = new UserAttributes(user);

			// Mandatory user attributes

			userInfo.put(
				UserAttributes.LIFERAY_COMPANY_ID,
				userAttributes.getValue(UserAttributes.LIFERAY_COMPANY_ID));

			userInfo.put(
				UserAttributes.LIFERAY_USER_ID,
				userAttributes.getValue(UserAttributes.LIFERAY_USER_ID));

			// Portlet user attributes

			for (String attrName : portletApp.getUserAttributes()) {
				String attrValue = userAttributes.getValue(attrName);

				if (attrValue != null) {
					userInfo.put(attrName, attrValue);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		Map<String, String> unmodifiableUserInfo = Collections.unmodifiableMap(
			(Map<String, String>)userInfo.clone());

		// Custom user attributes

		Map<String, CustomUserAttributes> cuaInstances =
			new HashMap<String, CustomUserAttributes>();

		for (Map.Entry<String, String> entry :
				portletApp.getCustomUserAttributes().entrySet()) {

			String attrName = entry.getKey();
			String attrCustomClass = entry.getValue();

			CustomUserAttributes cua = cuaInstances.get(attrCustomClass);

			if (cua == null) {
				if (portletApp.isWARFile()) {
					PortletContextBag portletContextBag =
						PortletContextBagPool.get(
							portletApp.getServletContextName());

					Map<String, CustomUserAttributes> customUserAttributes =
						portletContextBag.getCustomUserAttributes();

					cua = customUserAttributes.get(attrCustomClass);

					cua = (CustomUserAttributes)cua.clone();
				}
				else {
					try {
						cua = (CustomUserAttributes)InstanceFactory.newInstance(
							attrCustomClass);
					}
					catch (Exception e) {
						_log.error(e, e);
					}
				}

				cuaInstances.put(attrCustomClass, cua);
			}

			if (cua != null) {
				String attrValue = cua.getValue(attrName, unmodifiableUserInfo);

				if (attrValue != null) {
					userInfo.put(attrName, attrValue);
				}
			}
		}

		return userInfo;
	}

	private static Log _log = LogFactoryUtil.getLog(UserInfoFactory.class);

}