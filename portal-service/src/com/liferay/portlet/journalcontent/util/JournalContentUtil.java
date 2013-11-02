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

package com.liferay.portlet.journalcontent.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.journal.model.JournalArticleDisplay;

/**
 * @author Raymond Augé
 */
public class JournalContentUtil {

	public static void clearCache() {
		getJournalContent().clearCache();
	}

	public static void clearCache(
		long groupId, String articleId, String ddmTemplateKey) {

		getJournalContent().clearCache(groupId, articleId, ddmTemplateKey);
	}

	public static String getContent(
		long groupId, String articleId, String viewMode, String languageId,
		String xmlRequest) {

		return getJournalContent().getContent(
			groupId, articleId, viewMode, languageId, xmlRequest);
	}

	public static String getContent(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, String xmlRequest) {

		return getJournalContent().getContent(
			groupId, articleId, ddmTemplateKey, viewMode, languageId,
			xmlRequest);
	}

	public static String getContent(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, ThemeDisplay themeDisplay) {

		return getJournalContent().getContent(
			groupId, articleId, ddmTemplateKey, viewMode, languageId,
			themeDisplay);
	}

	public static String getContent(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, ThemeDisplay themeDisplay, String xmlRequest) {

		return getJournalContent().getContent(
			groupId, articleId, ddmTemplateKey, viewMode, languageId,
			themeDisplay, xmlRequest);
	}

	public static String getContent(
		long groupId, String articleId, String viewMode, String languageId,
		ThemeDisplay themeDisplay) {

		return getJournalContent().getContent(
			groupId, articleId, viewMode, languageId, themeDisplay);
	}

	public static JournalArticleDisplay getDisplay(
		long groupId, String articleId, double version, String ddmTemplateKey,
		String viewMode, String languageId, ThemeDisplay themeDisplay, int page,
		String xmlRequest) {

		return getJournalContent().getDisplay(
			groupId, articleId, version, ddmTemplateKey, viewMode, languageId,
			themeDisplay, page, xmlRequest);
	}

	public static JournalArticleDisplay getDisplay(
		long groupId, String articleId, String viewMode, String languageId,
		String xmlRequest) {

		return getJournalContent().getDisplay(
			groupId, articleId, viewMode, languageId, xmlRequest);
	}

	public static JournalArticleDisplay getDisplay(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, String xmlRequest) {

		return getJournalContent().getDisplay(
			groupId, articleId, ddmTemplateKey, viewMode, languageId,
			xmlRequest);
	}

	public static JournalArticleDisplay getDisplay(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, ThemeDisplay themeDisplay) {

		return getJournalContent().getDisplay(
			groupId, articleId, ddmTemplateKey, viewMode, languageId,
			themeDisplay);
	}

	public static JournalArticleDisplay getDisplay(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, ThemeDisplay themeDisplay, int page,
		String xmlRequest) {

		return getJournalContent().getDisplay(
			groupId, articleId, ddmTemplateKey, viewMode, languageId,
			themeDisplay, page, xmlRequest);
	}

	public static JournalArticleDisplay getDisplay(
		long groupId, String articleId, String viewMode, String languageId,
		ThemeDisplay themeDisplay) {

		return getJournalContent().getDisplay(
			groupId, articleId, viewMode, languageId, themeDisplay);
	}

	public static JournalArticleDisplay getDisplay(
		long groupId, String articleId, String viewMode, String languageId,
		ThemeDisplay themeDisplay, int page) {

		return getJournalContent().getDisplay(
			groupId, articleId, viewMode, languageId, themeDisplay, page);
	}

	public static JournalContent getJournalContent() {
		PortalRuntimePermission.checkGetBeanProperty(JournalContentUtil.class);

		return _journalContent;
	}

	public void setJournalContent(JournalContent journalContent) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_journalContent = journalContent;
	}

	private static JournalContent _journalContent;

}