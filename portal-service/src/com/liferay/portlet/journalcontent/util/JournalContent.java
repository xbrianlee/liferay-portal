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

import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.journal.model.JournalArticleDisplay;

/**
 * @author Raymond Augé
 */
public interface JournalContent {

	public static final String ARTICLE_SEPARATOR = "_ARTICLE_";

	public static final String LANGUAGE_SEPARATOR = "_LANGUAGE_";

	public static final String LAYOUT_SET_SEPARATOR = "_LAYOUT_SET_";

	public static final String PAGE_SEPARATOR = "_PAGE_";

	public static final String SECURE_SEPARATOR = "_SECURE_";

	public static final String TEMPLATE_SEPARATOR = "_TEMPLATE_";

	public static final String VERSION_SEPARATOR = "_VERSION_";

	public static final String VIEW_MODE_SEPARATOR = "_VIEW_MODE_";

	public void clearCache();

	public void clearCache(
		long groupId, String articleId, String ddmTemplateKey);

	public String getContent(
		long groupId, String articleId, String viewMode, String languageId,
		String xmlRequest);

	public String getContent(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, String xmlRequest);

	public String getContent(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, ThemeDisplay themeDisplay);

	public String getContent(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, ThemeDisplay themeDisplay, String xmlRequest);

	public String getContent(
		long groupId, String articleId, String viewMode, String languageId,
		ThemeDisplay themeDisplay);

	public JournalArticleDisplay getDisplay(
		long groupId, String articleId, double version, String ddmTemplateKey,
		String viewMode, String languageId, ThemeDisplay themeDisplay, int page,
		String xmlRequest);

	public JournalArticleDisplay getDisplay(
		long groupId, String articleId, String viewMode, String languageId,
		String xmlRequest);

	public JournalArticleDisplay getDisplay(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, String xmlRequest);

	public JournalArticleDisplay getDisplay(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, ThemeDisplay themeDisplay);

	public JournalArticleDisplay getDisplay(
		long groupId, String articleId, String ddmTemplateKey, String viewMode,
		String languageId, ThemeDisplay themeDisplay, int page,
		String xmlRequest);

	public JournalArticleDisplay getDisplay(
		long groupId, String articleId, String viewMode, String languageId,
		ThemeDisplay themeDisplay);

	public JournalArticleDisplay getDisplay(
		long groupId, String articleId, String viewMode, String languageId,
		ThemeDisplay themeDisplay, int page);

}