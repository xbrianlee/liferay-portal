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

package com.liferay.portlet.messageboards.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermissionUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.messageboards.NoSuchCategoryException;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBBanLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class MBMessagePermission {

	public static void check(
			PermissionChecker permissionChecker, long messageId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, messageId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, MBMessage message,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, message, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long messageId,
			String actionId)
		throws PortalException, SystemException {

		MBMessage message = MBMessageLocalServiceUtil.getMessage(messageId);

		return contains(permissionChecker, message, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, MBMessage message,
			String actionId)
		throws PortalException, SystemException {

		if (MBBanLocalServiceUtil.hasBan(
				message.getGroupId(), permissionChecker.getUserId())) {

			return false;
		}

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, message.getGroupId(), MBMessage.class.getName(),
			message.getMessageId(), PortletKeys.MESSAGE_BOARDS, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (message.isDraft() || message.isScheduled()) {
			if (actionId.equals(ActionKeys.VIEW) &&
				!contains(permissionChecker, message, ActionKeys.UPDATE)) {

				return false;
			}
		}
		else if (message.isPending()) {
			hasPermission = WorkflowPermissionUtil.hasPermission(
				permissionChecker, message.getGroupId(),
				message.getWorkflowClassName(), message.getMessageId(),
				actionId);

			if (hasPermission != null) {
				return hasPermission.booleanValue();
			}
		}

		if (actionId.equals(ActionKeys.VIEW) &&
			PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {

			long categoryId = message.getCategoryId();

			if ((categoryId !=
					MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) &&
				(categoryId != MBCategoryConstants.DISCUSSION_CATEGORY_ID)) {

				try {
					MBCategory category =
						MBCategoryLocalServiceUtil.getCategory(categoryId);

					if (!MBCategoryPermission.contains(
							permissionChecker, category, actionId)) {

						return false;
					}
				}
				catch (NoSuchCategoryException nsce) {
					if (!message.isInTrash()) {
						throw nsce;
					}
				}
			}
		}

		if (permissionChecker.hasOwnerPermission(
				message.getCompanyId(), MBMessage.class.getName(),
				message.getRootMessageId(), message.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			message.getGroupId(), MBMessage.class.getName(),
			message.getMessageId(), actionId);
	}

}