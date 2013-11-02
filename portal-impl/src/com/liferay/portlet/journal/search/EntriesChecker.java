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

package com.liferay.portlet.journal.search;

import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.journal.NoSuchArticleException;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalFolder;
import com.liferay.portlet.journal.service.JournalArticleServiceUtil;
import com.liferay.portlet.journal.service.JournalFolderServiceUtil;
import com.liferay.portlet.journal.service.permission.JournalArticlePermission;
import com.liferay.portlet.journal.service.permission.JournalFolderPermission;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sergio González
 */
public class EntriesChecker extends RowChecker {

	public EntriesChecker(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		super(liferayPortletResponse);

		_liferayPortletResponse = liferayPortletResponse;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		_permissionChecker = themeDisplay.getPermissionChecker();
	}

	@Override
	public String getAllRowsCheckBox() {
		return null;
	}

	@Override
	public String getRowCheckBox(
		HttpServletRequest request, boolean checked, boolean disabled,
		String primaryKey) {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		JournalArticle article = null;
		JournalFolder folder = null;

		String articleId = GetterUtil.getString(primaryKey);

		try {
			article = JournalArticleServiceUtil.getArticle(
				themeDisplay.getScopeGroupId(), articleId);
		}
		catch (Exception e1) {
			if (e1 instanceof NoSuchArticleException) {
				try {
					long folderId = GetterUtil.getLong(primaryKey);

					folder = JournalFolderServiceUtil.getFolder(folderId);
				}
				catch (Exception e2) {
					return StringPool.BLANK;
				}
			}
		}

		boolean showInput = false;

		String name = null;

		if (article != null) {
			name = JournalArticle.class.getSimpleName();

			try {
				if (JournalArticlePermission.contains(
						_permissionChecker, article, ActionKeys.DELETE) ||
					JournalArticlePermission.contains(
						_permissionChecker, article, ActionKeys.EXPIRE) ||
					JournalArticlePermission.contains(
						_permissionChecker, article, ActionKeys.UPDATE)) {

					showInput = true;
				}
			}
			catch (Exception e) {
			}
		}
		else if (folder != null) {
			name = JournalFolder.class.getSimpleName();

			try {
				if (JournalFolderPermission.contains(
						_permissionChecker, folder, ActionKeys.DELETE)) {

					showInput = true;
				}
			}
			catch (Exception e) {
			}
		}

		if (!showInput) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler();

		sb.append("['");
		sb.append(_liferayPortletResponse.getNamespace());
		sb.append(RowChecker.ROW_IDS);
		sb.append(JournalFolder.class.getSimpleName());
		sb.append("Checkbox', '");
		sb.append(_liferayPortletResponse.getNamespace());
		sb.append(RowChecker.ROW_IDS);
		sb.append(JournalArticle.class.getSimpleName());
		sb.append("Checkbox']");

		String checkBoxRowIds = sb.toString();

		return getRowCheckBox(
			checked, disabled,
			_liferayPortletResponse.getNamespace() + RowChecker.ROW_IDS +
				name + "Checkbox",
			primaryKey, checkBoxRowIds, "'#" + getAllRowIds() + "Checkbox'",
			StringPool.BLANK);
	}

	private LiferayPortletResponse _liferayPortletResponse;
	private PermissionChecker _permissionChecker;

}