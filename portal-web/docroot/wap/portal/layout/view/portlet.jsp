<%--
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
--%>

<%@ include file="/html/portal/init.jsp" %>

<liferay-portlet:runtime portletName="<%= PortletKeys.TAGS_COMPILER %>" />

<%
boolean layoutMaximized = layoutTypePortlet.hasStateMax();

if (!layoutMaximized) {
	String themeId = theme.getThemeId();

	String layoutTemplateId = layoutTypePortlet.getLayoutTemplateId();

	if (Validator.isNull(layoutTemplateId)) {
		layoutTemplateId = PropsValues.DEFAULT_LAYOUT_TEMPLATE_ID;
	}

	LayoutTemplate layoutTemplate = LayoutTemplateLocalServiceUtil.getLayoutTemplate(layoutTemplateId, false, theme.getThemeId());

	if (layoutTemplate != null) {
		themeId = layoutTemplate.getThemeId();
	}

	String velocityTemplateId = themeId + LayoutTemplateConstants.CUSTOM_SEPARATOR + layoutTypePortlet.getLayoutTemplateId();
	String velocityTemplateContent = LayoutTemplateLocalServiceUtil.getWapContent(layoutTypePortlet.getLayoutTemplateId(), false, theme.getThemeId());

	if (Validator.isNotNull(velocityTemplateContent)) {
		RuntimePageUtil.processTemplate(pageContext, new StringTemplateResource(velocityTemplateId, velocityTemplateContent));
	}
}
else {
	String velocityTemplateId = theme.getThemeId() + LayoutTemplateConstants.STANDARD_SEPARATOR + "max";
	String velocityTemplateContent = LayoutTemplateLocalServiceUtil.getWapContent("max", true, theme.getThemeId());

	if (Validator.isNotNull(velocityTemplateContent)) {
		RuntimePageUtil.processTemplate(pageContext, StringUtil.split(layoutTypePortlet.getStateMax())[0], new StringTemplateResource(velocityTemplateId, velocityTemplateContent));
	}
}
%>