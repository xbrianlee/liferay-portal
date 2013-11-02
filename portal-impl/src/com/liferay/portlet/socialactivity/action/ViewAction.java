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

package com.liferay.portlet.socialactivity.action;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.comparator.ModelResourceComparator;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.social.model.SocialActivityCounterConstants;
import com.liferay.portlet.social.model.SocialActivityCounterDefinition;
import com.liferay.portlet.social.model.SocialActivityDefinition;
import com.liferay.portlet.social.model.SocialActivitySetting;
import com.liferay.portlet.social.service.SocialActivitySettingServiceUtil;
import com.liferay.portlet.social.util.SocialConfigurationUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Zsolt Szabó
 * @author Zsolt Berentey
 */
public class ViewAction extends PortletAction {

	@Override
	public void processAction(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.UPDATE)) {
				updateActivitySettings(actionRequest);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception e) {
			if (e instanceof PrincipalException) {
				SessionErrors.add(actionRequest, e.getClass());
			}
			else {
				throw e;
			}
		}
	}

	@Override
	public ActionForward render(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			renderRequest.setAttribute(
				WebKeys.SOCIAL_ACTIVITY_SETTINGS_MAP,
				getActivitySettingsMap(themeDisplay));
		}
		catch (Exception e) {
			if (e instanceof PrincipalException) {
				SessionErrors.add(renderRequest, e.getClass());

				return actionMapping.findForward(
					"portlet.social_activity.error");
			}
			else {
				throw e;
			}
		}

		return actionMapping.findForward("portlet.social_activity.view");
	}

	protected Map<String, Boolean> getActivitySettingsMap(
			ThemeDisplay themeDisplay)
		throws Exception {

		Map<String, Boolean> activitySettingsMap =
			new LinkedHashMap<String, Boolean>();

		List<SocialActivitySetting> activitySettings =
			SocialActivitySettingServiceUtil.getActivitySettings(
				themeDisplay.getSiteGroupIdOrLiveGroupId());

		String[] modelNames = SocialConfigurationUtil.getActivityModelNames();

		Comparator<String> comparator = new ModelResourceComparator(
			themeDisplay.getLocale());

		Arrays.sort(modelNames, comparator);

		for (String modelName : modelNames) {
			List<SocialActivityDefinition> activityDefinitions =
				SocialActivitySettingServiceUtil.getActivityDefinitions(
					themeDisplay.getScopeGroupId(), modelName);

			for (SocialActivityDefinition activityDefinition :
					activityDefinitions) {

				if (activityDefinition.isCountersEnabled()) {
					activitySettingsMap.put(modelName, false);

					break;
				}
			}
		}

		for (SocialActivitySetting activitySetting : activitySettings) {
			String name = activitySetting.getName();

			if (name.equals("enabled") &&
				activitySettingsMap.containsKey(
					activitySetting.getClassName())) {

				activitySettingsMap.put(
					activitySetting.getClassName(),
					GetterUtil.getBoolean(activitySetting.getValue()));
			}
		}

		return activitySettingsMap;
	}

	protected SocialActivityCounterDefinition updateActivityCounterDefinition(
		JSONObject actionJSONObject,
		SocialActivityDefinition activityDefinition,
		String activityCounterName) {

		SocialActivityCounterDefinition activityCounterDefinition =
			activityDefinition.getActivityCounterDefinition(
				activityCounterName);

		if (activityCounterDefinition == null) {
			activityCounterDefinition = new SocialActivityCounterDefinition();

			activityCounterDefinition.setName(activityCounterName);
		}

		if (activityCounterName.equals(
				SocialActivityCounterConstants.NAME_CONTRIBUTION)) {

			activityCounterDefinition.setOwnerType(
				SocialActivityCounterConstants.TYPE_CREATOR);
		}
		else if (activityCounterName.equals(
					SocialActivityCounterConstants.NAME_PARTICIPATION)) {

			activityCounterDefinition.setOwnerType(
				SocialActivityCounterConstants.TYPE_ACTOR);
		}
		else if (activityCounterName.equals(
					SocialActivityCounterConstants.NAME_POPULARITY)) {

			activityCounterDefinition.setOwnerType(
				SocialActivityCounterConstants.TYPE_ASSET);

			activityCounterName =
				SocialActivityCounterConstants.NAME_CONTRIBUTION;
		}
		else {
			throw new IllegalArgumentException();
		}

		activityCounterDefinition.setEnabled(
			actionJSONObject.getBoolean("active"));
		activityCounterDefinition.setIncrement(
			actionJSONObject.getInt(activityCounterName + "Increment"));
		activityCounterDefinition.setLimitPeriod(
			actionJSONObject.getInt(activityCounterName + "LimitPeriod"));
		activityCounterDefinition.setLimitValue(
			actionJSONObject.getInt(activityCounterName + "LimitValue"));

		return activityCounterDefinition;
	}

	protected void updateActivitySettings(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String settingsJSON = ParamUtil.getString(
			actionRequest, "settingsJSON");

		JSONObject settingsJSONObject = JSONFactoryUtil.createJSONObject(
			settingsJSON);

		JSONArray actionsJSONArray = settingsJSONObject.getJSONArray("actions");

		String modelName = settingsJSONObject.getString("modelName");

		for (int i = 0; i < actionsJSONArray.length(); i++) {
			JSONObject actionJSONObject = actionsJSONArray.getJSONObject(i);

			int activityType = actionJSONObject.getInt("activityType");

			SocialActivityDefinition activityDefinition =
				SocialActivitySettingServiceUtil.getActivityDefinition(
					themeDisplay.getSiteGroupIdOrLiveGroupId(), modelName,
					activityType);

			if (activityDefinition == null) {
				continue;
			}

			List<SocialActivityCounterDefinition> activityCounterDefinitions =
				new ArrayList<SocialActivityCounterDefinition>();

			activityCounterDefinitions.add(
				updateActivityCounterDefinition(
					actionJSONObject, activityDefinition,
					SocialActivityCounterConstants.NAME_CONTRIBUTION));

			activityCounterDefinitions.add(
				updateActivityCounterDefinition(
					actionJSONObject, activityDefinition,
					SocialActivityCounterConstants.NAME_PARTICIPATION));

			activityCounterDefinitions.add(
				updateActivityCounterDefinition(
					actionJSONObject, activityDefinition,
					SocialActivityCounterConstants.NAME_POPULARITY));

			SocialActivitySettingServiceUtil.updateActivitySettings(
				themeDisplay.getSiteGroupIdOrLiveGroupId(), modelName,
				activityType, activityCounterDefinitions);
		}
	}

}