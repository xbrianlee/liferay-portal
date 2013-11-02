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

package com.liferay.portlet.bookmarks.trash;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.trash.BaseTrashHandler;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.ContainerModel;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.service.BookmarksEntryLocalServiceUtil;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the trash handler for bookmarks folder entity.
 *
 * @author Eudaldo Alonso
 */
public abstract class BookmarksBaseTrashHandler extends BaseTrashHandler {

	@Override
	public ContainerModel getContainerModel(long containerModelId)
		throws PortalException, SystemException {

		return BookmarksFolderLocalServiceUtil.getFolder(containerModelId);
	}

	@Override
	public String getContainerModelClassName() {
		return BookmarksFolder.class.getName();
	}

	@Override
	public String getContainerModelName() {
		return "folder";
	}

	@Override
	public List<ContainerModel> getContainerModels(
			long classPK, long parentContainerModelId, int start, int end)
		throws PortalException, SystemException {

		List<BookmarksFolder> folders =
			BookmarksFolderLocalServiceUtil.getFolders(
				getGroupId(classPK), parentContainerModelId, start, end);

		List<ContainerModel> containerModels = new ArrayList<ContainerModel>(
			folders.size());

		for (BookmarksFolder curFolder : folders) {
			containerModels.add(curFolder);
		}

		return containerModels;
	}

	@Override
	public int getContainerModelsCount(
			long classPK, long parentContainerModelId)
		throws PortalException, SystemException {

		return BookmarksFolderLocalServiceUtil.getFoldersCount(
			getGroupId(classPK), parentContainerModelId);
	}

	@Override
	public List<ContainerModel> getParentContainerModels(long classPK)
		throws PortalException, SystemException {

		List<ContainerModel> containerModels = new ArrayList<ContainerModel>();

		ContainerModel containerModel = getParentContainerModel(classPK);

		if (containerModel == null) {
			return containerModels;
		}

		containerModels.add(containerModel);

		while (containerModel.getParentContainerModelId() > 0) {
			containerModel = getContainerModel(
				containerModel.getParentContainerModelId());

			if (containerModel == null) {
				break;
			}

			containerModels.add(containerModel);
		}

		return containerModels;
	}

	@Override
	public String getRootContainerModelName() {
		return "home";
	}

	@Override
	public String getTrashContainedModelName() {
		return "bookmarks";
	}

	@Override
	public int getTrashContainedModelsCount(long classPK)
		throws PortalException, SystemException {

		BookmarksFolder folder = BookmarksFolderLocalServiceUtil.getFolder(
			classPK);

		return BookmarksEntryLocalServiceUtil.getEntriesCount(
			folder.getGroupId(), classPK, WorkflowConstants.STATUS_IN_TRASH);
	}

	@Override
	public List<TrashRenderer> getTrashContainedModelTrashRenderers(
			long classPK, int start, int end)
		throws PortalException, SystemException {

		List<TrashRenderer> trashRenderers = new ArrayList<TrashRenderer>();

		BookmarksFolder folder = BookmarksFolderLocalServiceUtil.getFolder(
			classPK);

		List<BookmarksEntry> entries =
			BookmarksEntryLocalServiceUtil.getEntries(
				folder.getGroupId(), classPK, WorkflowConstants.STATUS_IN_TRASH,
				start, end);

		for (BookmarksEntry entry : entries) {
			TrashHandler trashHandler =
				TrashHandlerRegistryUtil.getTrashHandler(
					BookmarksEntry.class.getName());

			TrashRenderer trashRenderer = trashHandler.getTrashRenderer(
				entry.getEntryId());

			trashRenderers.add(trashRenderer);
		}

		return trashRenderers;
	}

	@Override
	public String getTrashContainerModelName() {
		return "folders";
	}

	@Override
	public int getTrashContainerModelsCount(long classPK)
		throws PortalException, SystemException {

		BookmarksFolder folder = BookmarksFolderLocalServiceUtil.getFolder(
			classPK);

		return BookmarksFolderLocalServiceUtil.getFoldersCount(
			folder.getGroupId(), classPK, WorkflowConstants.STATUS_IN_TRASH);
	}

	@Override
	public List<TrashRenderer> getTrashContainerModelTrashRenderers(
			long classPK, int start, int end)
		throws PortalException, SystemException {

		List<TrashRenderer> trashRenderers = new ArrayList<TrashRenderer>();

		BookmarksFolder folder = BookmarksFolderLocalServiceUtil.getFolder(
			classPK);

		List<BookmarksFolder> folders =
			BookmarksFolderLocalServiceUtil.getFolders(
				folder.getGroupId(), classPK, WorkflowConstants.STATUS_IN_TRASH,
				start, end);

		for (BookmarksFolder curFolder : folders) {
			TrashHandler trashHandler =
				TrashHandlerRegistryUtil.getTrashHandler(
					BookmarksFolder.class.getName());

			TrashRenderer trashRenderer = trashHandler.getTrashRenderer(
				curFolder.getPrimaryKey());

			trashRenderers.add(trashRenderer);
		}

		return trashRenderers;
	}

	@Override
	public boolean isMovable() {
		return true;
	}

	protected abstract long getGroupId(long classPK)
		throws PortalException, SystemException;

}