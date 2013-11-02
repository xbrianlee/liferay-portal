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

package com.liferay.portal.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.InMemoryConsumerAssociationStore;
import org.openid4java.consumer.InMemoryNonceVerifier;

/**
 * @author Jorge Ferrer
 */
public class OpenIdUtil {

	public static ConsumerManager getConsumerManager() {
		_instance._initialize();

		return _instance._manager;
	}

	public static String getScreenName(String openId) {
		String result = normalize(openId);

		if (result.startsWith(Http.HTTP_WITH_SLASH)) {
			result = result.substring(Http.HTTP_WITH_SLASH.length());
		}

		if (result.startsWith(Http.HTTPS_WITH_SLASH)) {
			result = result.substring(Http.HTTPS_WITH_SLASH.length());
		}

		result = StringUtil.replace(
			result, new String[] {StringPool.SLASH, StringPool.UNDERLINE},
			new String[] {StringPool.PERIOD, StringPool.PERIOD});

		return result;
	}

	public static boolean isEnabled(long companyId) throws SystemException {
		return PrefsPropsUtil.getBoolean(
			companyId, PropsKeys.OPEN_ID_AUTH_ENABLED,
			PropsValues.OPEN_ID_AUTH_ENABLED);
	}

	public static String normalize(String identity) {
		String result = identity;

		if (result.endsWith(StringPool.SLASH)) {
			result = result.substring(0, result.length() - 1);
		}

		return result;
	}

	private void _initialize() {
		try {
			if (_manager == null) {
				_manager = new ConsumerManager();

				_manager.setAssociations(
					new InMemoryConsumerAssociationStore());
				_manager.setNonceVerifier(new InMemoryNonceVerifier(5000));
			}
		}
		catch (ConsumerException ce) {
			_log.error(ce.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(OpenIdUtil.class);

	private static OpenIdUtil _instance = new OpenIdUtil();

	private ConsumerManager _manager;

}