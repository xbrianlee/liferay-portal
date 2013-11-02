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

<%@ include file="/html/portlet/layouts_admin/init.jsp" %>

<%
Group liveGroup = (Group)request.getAttribute("edit_pages.jsp-liveGroup");
boolean privateLayout = ((Boolean)request.getAttribute("edit_pages.jsp-privateLayout")).booleanValue();
UnicodeProperties groupTypeSettings = (UnicodeProperties)request.getAttribute("edit_pages.jsp-groupTypeSettings");

Group guestGroup = GroupLocalServiceUtil.getGroup(company.getCompanyId(), GroupConstants.GUEST);

boolean mergeGuestPublicPages = PropertiesParamUtil.getBoolean(groupTypeSettings, request, "mergeGuestPublicPages");
%>

<liferay-ui:error-marker key="errorSection" value="advanced" />

<h3><liferay-ui:message key="advanced" /></h3>

<aui:fieldset>
	<c:choose>
		<c:when test="<%= !privateLayout && (liveGroup.getGroupId() != guestGroup.getGroupId()) %>">

			<%
			String taglibLabel = LanguageUtil.format(pageContext, "merge-x-public-pages", HtmlUtil.escape(guestGroup.getDescriptiveName(locale)));
			String taglibHelpMessage = LanguageUtil.format(pageContext, "you-can-configure-the-top-level-pages-of-this-public-site-to-merge-with-the-top-level-pages-of-the-public-x-site", HtmlUtil.escape(guestGroup.getDescriptiveName(locale)));
			%>

			<aui:input helpMessage="<%= taglibHelpMessage %>" label="<%= taglibLabel %>" name="mergeGuestPublicPages" type="checkbox" value="<%= mergeGuestPublicPages %>" />
		</c:when>
		<c:otherwise>
			<div class="alert alert-info">
				<liferay-ui:message key="there-are-no-available-advanced-settings-for-these-pages" />
			</div>
		</c:otherwise>
	</c:choose>
</aui:fieldset>