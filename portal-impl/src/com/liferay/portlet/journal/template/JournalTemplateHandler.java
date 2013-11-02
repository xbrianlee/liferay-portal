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

package com.liferay.portlet.journal.template;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.template.TemplateVariableCodeHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalService;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureService;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalService;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateService;
import com.liferay.portlet.dynamicdatamapping.template.BaseDDMTemplateHandler;
import com.liferay.portlet.dynamicdatamapping.template.DDMTemplateVariableCodeHandler;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalService;
import com.liferay.portlet.journal.service.JournalArticleService;

import java.util.Locale;
import java.util.Map;

/**
 * @author Jorge Ferrer
 */
public class JournalTemplateHandler extends BaseDDMTemplateHandler {

	@Override
	public String getClassName() {
		return JournalArticle.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		String portletTitle = PortalUtil.getPortletTitle(
			PortletKeys.JOURNAL, locale);

		return portletTitle.concat(StringPool.SPACE).concat(
			LanguageUtil.get(locale, "template"));
	}

	@Override
	public String getResourceName() {
		return "com.liferay.portlet.journal.template";
	}

	@Override
	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
			long classPK, String language, Locale locale)
		throws Exception {

		Map<String, TemplateVariableGroup> templateVariableGroups =
			super.getTemplateVariableGroups(classPK, language, locale);

		String[] restrictedVariables = getRestrictedVariables(language);

		TemplateVariableGroup journalServicesTemplateVariableGroup =
			new TemplateVariableGroup(
				"web-content-services", restrictedVariables);

		journalServicesTemplateVariableGroup.setAutocompleteEnabled(false);

		journalServicesTemplateVariableGroup.addServiceLocatorVariables(
			JournalArticleLocalService.class, JournalArticleService.class,
			DDMStructureLocalService.class, DDMStructureService.class,
			DDMTemplateLocalService.class, DDMTemplateService.class);

		templateVariableGroups.put(
			journalServicesTemplateVariableGroup.getLabel(),
			journalServicesTemplateVariableGroup);

		return templateVariableGroups;
	}

	@Override
	protected TemplateVariableCodeHandler getTemplateVariableCodeHandler() {
		return _templateVariableCodeHandler;
	}

	@Override
	protected TemplateVariableGroup getUtilTemplateVariableGroup() {
		TemplateVariableGroup utilTemplateVariableGroup =
			super.getUtilTemplateVariableGroup();

		utilTemplateVariableGroup.addVariable(
			"xml-request", String.class, "xmlRequest");

		return utilTemplateVariableGroup;
	}

	private TemplateVariableCodeHandler _templateVariableCodeHandler =
		new DDMTemplateVariableCodeHandler(
			"com/liferay/portlet/journal/dependencies/template/");

}