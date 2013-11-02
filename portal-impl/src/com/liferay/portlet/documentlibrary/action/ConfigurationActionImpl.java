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

package com.liferay.portlet.documentlibrary.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

/**
 * @author Jorge Ferrer
 * @author Sergio González
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (Validator.isNotNull(cmd)) {
			if (tabs2.equals("display-settings")) {
				validateRootFolder(actionRequest);
			}
			else if (tabs2.equals("document-added-email")) {
				validateEmailFileEntryAdded(actionRequest);
			}
			else if (tabs2.equals("document-updated-email")) {
				validateEmailFileEntryUpdated(actionRequest);
			}
			else if (tabs2.equals("email-from")) {
				validateEmailFrom(actionRequest);
			}
		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	protected void validateEmailFileEntryAdded(ActionRequest actionRequest)
		throws Exception {

		String emailFileEntryAddedSubject = getLocalizedParameter(
			actionRequest, "emailFileEntryAddedSubject");
		String emailFileEntryAddedBody = getLocalizedParameter(
			actionRequest, "emailFileEntryAddedBody");

		if (Validator.isNull(emailFileEntryAddedSubject)) {
			SessionErrors.add(actionRequest, "emailFileEntryAddedSubject");
		}
		else if (Validator.isNull(emailFileEntryAddedBody)) {
			SessionErrors.add(actionRequest, "emailFileEntryAddedBody");
		}
	}

	protected void validateEmailFileEntryUpdated(ActionRequest actionRequest)
		throws Exception {

		String emailFileEntryUpdatedSubject = getLocalizedParameter(
			actionRequest, "emailFileEntryUpdatedSubject");
		String emailFileEntryUpdatedBody = getLocalizedParameter(
			actionRequest, "emailFileEntryUpdatedBody");

		if (Validator.isNull(emailFileEntryUpdatedSubject)) {
			SessionErrors.add(actionRequest, "emailFileEntryUpdatedSubject");
		}
		else if (Validator.isNull(emailFileEntryUpdatedBody)) {
			SessionErrors.add(actionRequest, "emailFileEntryUpdatedBody");
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

	protected void validateRootFolder(ActionRequest actionRequest)
		throws Exception {

		long rootFolderId = GetterUtil.getLong(
			getParameter(actionRequest, "rootFolderId"));

		if (rootFolderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			try {
				DLAppLocalServiceUtil.getFolder(rootFolderId);
			}
			catch (NoSuchFolderException nsfe) {
				SessionErrors.add(actionRequest, "rootFolderIdInvalid");
			}
		}
	}

}