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

package com.liferay.portlet.dynamicdatamapping.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil;

/**
 * @author Eduardo Lundgren
 */
public class DDMTemplatePermission {

	public static void check(
			PermissionChecker permissionChecker, DDMTemplate template,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, template, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long templateId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, templateId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, DDMTemplate template,
		String actionId) {

		return contains(permissionChecker, template, null, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, DDMTemplate template,
			String portletId, String actionId) {

		if (Validator.isNotNull(portletId)) {
			Boolean hasPermission = StagingPermissionUtil.hasPermission(
				permissionChecker, template.getGroupId(),
				DDMTemplate.class.getName(), template.getTemplateId(),
				portletId, actionId);

			if (hasPermission != null) {
				return hasPermission.booleanValue();
			}
		}

		if (permissionChecker.hasOwnerPermission(
				template.getCompanyId(), DDMTemplate.class.getName(),
				template.getTemplateId(), template.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			template.getGroupId(), DDMTemplate.class.getName(),
			template.getTemplateId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long templateId,
			String actionId)
		throws PortalException, SystemException {

		return contains(permissionChecker, templateId, null, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long templateId,
			String portletId, String actionId)
		throws PortalException, SystemException {

		DDMTemplate template = DDMTemplateLocalServiceUtil.getTemplate(
			templateId);

		return contains(permissionChecker, template, portletId, actionId);
	}

}