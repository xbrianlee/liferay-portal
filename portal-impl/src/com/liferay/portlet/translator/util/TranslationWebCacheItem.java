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

package com.liferay.portlet.translator.util;

import com.liferay.portal.kernel.microsofttranslator.MicrosoftTranslator;
import com.liferay.portal.kernel.microsofttranslator.MicrosoftTranslatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portlet.translator.model.Translation;

/**
 * @author Brian Wing Shun Chan
 * @author Hugo Huijser
 */
public class TranslationWebCacheItem implements WebCacheItem {

	public TranslationWebCacheItem(
		String fromLanguageId, String toLanguageId, String fromText) {

		_fromLanguageId = fromLanguageId;
		_toLanguageId = toLanguageId;
		_fromText = fromText;
	}

	@Override
	public Object convert(String key) throws WebCacheException {
		Translation translation = new Translation(
			_fromLanguageId, _toLanguageId, _fromText);

		try {
			MicrosoftTranslator microsoftTranslator =
				MicrosoftTranslatorFactoryUtil.getMicrosoftTranslator();

			String toText = microsoftTranslator.translate(
				_fromLanguageId, _toLanguageId, _fromText);

			translation.setToText(toText);
		}
		catch (Exception e) {
			throw new WebCacheException(e);
		}

		return translation;
	}

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.DAY * 90;

	private String _fromLanguageId;
	private String _fromText;
	private String _toLanguageId;

}