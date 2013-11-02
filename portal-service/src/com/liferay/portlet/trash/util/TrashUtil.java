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

package com.liferay.portlet.trash.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Group;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.trash.model.TrashEntry;

import java.util.Date;
import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 */
public class TrashUtil {

	public static void addBaseModelBreadcrumbEntries(
			HttpServletRequest request, String className, long classPK,
			PortletURL containerModelURL)
		throws PortalException, SystemException {

		getTrash().addBaseModelBreadcrumbEntries(
			request, className, classPK, containerModelURL);
	}

	public static void addContainerModelBreadcrumbEntries(
			HttpServletRequest request, String className, long classPK,
			PortletURL containerModelURL)
		throws PortalException, SystemException {

		getTrash().addContainerModelBreadcrumbEntries(
			request, className, classPK, containerModelURL);
	}

	public static void deleteEntriesAttachments(
			long companyId, long repositoryId, Date date,
			String[] attachmentFileNames)
		throws PortalException, SystemException {

		getTrash().deleteEntriesAttachments(
			companyId, repositoryId, date, attachmentFileNames);
	}

	public static List<TrashEntry> getEntries(Hits hits)
		throws PortalException, SystemException {

		return getTrash().getEntries(hits);
	}

	public static OrderByComparator getEntryOrderByComparator(
		String orderByCol, String orderByType) {

		return getTrash().getEntryOrderByComparator(orderByCol, orderByType);
	}

	public static int getMaxAge(Group group)
		throws PortalException, SystemException {

		return getTrash().getMaxAge(group);
	}

	public static String getNewName(String oldName, String token) {
		return getTrash().getNewName(oldName, token);
	}

	public static String getNewName(
			ThemeDisplay themeDisplay, String className, long classPK,
			String oldName)
		throws PortalException, SystemException {

		return getTrash().getNewName(themeDisplay, className, classPK, oldName);
	}

	public static String getOriginalTitle(String title) {
		return getTrash().getOriginalTitle(title);
	}

	public static Trash getTrash() {
		PortalRuntimePermission.checkGetBeanProperty(TrashUtil.class);

		return _trash;
	}

	public static String getTrashTime(String title, String separator) {
		return getTrash().getTrashTime(title, separator);
	}

	public static String getTrashTitle(long trashEntryId) {
		return getTrash().getTrashTitle(trashEntryId);
	}

	public static PortletURL getViewContentURL(
			HttpServletRequest request, String className, long classPK)
		throws PortalException, SystemException {

		return getTrash().getViewContentURL(request, className, classPK);
	}

	public static boolean isInTrash(String className, long classPK)
		throws PortalException, SystemException {

		return getTrash().isInTrash(className, classPK);
	}

	public static boolean isTrashEnabled(long groupId)
		throws PortalException, SystemException {

		return getTrash().isTrashEnabled(groupId);
	}

	public void setTrash(Trash trash) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_trash = trash;
	}

	private static Trash _trash;

}