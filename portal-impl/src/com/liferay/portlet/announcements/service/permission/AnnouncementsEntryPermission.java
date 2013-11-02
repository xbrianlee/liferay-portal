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

package com.liferay.portlet.announcements.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.impl.VirtualLayout;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portlet.announcements.model.AnnouncementsEntry;
import com.liferay.portlet.announcements.service.AnnouncementsEntryLocalServiceUtil;

/**
 * @author Raymond Augé
 */
public class AnnouncementsEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, AnnouncementsEntry entry,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, entry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, Layout layout, String name,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, layout, name, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long entryId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, entryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long plid, String name,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, plid, name, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, AnnouncementsEntry entry,
			String actionId)
		throws PortalException, SystemException {

		if (permissionChecker.hasOwnerPermission(
				entry.getCompanyId(), AnnouncementsEntry.class.getName(),
				entry.getEntryId(), entry.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			entry.getGroupId(), AnnouncementsEntry.class.getName(),
			entry.getEntryId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, Layout layout, String name,
			String actionId)
		throws PortalException, SystemException {

		if (layout instanceof VirtualLayout) {
			VirtualLayout virtualLayout = (VirtualLayout)layout;

			layout = virtualLayout.getSourceLayout();
		}

		if (permissionChecker.isGroupAdmin(layout.getGroupId()) ||
			permissionChecker.isGroupOwner(layout.getGroupId())) {

			return true;
		}

		return PortletPermissionUtil.contains(
			permissionChecker, layout, name, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long entryId, String actionId)
		throws PortalException, SystemException {

		AnnouncementsEntry entry = AnnouncementsEntryLocalServiceUtil.getEntry(
			entryId);

		return contains(permissionChecker, entry, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long plid, String name,
			String actionId)
		throws PortalException, SystemException {

		Layout layout = LayoutLocalServiceUtil.fetchLayout(plid);

		return contains(permissionChecker, layout, name, actionId);
	}

}