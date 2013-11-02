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

package com.liferay.portlet.polls.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.polls.model.PollsQuestion;
import com.liferay.portlet.polls.service.PollsQuestionLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class PollsQuestionPermission {

	public static void check(
			PermissionChecker permissionChecker, long questionId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, questionId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, PollsQuestion question,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, question, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long questionId,
			String actionId)
		throws PortalException, SystemException {

		PollsQuestion question = PollsQuestionLocalServiceUtil.getQuestion(
			questionId);

		return contains(permissionChecker, question, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, PollsQuestion question,
		String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, question.getGroupId(),
			PollsQuestion.class.getName(), question.getQuestionId(),
			PortletKeys.POLLS, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (permissionChecker.hasOwnerPermission(
				question.getCompanyId(), PollsQuestion.class.getName(),
				question.getQuestionId(), question.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			question.getGroupId(), PollsQuestion.class.getName(),
			question.getQuestionId(), actionId);
	}

}