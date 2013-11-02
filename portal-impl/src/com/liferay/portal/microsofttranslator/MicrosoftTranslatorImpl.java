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
import com.liferay.portal.kernel.microsofttranslator.MicrosoftTranslatorException;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Hugo Huijser
 */
public class MicrosoftTranslatorImpl implements MicrosoftTranslator {

	public MicrosoftTranslatorImpl() {
		_microsoftTranslatorAuthenticator =
			new MicrosoftTranslatorAuthenticator();
	}

	public MicrosoftTranslatorImpl(String clientId, String clientSecret) {
		_microsoftTranslatorAuthenticator =
			new MicrosoftTranslatorAuthenticator(clientId, clientSecret);
	}

	public MicrosoftTranslatorAuthenticator
		getMicrosoftTranslatorAuthenticator() {

		return _microsoftTranslatorAuthenticator;
	}

	@Override
	public String translate(
			String fromLanguageId, String toLanguageId, String fromText)
		throws MicrosoftTranslatorException {

		try {
			return doTranslate(fromLanguageId, toLanguageId, fromText);
		}
		catch (MicrosoftTranslatorException mte) {
			throw mte;
		}
		catch (Exception e) {
			throw new MicrosoftTranslatorException(e);
		}
	}

	protected String doTranslate(
			String fromLanguageId, String toLanguageId, String fromText)
		throws Exception {

		fromLanguageId = getMicrosoftLanguageId(fromLanguageId);
		toLanguageId = getMicrosoftLanguageId(toLanguageId);

		Http.Options options = new Http.Options();

		StringBundler sb = new StringBundler(7);

		sb.append("http://api.microsofttranslator.com/v2/Http.svc/Translate?");
		sb.append("text=");
		sb.append(HttpUtil.encodeURL(fromText));
		sb.append("&from=");
		sb.append(fromLanguageId);
		sb.append("&to=");
		sb.append(toLanguageId);

		options.setLocation(sb.toString());

		String accessToken = _microsoftTranslatorAuthenticator.getAccessToken();

		if (Validator.isNull(accessToken)) {
			throw new MicrosoftTranslatorException(
				_microsoftTranslatorAuthenticator.getError());
		}

		options.addHeader("Authorization", "Bearer " + accessToken);

		String text = HttpUtil.URLtoString(options);

		int x = text.indexOf(">") + 1;
		int y = text.indexOf("</string>", x);

		if ((x == -1) || (y == -1)) {
			x = text.indexOf("Message: ");
			y = text.indexOf("<", x);

			if ((x > -1) && (y > -1)) {
				text = text.substring(x, y);
			}

			throw new MicrosoftTranslatorException(text);
		}

		String toText = text.substring(x, y);

		toText = toText.trim();

		return StringUtil.replace(toText, CharPool.NEW_LINE, CharPool.SPACE);
	}

	protected String getMicrosoftLanguageId(String languageId) {
		if (languageId.equals("pt_BR") || languageId.equals("pt_PT")) {
			return "pt";
		}
		else if (languageId.equals("hi_IN")) {
			return "hi";
		}
		else if (languageId.equals("in")) {
			return "id";
		}
		else if (languageId.equals("iw")) {
			return "he";
		}
		else if (languageId.equals("nb")) {
			return "no";
		}
		else if (languageId.equals("zh_CN")) {
			return "zh-CHS";
		}
		else if (languageId.equals("zh_TW")) {
			return "zh-CHT";
		}

		return languageId;
	}

	private MicrosoftTranslatorAuthenticator _microsoftTranslatorAuthenticator;

}