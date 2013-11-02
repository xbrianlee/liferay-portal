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

package com.liferay.portal.kernel.repository.cmis;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.util.ClassResolverUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.Map;

/**
 * @author Alexander Chow
 */
public class CMISRepositoryUtil {

	public static void checkRepository(
		long repositoryId, Map<String, String> parameters,
		UnicodeProperties typeSettingsProperties, String typeSettingsKey) {

		try {
			PortalClassInvoker.invoke(
				false, _checkRepositoryMethodKey, repositoryId, parameters,
				typeSettingsProperties, typeSettingsKey);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static Session createSession(Map<String, String> parameters)
		throws RepositoryException {

		Session session = null;

		try {
			Object returnObj = PortalClassInvoker.invoke(
				false, _createSessionMethodKey, parameters);

			if (returnObj != null) {
				session = (Session)returnObj;
			}
		}
		catch (RepositoryException re) {
			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return session;
	}

	public static String getTypeSettingsValue(
		UnicodeProperties typeSettingsProperties, String typeSettingsKey) {

		String value = null;

		try {
			Object returnObj = PortalClassInvoker.invoke(
				false, _getTypeSettingsValueMethodKey, typeSettingsProperties,
				typeSettingsKey);

			if (returnObj != null) {
				value = (String)returnObj;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return value;
	}

	private static final String _CLASS_NAME =
		"com.liferay.portal.repository.cmis.CMISRepositoryUtil";

	private static Log _log = LogFactoryUtil.getLog(CMISRepositoryUtil.class);

	private static MethodKey _checkRepositoryMethodKey = new MethodKey(
		ClassResolverUtil.resolveByPortalClassLoader(_CLASS_NAME),
		"checkRepository", long.class, Map.class, UnicodeProperties.class,
		String.class);
	private static MethodKey _createSessionMethodKey = new MethodKey(
		ClassResolverUtil.resolveByPortalClassLoader(_CLASS_NAME),
		"createSession", Map.class);
	private static MethodKey _getTypeSettingsValueMethodKey = new MethodKey(
		ClassResolverUtil.resolveByPortalClassLoader(_CLASS_NAME),
		"getTypeSettingsValue", UnicodeProperties.class, String.class);

}