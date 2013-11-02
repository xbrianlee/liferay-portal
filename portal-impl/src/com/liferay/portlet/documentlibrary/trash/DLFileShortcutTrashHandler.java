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

package com.liferay.portlet.documentlibrary.trash;

import com.liferay.portal.InvalidRepositoryException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.trash.TrashActionKeys;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.model.ContainerModel;
import com.liferay.portal.model.TrashedModel;
import com.liferay.portal.repository.liferayrepository.LiferayRepository;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.RepositoryServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.service.DLAppHelperLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileShortcutLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.permission.DLFileShortcutPermission;
import com.liferay.portlet.documentlibrary.service.permission.DLFolderPermission;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import com.liferay.portlet.trash.model.TrashEntry;

import javax.portlet.PortletRequest;

/**
 * Implements trash handling for the file shortcut entity.
 *
 * @author Zsolt Berentey
 */
public class DLFileShortcutTrashHandler extends DLBaseTrashHandler {

	@Override
	public void deleteTrashEntry(long classPK)
		throws PortalException, SystemException {

		DLAppLocalServiceUtil.deleteFileShortcut(classPK);
	}

	@Override
	public String getClassName() {
		return DLFileShortcut.class.getName();
	}

	@Override
	public ContainerModel getParentContainerModel(long classPK)
		throws PortalException, SystemException {

		DLFileShortcut dlFileShortcut = getDLFileShortcut(classPK);

		long parentFolderId = dlFileShortcut.getFolderId();

		if (parentFolderId <= 0) {
			return null;
		}

		return getContainerModel(parentFolderId);
	}

	@Override
	public ContainerModel getParentContainerModel(TrashedModel trashedModel)
		throws PortalException, SystemException {

		DLFileShortcut fileShortcut = (DLFileShortcut)trashedModel;

		return getContainerModel(fileShortcut.getFolderId());
	}

	@Override
	public String getRestoreContainedModelLink(
			PortletRequest portletRequest, long classPK)
		throws PortalException, SystemException {

		DLFileShortcut dlFileShortcut = getDLFileShortcut(classPK);

		return DLUtil.getDLFileEntryControlPanelLink(
			portletRequest, dlFileShortcut.getToFileEntryId());
	}

	@Override
	public String getRestoreContainerModelLink(
			PortletRequest portletRequest, long classPK)
		throws PortalException, SystemException {

		DLFileShortcut fileShortcut = getDLFileShortcut(classPK);

		return DLUtil.getDLFolderControlPanelLink(
			portletRequest, fileShortcut.getFolderId());
	}

	@Override
	public String getRestoreMessage(PortletRequest portletRequest, long classPK)
		throws PortalException, SystemException {

		DLFileShortcut fileShortcut = getDLFileShortcut(classPK);

		return DLUtil.getAbsolutePath(
			portletRequest, fileShortcut.getFolderId());
	}

	@Override
	public TrashEntry getTrashEntry(long classPK)
		throws PortalException, SystemException {

		DLFileShortcut fileShortcut = getDLFileShortcut(classPK);

		return fileShortcut.getTrashEntry();
	}

	@Override
	public TrashRenderer getTrashRenderer(long classPK)
		throws PortalException, SystemException {

		DLFileShortcut fileShortcut = getDLFileShortcut(classPK);

		return new DLFileShortcutTrashRenderer(fileShortcut);
	}

	@Override
	public boolean hasTrashPermission(
			PermissionChecker permissionChecker, long groupId, long classPK,
			String trashActionId)
		throws PortalException, SystemException {

		if (trashActionId.equals(TrashActionKeys.MOVE)) {
			return DLFolderPermission.contains(
				permissionChecker, groupId, classPK, ActionKeys.ADD_SHORTCUT);
		}

		return super.hasTrashPermission(
			permissionChecker, groupId, classPK, trashActionId);
	}

	@Override
	public boolean isInTrash(long classPK)
		throws PortalException, SystemException {

		DLFileShortcut fileShortcut = getDLFileShortcut(classPK);

		return fileShortcut.isInTrash();
	}

	@Override
	public boolean isInTrashContainer(long classPK)
		throws PortalException, SystemException {

		DLFileShortcut fileShortcut = getDLFileShortcut(classPK);

		return fileShortcut.isInTrashContainer();
	}

	@Override
	public boolean isRestorable(long classPK)
		throws PortalException, SystemException {

		DLFileShortcut dlFileShortcut = getDLFileShortcut(classPK);

		try {
			dlFileShortcut.getFolder();
		}
		catch (NoSuchFolderException nsfe) {
			return false;
		}

		return !dlFileShortcut.isInTrashContainer();
	}

	@Override
	public void moveEntry(
			long userId, long classPK, long containerModelId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DLFileShortcut dlFileShortcut = getDLFileShortcut(classPK);

		DLAppLocalServiceUtil.updateFileShortcut(
			userId, classPK, containerModelId,
			dlFileShortcut.getToFileEntryId(), serviceContext);
	}

	@Override
	public void moveTrashEntry(
			long userId, long classPK, long containerModelId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DLAppHelperLocalServiceUtil.moveFileShortcutFromTrash(
			userId, getDLFileShortcut(classPK), containerModelId,
			serviceContext);
	}

	@Override
	public void restoreTrashEntry(long userId, long classPK)
		throws PortalException, SystemException {

		DLAppHelperLocalServiceUtil.restoreFileShortcutFromTrash(
			userId, getDLFileShortcut(classPK));
	}

	protected DLFileShortcut getDLFileShortcut(long classPK)
		throws PortalException, SystemException {

		return DLFileShortcutLocalServiceUtil.getDLFileShortcut(classPK);
	}

	@Override
	protected Repository getRepository(long classPK)
		throws PortalException, SystemException {

		DLFileShortcut dlFileShortcut = getDLFileShortcut(classPK);

		Repository repository = RepositoryServiceUtil.getRepositoryImpl(
			0, dlFileShortcut.getToFileEntryId(), 0);

		if (!(repository instanceof LiferayRepository)) {
			throw new InvalidRepositoryException(
				"Repository " + repository.getRepositoryId() +
					" does not support trash operations");
		}

		return repository;
	}

	@Override
	protected boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws PortalException, SystemException {

		DLFileShortcut dlFileShortcut = getDLFileShortcut(classPK);

		if (dlFileShortcut.isInHiddenFolder() &&
			actionId.equals(ActionKeys.VIEW)) {

			return false;
		}

		return DLFileShortcutPermission.contains(
			permissionChecker, classPK, actionId);
	}

}