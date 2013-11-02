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

<%@ include file="/html/portal/layout/edit/init.jsp" %>

<%
Boolean showCopyPortlets = ParamUtil.getBoolean(request, "showCopyPortlets");
Boolean showLayoutTemplates = ParamUtil.getBoolean(request, "showLayoutTemplates", true);
%>

<div class="<%= showCopyPortlets ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />copyPortletsFromPage">
	<p>
		<c:if test="<%= selLayout != null %>">
			<liferay-ui:message arguments="<%= HtmlUtil.escape(selLayout.getName(locale)) %>" key="the-applications-in-page-x-will-be-replaced-with-the-ones-in-the-page-you-select-below" />
		</c:if>
	</p>

	<liferay-util:include page="/html/portal/layout/edit/portlet_applications.jsp" />

	<aui:button-row>
		<aui:button name="copySubmitButton" value="copy" />
	</aui:button-row>
</div>

<div class="<%= showLayoutTemplates ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />layoutTemplates">

	<%
	LayoutTypePortlet selLayoutTypePortlet = null;

	Theme selTheme = layout.getTheme();

	if (selLayout != null) {
		selLayoutTypePortlet = (LayoutTypePortlet)selLayout.getLayoutType();

		selTheme = selLayout.getTheme();
	}

	String layoutTemplateId = StringPool.BLANK;

	if (selLayoutTypePortlet != null) {
		layoutTemplateId = selLayoutTypePortlet.getLayoutTemplateId();
	}

	String layoutTemplateIdPrefix = StringPool.BLANK;

	List<LayoutTemplate> layoutTemplates = LayoutTemplateLocalServiceUtil.getLayoutTemplates(selTheme.getThemeId());
	%>

	<%@ include file="/html/portlet/layouts_admin/layout/layout_templates_list.jspf" %>
</div>