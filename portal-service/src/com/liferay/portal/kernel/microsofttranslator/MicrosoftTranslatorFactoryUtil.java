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

package com.liferay.portal.kernel.microsofttranslator;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Hugo Huijser
 */
public class MicrosoftTranslatorFactoryUtil {

	public static MicrosoftTranslator getMicrosoftTranslator() {
		return getMicrosoftTranslatorFactory().getMicrosoftTranslator();
	}

	public static MicrosoftTranslator getMicrosoftTranslator(
		String clientId, String clientSecret) {

		return getMicrosoftTranslatorFactory().getMicrosoftTranslator(
			clientId, clientSecret);
	}

	public static MicrosoftTranslatorFactory getMicrosoftTranslatorFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			MicrosoftTranslatorFactoryUtil.class);

		return _microsoftTranslatorFactory;
	}

	public void setMicrosoftTranslatorFactory(
		MicrosoftTranslatorFactory microsoftTranslatorFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_microsoftTranslatorFactory = microsoftTranslatorFactory;
	}

	private static MicrosoftTranslatorFactory _microsoftTranslatorFactory;

}