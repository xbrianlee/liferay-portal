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

package com.liferay.portal.kernel.template;

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author Jorge Ferrer
 */
public abstract class BaseTemplateHandler implements TemplateHandler {

	@Override
	public List<Element> getDefaultTemplateElements() throws Exception {
		String templatesConfigPath = getTemplatesConfigPath();

		if (Validator.isNull(templatesConfigPath)) {
			return Collections.emptyList();
		}

		Class<?> clazz = getClass();

		String xml = StringUtil.read(
			clazz.getClassLoader(), templatesConfigPath, false);

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		return rootElement.elements("template");
	}

	@Override
	public String[] getRestrictedVariables(String language) {
		if (language.equals(TemplateConstants.LANG_TYPE_FTL)) {
			return PropsUtil.getArray(
				PropsKeys.FREEMARKER_ENGINE_RESTRICTED_VARIABLES);
		}
		else if (language.equals(TemplateConstants.LANG_TYPE_VM)) {
			return PropsUtil.getArray(
				PropsKeys.VELOCITY_ENGINE_RESTRICTED_VARIABLES);
		}

		return new String[0];
	}

	@Override
	public String getTemplatesHelpPath(String language) {
		return PropsUtil.get(
			getTemplatesHelpPropertyKey(), new Filter(language));
	}

	@Override
	public String getTemplatesHelpPropertyKey() {
		return PropsKeys.PORTLET_DISPLAY_TEMPLATES_HELP;
	}

	protected String getTemplatesConfigPath() {
		return null;
	}

}