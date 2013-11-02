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

package com.liferay.portlet.mobiledevicerules.action;

import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.mobiledevicerules.NoSuchRuleGroupException;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupInstanceLocalServiceUtil;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupInstanceServiceUtil;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Edward Han
 */
public class EditRuleGroupInstanceAction extends PortletAction {

	@Override
	public void processAction(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteRuleGroupInstance(actionRequest);
			}
			else if (cmd.equals(Constants.UPDATE)) {
				updateRuleGroupInstancesPriorities(actionRequest);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception e) {
			if (e instanceof PrincipalException) {
				SessionErrors.add(actionRequest, e.getClass());

				setForward(actionRequest, "portlet.mobile_device_rules.error");
			}
			else if (e instanceof NoSuchRuleGroupException) {
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

		long ruleGroupInstanceId = ParamUtil.getLong(
			renderRequest, "ruleGroupInstanceId");

		MDRRuleGroupInstance ruleGroupInstance =
			MDRRuleGroupInstanceLocalServiceUtil.fetchRuleGroupInstance(
				ruleGroupInstanceId);

		renderRequest.setAttribute(
			WebKeys.MOBILE_DEVICE_RULES_RULE_INSTANCE, ruleGroupInstance);

		long ruleGroupId = BeanParamUtil.getLong(
			ruleGroupInstance, renderRequest, "ruleGroupId");

		MDRRuleGroup ruleGroup = MDRRuleGroupLocalServiceUtil.fetchRuleGroup(
			ruleGroupId);

		renderRequest.setAttribute(
			WebKeys.MOBILE_DEVICE_RULES_RULE_GROUP, ruleGroup);

		return actionMapping.findForward(
			"portlet.mobile_device_rules.edit_rule_group_instance_priorities");
	}

	protected void deleteRuleGroupInstance(ActionRequest actionRequest)
		throws PortalException, SystemException {

		long ruleGroupInstanceId = ParamUtil.getLong(
			actionRequest, "ruleGroupInstanceId");

		MDRRuleGroupInstanceServiceUtil.deleteRuleGroupInstance(
			ruleGroupInstanceId);
	}

	protected void updateRuleGroupInstancesPriorities(
			ActionRequest actionRequest)
		throws PortalException, SystemException {

		String ruleGroupsInstancesJSON = ParamUtil.getString(
			actionRequest, "ruleGroupsInstancesJSON");

		JSONArray ruleGroupsInstancesJSONArray =
			JSONFactoryUtil.createJSONArray(ruleGroupsInstancesJSON);

		for (int i = 0; i < ruleGroupsInstancesJSONArray.length(); i++) {
			JSONObject ruleGroupInstanceJSONObject =
				ruleGroupsInstancesJSONArray.getJSONObject(i);

			long ruleGroupInstanceId = ruleGroupInstanceJSONObject.getLong(
				"ruleGroupInstanceId");

			int priority = ruleGroupInstanceJSONObject.getInt("priority");

			MDRRuleGroupInstanceServiceUtil.updateRuleGroupInstance(
				ruleGroupInstanceId, priority);
		}
	}

}