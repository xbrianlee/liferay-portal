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

package com.liferay.portlet.invitation.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Validator;

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

		validateEmailMessage(actionRequest);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	protected void validateEmailMessage(ActionRequest actionRequest) {
		String emailMessageSubject = getParameter(
			actionRequest, "emailMessageSubject");
		String emailMessageBody = getParameter(
			actionRequest, "emailMessageBody");

		if (Validator.isNull(emailMessageSubject)) {
			SessionErrors.add(actionRequest, "emailMessageSubject");
		}
		else if (Validator.isNull(emailMessageBody)) {
			SessionErrors.add(actionRequest, "emailMessageBody");
		}
	}

}