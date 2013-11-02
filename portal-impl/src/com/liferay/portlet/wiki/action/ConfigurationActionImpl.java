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

package com.liferay.portlet.wiki.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

/**
 * @author Bruno Farache
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (tabs2.equals("display-settings")) {
			validateDisplaySettings(actionRequest);
		}
		else if (tabs2.equals("email-from")) {
			validateEmailFrom(actionRequest);
		}
		else if (tabs2.equals("page-added-email")) {
			validateEmailPageAdded(actionRequest);
		}
		else if (tabs2.equals("page-updated-email")) {
			validateEmailPageUpdated(actionRequest);
		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	protected void validateDisplaySettings(ActionRequest actionRequest)
		throws Exception {

		String visibleNodes = getParameter(actionRequest, "visibleNodes");

		if (Validator.isNull(visibleNodes)) {
			SessionErrors.add(actionRequest, "visibleNodesCount");
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

	protected void validateEmailPageAdded(ActionRequest actionRequest)
		throws Exception {

		String emailPageAddedSubject = getParameter(
			actionRequest, "emailPageAddedSubject");
		String emailPageAddedBody = getParameter(
			actionRequest, "emailPageAddedBody");

		if (Validator.isNull(emailPageAddedSubject)) {
			SessionErrors.add(actionRequest, "emailPageAddedSubject");
		}
		else if (Validator.isNull(emailPageAddedBody)) {
			SessionErrors.add(actionRequest, "emailPageAddedBody");
		}
	}

	protected void validateEmailPageUpdated(ActionRequest actionRequest)
		throws Exception {

		String emailPageUpdatedSubject = getParameter(
			actionRequest, "emailPageUpdatedSubject");
		String emailPageUpdatedBody = getParameter(
			actionRequest, "emailPageUpdatedBody");

		if (Validator.isNull(emailPageUpdatedSubject)) {
			SessionErrors.add(actionRequest, "emailPageUpdatedSubject");
		}
		else if (Validator.isNull(emailPageUpdatedBody)) {
			SessionErrors.add(actionRequest, "emailPageUpdatedBody");
		}
	}

}