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

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletSetupUtil {

	public static JSONObject cssToJSONObject(
			PortletPreferences portletSetup, String css)
		throws Exception {

		return _toJSONObject(portletSetup, css);
	}

	public static String cssToJSONString(PortletPreferences portletSetup) {
		String css = portletSetup.getValue("portletSetupCss", StringPool.BLANK);

		try {
			JSONObject jsonObject = _toJSONObject(portletSetup, css);

			return jsonObject.toString();
		}
		catch (Exception e) {
			css = null;

			if (_log.isWarnEnabled()) {
				_log.warn(e);
			}
		}

		return css;
	}

	private static JSONObject _toJSONObject(
			PortletPreferences portletSetup, String css)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("Transform CSS to JSON " + css);
		}

		JSONObject cssJSONObject = null;

		if (Validator.isNotNull(css)) {
			cssJSONObject = JSONFactoryUtil.createJSONObject(css);

			cssJSONObject.put("hasCssValue", true);
		}
		else {
			cssJSONObject = JSONFactoryUtil.createJSONObject();
		}

		JSONObject portletDataJSONObject = JSONFactoryUtil.createJSONObject();

		cssJSONObject.put("portletData", portletDataJSONObject);

		JSONObject titlesJSONObject = JSONFactoryUtil.createJSONObject();

		portletDataJSONObject.put("titles", titlesJSONObject);

		Locale[] locales = LanguageUtil.getAvailableLocales();

		for (int i = 0; i < locales.length; i++) {
			String languageId = LocaleUtil.toLanguageId(locales[i]);

			String title = portletSetup.getValue(
				"portletSetupTitle_" + languageId, null);

			if (Validator.isNotNull(languageId)) {
				titlesJSONObject.put(languageId, title);
			}
		}

		boolean useCustomTitle = GetterUtil.getBoolean(
			portletSetup.getValue("portletSetupUseCustomTitle", null));
		String showBorders = GetterUtil.getString(
			portletSetup.getValue("portletSetupShowBorders", null));
		String linkToLayoutUuid = GetterUtil.getString(
			portletSetup.getValue("portletSetupLinkToLayoutUuid", null));

		portletDataJSONObject.put("useCustomTitle", useCustomTitle);
		portletDataJSONObject.put("showBorders", showBorders);
		portletDataJSONObject.put("portletLinksTarget", linkToLayoutUuid);

		return cssJSONObject;
	}

	private static Log _log = LogFactoryUtil.getLog(PortletSetupUtil.class);

}