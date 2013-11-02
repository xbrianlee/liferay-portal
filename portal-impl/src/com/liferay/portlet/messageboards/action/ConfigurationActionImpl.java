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

package com.liferay.portlet.messageboards.action;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.NumericalStringComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

/**
 * @author Brian Wing Shun Chan
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (tabs2.equals("email-from")) {
			validateEmailFrom(actionRequest);
		}
		else if (tabs2.equals("message-added-email")) {
			validateEmailMessageAdded(actionRequest);
		}
		else if (tabs2.equals("message-updated-email")) {
			validateEmailMessageUpdated(actionRequest);
		}
		else if (tabs2.equals("thread-priorities")) {
			updateThreadPriorities(actionRequest);
		}
		else if (tabs2.equals("user-ranks")) {
			updateUserRanks(actionRequest);
		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	protected boolean isValidUserRank(String rank) {
		if ((StringUtil.count(rank, StringPool.EQUAL) != 1) ||
			rank.startsWith(StringPool.EQUAL) ||
			rank.endsWith(StringPool.EQUAL)) {

			return false;
		}

		return true;
	}

	protected void updateThreadPriorities(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Locale[] locales = LanguageUtil.getAvailableLocales(
			themeDisplay.getSiteGroupId());

		for (int i = 0; i < locales.length; i++) {
			String languageId = LocaleUtil.toLanguageId(locales[i]);

			List<String> priorities = new ArrayList<String>();

			for (int j = 0; j < 10; j++) {
				String name = ParamUtil.getString(
					actionRequest, "priorityName" + j + "_" + languageId);
				String image = ParamUtil.getString(
					actionRequest, "priorityImage" + j + "_" + languageId);
				double value = ParamUtil.getDouble(
					actionRequest, "priorityValue" + j + "_" + languageId);

				if (Validator.isNotNull(name) || Validator.isNotNull(image) ||
					(value != 0.0)) {

					priorities.add(
						name + StringPool.COMMA + image + StringPool.COMMA +
							value);
				}
			}

			String preferenceName = LocalizationUtil.getPreferencesKey(
				"priorities", languageId);

			setPreference(
				actionRequest, preferenceName,
				priorities.toArray(new String[priorities.size()]));
		}
	}

	protected void updateUserRanks(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Locale[] locales = LanguageUtil.getAvailableLocales(
			themeDisplay.getSiteGroupId());

		for (Locale locale : locales) {
			String languageId = LocaleUtil.toLanguageId(locale);

			String[] ranks = StringUtil.splitLines(
				ParamUtil.getString(actionRequest, "ranks_" + languageId));

			Map<String, String> map = new TreeMap<String, String>(
				new NumericalStringComparator());

			for (String rank : ranks) {
				if (!isValidUserRank(rank)) {
					SessionErrors.add(actionRequest, "userRank");

					return;
				}

				String[] kvp = StringUtil.split(rank, CharPool.EQUAL);

				String kvpName = kvp[0];
				String kvpValue = kvp[1];

				map.put(kvpValue, kvpName);
			}

			ranks = new String[map.size()];

			int count = 0;

			for (Map.Entry<String, String> entry : map.entrySet()) {
				String kvpValue = entry.getKey();
				String kvpName = entry.getValue();

				ranks[count++] = kvpName + StringPool.EQUAL + kvpValue;
			}

			String preferenceName = LocalizationUtil.getPreferencesKey(
				"ranks", languageId);

			setPreference(actionRequest, preferenceName, ranks);
		}
	}

	protected void validateEmailFrom(ActionRequest actionRequest)
		throws Exception {

		String emailFromName = getParameter(actionRequest, "emailFromName");
		String emailFromAddress = getParameter(
			actionRequest, "emailFromAddress");

		if (Validator.isNull(emailFromName)) {
			SessionErrors.add(actionRequest, "emailFromName");
		}
		else if (!Validator.isEmailAddress(emailFromAddress) &&
				 !Validator.isVariableTerm(emailFromAddress)) {

			SessionErrors.add(actionRequest, "emailFromAddress");
		}
	}

	protected void validateEmailMessageAdded(ActionRequest actionRequest)
		throws Exception {

		String emailMessageAddedSubject = getParameter(
			actionRequest, "emailMessageAddedSubject");
		String emailMessageAddedBody = getParameter(
			actionRequest, "emailMessageAddedBody");

		if (Validator.isNull(emailMessageAddedSubject)) {
			SessionErrors.add(actionRequest, "emailMessageAddedSubject");
		}
		else if (Validator.isNull(emailMessageAddedBody)) {
			SessionErrors.add(actionRequest, "emailMessageAddedBody");
		}
	}

	protected void validateEmailMessageUpdated(ActionRequest actionRequest)
		throws Exception {

		String emailMessageUpdatedSubject = getParameter(
			actionRequest, "emailMessageUpdatedSubject");
		String emailMessageUpdatedBody = getParameter(
			actionRequest, "emailMessageUpdatedBody");

		if (Validator.isNull(emailMessageUpdatedSubject)) {
			SessionErrors.add(actionRequest, "emailMessageUpdatedSubject");
		}
		else if (Validator.isNull(emailMessageUpdatedBody)) {
			SessionErrors.add(actionRequest, "emailMessageUpdatedBody");
		}
	}

}