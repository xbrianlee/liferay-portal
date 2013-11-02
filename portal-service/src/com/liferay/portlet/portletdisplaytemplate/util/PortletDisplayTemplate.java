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

package com.liferay.portlet.portletdisplaytemplate.util;

import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.PageContext;

/**
 * @author Eduardo Garcia
 */
public interface PortletDisplayTemplate {

	public static final String DISPLAY_STYLE_PREFIX = "ddmTemplate_";

	public DDMTemplate fetchDDMTemplate(long groupId, String displayStyle);

	public long getDDMTemplateGroupId(long groupId);

	public String getDDMTemplateUuid(String displayStyle);

	public long getPortletDisplayTemplateDDMTemplateId(
		long groupId, String displayStyle);

	public List<TemplateHandler> getPortletDisplayTemplateHandlers();

	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
		String language);

	public String renderDDMTemplate(
			PageContext pageContext, long ddmTemplateId, List<?> entries)
		throws Exception;

	public String renderDDMTemplate(
			PageContext pageContext, long ddmTemplateId, List<?> entries,
			Map<String, Object> contextObjects)
		throws Exception;

}