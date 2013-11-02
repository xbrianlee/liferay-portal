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

package com.liferay.portlet.dynamicdatamapping.util;

import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eudaldo Alonso
 */
public class DDMTemplateTestUtil {

	public static void addDynamicContentElement(
		Element dynamicElementElement, String languageId, String value) {

		Element dynamicContentElement = dynamicElementElement.addElement(
			"dynamic-content");

		dynamicContentElement.addAttribute("language-id", languageId);
		dynamicContentElement.setText(value);
	}

	public static Element addDynamicElementElement(
		Element element, String type, String name) {

		Element dynamicElementElement = element.addElement("dynamic-element");

		dynamicElementElement.addAttribute("name", name);
		dynamicElementElement.addAttribute("type", type);

		return dynamicElementElement;
	}

	public static DDMTemplate addTemplate(long structureId) throws Exception {
		return addTemplate(
			structureId, TemplateConstants.LANG_TYPE_VM, getSampleTemplateXSL(),
			LocaleUtil.getSiteDefault());
	}

	public static DDMTemplate addTemplate(
			long structureId, Locale defaultLocale)
		throws Exception {

		return addTemplate(
			structureId, TemplateConstants.LANG_TYPE_VM, getSampleTemplateXSL(),
			defaultLocale);
	}

	public static DDMTemplate addTemplate(long groupId, long structureId)
		throws Exception {

		return addTemplate(
			groupId, structureId, TemplateConstants.LANG_TYPE_VM,
			getSampleTemplateXSL(), LocaleUtil.getSiteDefault());
	}

	public static DDMTemplate addTemplate(
			long groupId, long structureId, Locale defaultLocale)
		throws Exception {

		return addTemplate(
			groupId, structureId, TemplateConstants.LANG_TYPE_VM,
			getSampleTemplateXSL(), defaultLocale);
	}

	public static DDMTemplate addTemplate(
			long groupId, long classNameId, long classPK)
		throws Exception {

		return addTemplate(
			groupId, classNameId, classPK, TemplateConstants.LANG_TYPE_VM,
			getSampleTemplateXSL(), LocaleUtil.getSiteDefault());
	}

	public static DDMTemplate addTemplate(
			long groupId, long classNameId, long classPK, String language,
			String script, Locale defaultLocale)
		throws Exception {

		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		nameMap.put(defaultLocale, "Test Template");

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		return DDMTemplateLocalServiceUtil.addTemplate(
			TestPropsValues.getUserId(), groupId, classNameId, classPK, nameMap,
			null, DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, null, language,
			script, serviceContext);
	}

	public static DDMTemplate addTemplate(
			long groupId, long structureId, String language, String script,
			Locale defaultLocale)
		throws Exception {

		return addTemplate(
			groupId, PortalUtil.getClassNameId(DDMStructure.class), structureId,
			language, script, defaultLocale);
	}

	public static DDMTemplate addTemplate(
			long structureId, String language, String script)
		throws Exception {

		return addTemplate(
			TestPropsValues.getGroupId(), structureId, language, script,
			LocaleUtil.getSiteDefault());
	}

	public static DDMTemplate addTemplate(
			long structureId, String language, String script,
			Locale defaultLocale)
		throws Exception {

		return addTemplate(
			TestPropsValues.getGroupId(), structureId, language, script,
			defaultLocale);
	}

	public static Document createDocument(
		String availableLocales, String defaultLocale) {

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("root");

		rootElement.addAttribute("available-locales", availableLocales);
		rootElement.addAttribute("default-locale", defaultLocale);
		rootElement.addElement("request");

		return document;
	}

	public static String getSampleTemplateXSL() {
		return "$name.getData()";
	}

}