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

package com.liferay.portlet.journal.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
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

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (tabs2.equals("email-from")) {
			validateEmailFrom(actionRequest);
		}
		else if (tabs2.equals("web-content-added-email")) {
			validateEmailArticleAdded(actionRequest);
		}
		else if (tabs2.equals("web-content-approval-denied-email")) {
			validateEmailArticleApprovalDenied(actionRequest);
		}
		else if (tabs2.equals("web-content-approval-granted-email")) {
			validateEmailArticleApprovalGranted(actionRequest);
		}
		else if (tabs2.equals("web-content-approval-requested-email")) {
			validateEmailArticleApprovalRequested(actionRequest);
		}
		else if (tabs2.equals("web-content-review-email")) {
			validateEmailArticleReview(actionRequest);
		}
		else if (tabs2.equals("web-content-updated-email")) {
			validateEmailArticleUpdated(actionRequest);
		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	protected void validateEmailArticleAdded(ActionRequest actionRequest)
		throws Exception {

		String emailArticleAddedSubject = getParameter(
			actionRequest, "emailArticleAddedSubject");
		String emailArticleAddedBody = getParameter(
			actionRequest, "emailArticleAddedBody");

		if (Validator.isNull(emailArticleAddedSubject)) {
			SessionErrors.add(actionRequest, "emailArticleAddedSubject");
		}
		else if (Validator.isNull(emailArticleAddedBody)) {
			SessionErrors.add(actionRequest, "emailArticleAddedBody");
		}
	}

	protected void validateEmailArticleApprovalDenied(
			ActionRequest actionRequest)
		throws Exception {

		String emailArticleApprovalDeniedSubject = getParameter(
			actionRequest, "emailArticleApprovalDeniedSubject");
		String emailArticleApprovalDeniedBody = getParameter(
			actionRequest, "emailArticleApprovalDeniedBody");

		if (Validator.isNull(emailArticleApprovalDeniedSubject)) {
			SessionErrors.add(
				actionRequest, "emailArticleApprovalDeniedSubject");
		}
		else if (Validator.isNull(emailArticleApprovalDeniedBody)) {
			SessionErrors.add(actionRequest, "emailArticleApprovalDeniedBody");
		}
	}

	protected void validateEmailArticleApprovalGranted(
			ActionRequest actionRequest)
		throws Exception {

		String emailArticleApprovalGrantedSubject = getParameter(
			actionRequest, "emailArticleApprovalGrantedSubject");
		String emailArticleApprovalGrantedBody = getParameter(
			actionRequest, "emailArticleApprovalGrantedBody");

		if (Validator.isNull(emailArticleApprovalGrantedSubject)) {
			SessionErrors.add(
				actionRequest, "emailArticleApprovalGrantedSubject");
		}
		else if (Validator.isNull(emailArticleApprovalGrantedBody)) {
			SessionErrors.add(actionRequest, "emailArticleApprovalGrantedBody");
		}
	}

	protected void validateEmailArticleApprovalRequested(
			ActionRequest actionRequest)
		throws Exception {

		String emailArticleApprovalRequestedSubject = getParameter(
			actionRequest, "emailArticleApprovalRequestedSubject");
		String emailArticleApprovalRequestedBody = getParameter(
			actionRequest, "emailArticleApprovalRequestedBody");

		if (Validator.isNull(emailArticleApprovalRequestedSubject)) {
			SessionErrors.add(
				actionRequest, "emailArticleApprovalRequestedSubject");
		}
		else if (Validator.isNull(emailArticleApprovalRequestedBody)) {
			SessionErrors.add(
				actionRequest, "emailArticleApprovalRequestedBody");
		}
	}

	protected void validateEmailArticleReview(ActionRequest actionRequest)
		throws Exception {

		String emailArticleReviewSubject = getParameter(
			actionRequest, "emailArticleReviewSubject");
		String emailArticleReviewBody = getParameter(
			actionRequest, "emailArticleReviewBody");

		if (Validator.isNull(emailArticleReviewSubject)) {
			SessionErrors.add(actionRequest, "emailArticleReviewSubject");
		}
		else if (Validator.isNull(emailArticleReviewBody)) {
			SessionErrors.add(actionRequest, "emailArticleReviewBody");
		}
	}

	protected void validateEmailArticleUpdated(ActionRequest actionRequest)
		throws Exception {

		String emailArticleUpdatedSubject = getParameter(
			actionRequest, "emailArticleUpdatedSubject");
		String emailArticleUpdatedBody = getParameter(
			actionRequest, "emailArticleUpdatedBody");

		if (Validator.isNull(emailArticleUpdatedSubject)) {
			SessionErrors.add(actionRequest, "emailArticleUpdatedSubject");
		}
		else if (Validator.isNull(emailArticleUpdatedBody)) {
			SessionErrors.add(actionRequest, "emailArticleUpdatedBody");
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
		else if (!Validator.isEmailAddress(emailFromAddress)) {
			SessionErrors.add(actionRequest, "emailFromAddress");
		}
	}

}