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

package com.liferay.portal.microsofttranslator;

import com.liferay.portal.kernel.microsofttranslator.MicrosoftTranslator;
import com.liferay.portal.kernel.microsofttranslator.MicrosoftTranslatorFactory;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;

/**
 * @author Hugo Huijser
 */
@DoPrivileged
public class MicrosoftTranslatorFactoryImpl
	implements MicrosoftTranslatorFactory {

	@Override
	public MicrosoftTranslator getMicrosoftTranslator() {
		if (_microsoftTranslator == null) {
			_microsoftTranslator = new MicrosoftTranslatorImpl();
		}

		return _microsoftTranslator;
	}

	@Override
	public MicrosoftTranslator getMicrosoftTranslator(
		String clientId, String clientSecret) {

		return new MicrosoftTranslatorImpl(clientId, clientSecret);
	}

	private MicrosoftTranslator _microsoftTranslator;

}