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

package com.liferay.portlet.rss.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.ValidatorException;

/**
 * @author Brian Wing Shun Chan
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals(Constants.UPDATE)) {
			updateSubscriptions(actionRequest);

			super.processAction(portletConfig, actionRequest, actionResponse);

			return;
		}

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences = actionRequest.getPreferences();

		if (cmd.equals("remove-footer-article")) {
			removeFooterArticle(actionRequest, preferences);
		}
		else if (cmd.equals("remove-header-article")) {
			removeHeaderArticle(actionRequest, preferences);
		}
		else if (cmd.equals("set-footer-article")) {
			setFooterArticle(actionRequest, preferences);
		}
		else if (cmd.equals("set-header-article")) {
			setHeaderArticle(actionRequest, preferences);
		}

		if (!SessionErrors.isEmpty(actionRequest)) {
			return;
		}

		try {
			preferences.store();
		}
		catch (ValidatorException ve) {
			SessionErrors.add(
				actionRequest, ValidatorException.class.getName(), ve);

			return;
		}

		SessionMessages.add(
			actionRequest,
			PortalUtil.getPortletId(actionRequest) +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			portletResource);

		SessionMessages.add(
			actionRequest,
			PortalUtil.getPortletId(actionRequest) +
				SessionMessages.KEY_SUFFIX_UPDATED_CONFIGURATION);
	}

	protected void removeFooterArticle(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		preferences.setValues("footerArticleValues", new String[] {"0", ""});
	}

	protected void removeHeaderArticle(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		preferences.setValues("headerArticleValues", new String[] {"0", ""});
	}

	protected void setFooterArticle(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		long articleGroupId = ParamUtil.getLong(
			actionRequest, "articleGroupId");
		String articleId = ParamUtil.getString(actionRequest, "articleId");

		preferences.setValues(
			"footerArticleValues",
			new String[] {String.valueOf(articleGroupId), articleId});
	}

	protected void setHeaderArticle(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		long articleGroupId = ParamUtil.getLong(
			actionRequest, "articleGroupId");
		String articleId = ParamUtil.getString(actionRequest, "articleId");

		preferences.setValues(
			"headerArticleValues",
			new String[] {String.valueOf(articleGroupId), articleId});
	}

	protected void updateSubscriptions(ActionRequest actionRequest)
		throws Exception {

		int[] subscriptionIndexes = StringUtil.split(
			ParamUtil.getString(actionRequest, "subscriptionIndexes"), 0);

		Map<String, String> subscriptions = new LinkedHashMap<String, String>();

		for (int subscriptionIndex : subscriptionIndexes) {
			String url = ParamUtil.getString(
				actionRequest, "url" + subscriptionIndex);
			String title = ParamUtil.getString(
				actionRequest, "title" + subscriptionIndex);

			if (Validator.isNull(url)) {
				continue;
			}

			subscriptions.put(url, title);
		}

		String[] urls = new String[subscriptions.size()];
		String[] titles = new String[subscriptions.size()];

		int i = 0;

		for (Map.Entry<String, String> entry : subscriptions.entrySet()) {
			urls[i] = entry.getKey();
			titles[i] = entry.getValue();

			i++;
		}

		setPreference(actionRequest, "urls", urls);
		setPreference(actionRequest, "titles", titles);
	}

}