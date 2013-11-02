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

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;

import java.util.Locale;
import java.util.Set;

/**
 * @author Eduardo Garcia
 */
public interface DDMDisplay {

	public String getAddStructureActionId();

	public String getAddTemplateActionId();

	public String getAvailableFields();

	public String getEditStructureDefaultValuesURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			DDMStructure structure, String redirectURL, String backURL)
		throws Exception;

	public String getEditTemplateBackURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, long classNameId,
			long classPK, String portletResource)
		throws Exception;

	public String getEditTemplateTitle(
		DDMStructure structure, DDMTemplate template, Locale locale);

	public String getEditTemplateTitle(long classNameId, Locale locale);

	public String getPortletId();

	public String getResourceName();

	public String getResourceName(long classNameId);

	public String getStorageType();

	public String getStructureName(Locale locale);

	public String getStructureType();

	public long[] getTemplateClassNameIds(long classNameId);

	public long[] getTemplateClassPKs(
			long companyId, long classNameId, long classPK)
		throws Exception;

	public long[] getTemplateGroupIds(
			ThemeDisplay themeDisplay, boolean showGlobalScope)
		throws Exception;

	public long getTemplateHandlerClassNameId(
		DDMTemplate template, long classNameId);

	public Set<String> getTemplateLanguageTypes();

	public String getTemplateMode();

	public String getTemplateType();

	public String getTemplateType(DDMTemplate template, Locale locale);

	public String getViewTemplatesBackURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, long classPK)
		throws Exception;

	public Set<String> getViewTemplatesExcludedColumnNames();

	public String getViewTemplatesTitle(
		DDMStructure structure, boolean controlPanel, Locale locale);

	public String getViewTemplatesTitle(DDMStructure structure, Locale locale);

	public boolean isShowAddStructureButton();

	public boolean isShowStructureSelector();

}