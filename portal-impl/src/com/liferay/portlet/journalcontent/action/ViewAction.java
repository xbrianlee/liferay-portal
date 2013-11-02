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

package com.liferay.portlet.journalcontent.action;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleDisplay;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journalcontent.util.JournalContentUtil;
import com.liferay.util.portlet.PortletRequestUtil;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class ViewAction extends WebContentAction {

	@Override
	public ActionForward render(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long articleGroupId = ParamUtil.getLong(
			renderRequest, "articleGroupId");

		if (articleGroupId <= 0) {
			articleGroupId = GetterUtil.getLong(
				portletPreferences.getValue(
					"groupId", String.valueOf(themeDisplay.getScopeGroupId())));
		}

		String articleId = ParamUtil.getString(renderRequest, "articleId");
		String ddmTemplateKey = ParamUtil.getString(
			renderRequest, "ddmTemplateKey");

		if (Validator.isNull(articleId)) {
			articleId = GetterUtil.getString(
				portletPreferences.getValue("articleId", null));
			ddmTemplateKey = GetterUtil.getString(
				portletPreferences.getValue("ddmTemplateKey", null));
		}

		String viewMode = ParamUtil.getString(renderRequest, "viewMode");
		String languageId = LanguageUtil.getLanguageId(renderRequest);
		int page = ParamUtil.getInteger(renderRequest, "page", 1);
		String xmlRequest = PortletRequestUtil.toXML(
			renderRequest, renderResponse);

		JournalArticle article = null;
		JournalArticleDisplay articleDisplay = null;

		if ((articleGroupId > 0) && Validator.isNotNull(articleId)) {
			article = JournalArticleLocalServiceUtil.fetchLatestArticle(
				articleGroupId, articleId, WorkflowConstants.STATUS_APPROVED);

			try {
				if (article == null) {
					article = JournalArticleLocalServiceUtil.getLatestArticle(
						articleGroupId, articleId,
						WorkflowConstants.STATUS_ANY);
				}

				double version = article.getVersion();

				articleDisplay = JournalContentUtil.getDisplay(
					articleGroupId, articleId, version, ddmTemplateKey,
					viewMode, languageId, themeDisplay, page, xmlRequest);
			}
			catch (Exception e) {
				renderRequest.removeAttribute(WebKeys.JOURNAL_ARTICLE);

				articleDisplay = JournalContentUtil.getDisplay(
					articleGroupId, articleId, ddmTemplateKey, viewMode,
					languageId, themeDisplay, page, xmlRequest);
			}
		}

		if (article != null) {
			renderRequest.setAttribute(WebKeys.JOURNAL_ARTICLE, article);
		}

		if (articleDisplay != null) {
			renderRequest.setAttribute(
				WebKeys.JOURNAL_ARTICLE_DISPLAY, articleDisplay);
		}
		else {
			renderRequest.removeAttribute(WebKeys.JOURNAL_ARTICLE_DISPLAY);
		}

		return actionMapping.findForward("portlet.journal_content.view");
	}

}