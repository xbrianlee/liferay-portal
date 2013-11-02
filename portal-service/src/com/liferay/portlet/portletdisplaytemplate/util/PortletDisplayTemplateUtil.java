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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.PageContext;

/**
 * @author Eduardo Garcia
 */
public class PortletDisplayTemplateUtil {

	public static DDMTemplate fetchDDMTemplate(
		long groupId, String displayStyle) {

		return getPortletDisplayTemplate().fetchDDMTemplate(
			groupId, displayStyle);
	}

	public static long getDDMTemplateGroupId(long groupId) {
		return getPortletDisplayTemplate().getDDMTemplateGroupId(groupId);
	}

	public static String getDDMTemplateUuid(String displayStyle) {
		return getPortletDisplayTemplate().getDDMTemplateUuid(displayStyle);
	}

	public static PortletDisplayTemplate getPortletDisplayTemplate() {
		PortalRuntimePermission.checkGetBeanProperty(
			PortletDisplayTemplate.class);

		return _portletDisplayTemplate;
	}

	public static long getPortletDisplayTemplateDDMTemplateId(
		long groupId, String displayStyle) {

		return
			getPortletDisplayTemplate().getPortletDisplayTemplateDDMTemplateId(
				groupId, displayStyle);
	}

	public static List<TemplateHandler> getPortletDisplayTemplateHandlers() {
		return getPortletDisplayTemplate().getPortletDisplayTemplateHandlers();
	}

	public static Map<String, TemplateVariableGroup>
		getTemplateVariableGroups(String language) {

		return getPortletDisplayTemplate().getTemplateVariableGroups(language);
	}

	public static String renderDDMTemplate(
			PageContext pageContext, long ddmTemplateId, List<?> entries)
		throws Exception {

		return getPortletDisplayTemplate().renderDDMTemplate(
			pageContext, ddmTemplateId, entries);
	}

	public static String renderDDMTemplate(
			PageContext pageContext, long ddmTemplateId, List<?> entries,
			Map<String, Object> contextObjects)
		throws Exception {

		return getPortletDisplayTemplate().renderDDMTemplate(
			pageContext, ddmTemplateId, entries, contextObjects);
	}

	public void setPortletDisplayTemplate(
		PortletDisplayTemplate portletDisplayTemplate) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_portletDisplayTemplate = portletDisplayTemplate;
	}

	private static PortletDisplayTemplate _portletDisplayTemplate;

}