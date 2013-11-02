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

package com.liferay.portlet.documentlibrary.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class DLFolderPermission {

	public static void check(
			PermissionChecker permissionChecker, DLFolder dlFolder,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, dlFolder, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, Folder folder, String actionId)
		throws PortalException, SystemException {

		if (!folder.containsPermission(permissionChecker, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId, long folderId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, groupId, folderId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, DLFolder dlFolder,
			String actionId)
		throws PortalException, SystemException {

		if (actionId.equals(ActionKeys.ADD_FOLDER)) {
			actionId = ActionKeys.ADD_SUBFOLDER;
		}

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, dlFolder.getGroupId(), DLFolder.class.getName(),
			dlFolder.getFolderId(), PortletKeys.DOCUMENT_LIBRARY, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (actionId.equals(ActionKeys.VIEW) &&
			PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {

			try {
				long dlFolderId = dlFolder.getFolderId();

				while (dlFolderId !=
							DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

					dlFolder = DLFolderLocalServiceUtil.getFolder(dlFolderId);

					if (!_hasPermission(
							permissionChecker, dlFolder, actionId)) {

						return false;
					}

					dlFolderId = dlFolder.getParentFolderId();
				}
			}
			catch (NoSuchFolderException nsfe) {
				if (!dlFolder.isInTrash()) {
					throw nsfe;
				}
			}

			return true;
		}

		return _hasPermission(permissionChecker, dlFolder, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, Folder folder, String actionId)
		throws PortalException, SystemException {

		return folder.containsPermission(permissionChecker, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, long folderId,
			String actionId)
		throws PortalException, SystemException {

		if (folderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			// Prevent the propagation of checks for actions that are not
			// supported at the application resource level. See LPS-24245.

			if (actionId.equals(ActionKeys.ACCESS) ||
				actionId.equals(ActionKeys.ADD_SUBFOLDER) ||
				actionId.equals(ActionKeys.DELETE)) {

				return false;
			}

			return DLPermission.contains(permissionChecker, groupId, actionId);
		}

		Folder folder = DLAppLocalServiceUtil.getFolder(folderId);

		return folder.containsPermission(permissionChecker, actionId);
	}

	private static boolean _hasPermission(
		PermissionChecker permissionChecker, DLFolder dlFolder,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				dlFolder.getCompanyId(), DLFolder.class.getName(),
				dlFolder.getFolderId(), dlFolder.getUserId(), actionId) ||
			permissionChecker.hasPermission(
				dlFolder.getGroupId(), DLFolder.class.getName(),
				dlFolder.getFolderId(), actionId)) {

			return true;
		}

		return false;
	}

}