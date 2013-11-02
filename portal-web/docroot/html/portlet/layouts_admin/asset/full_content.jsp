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
LayoutRevision layoutRevision = (LayoutRevision)request.getAttribute(WebKeys.LAYOUT_REVISION);

LayoutBranch layoutBranch = layoutRevision.getLayoutBranch();

LayoutSetBranch layoutSetBranch = LayoutSetBranchLocalServiceUtil.getLayoutSetBranch(layoutRevision.getLayoutSetBranchId());

Layout targetLayout = LayoutLocalServiceUtil.getLayout(layoutRevision.getPlid());

String layoutFriendlyURL = PortalUtil.getLayoutFriendlyURL(targetLayout, themeDisplay);
%>

<strong><liferay-ui:message key="page" />:</strong> <a href="<%= layoutFriendlyURL + "?layoutSetBranchId=" + layoutRevision.getLayoutSetBranchId() + "&layoutRevisionId=" + layoutRevision.getLayoutRevisionId() %>"><%= targetLayout.getHTMLTitle(locale) %></a><br />

<strong><liferay-ui:message key="site-pages-variation" />:</strong> <%= LanguageUtil.get(locale, HtmlUtil.escape(layoutSetBranch.getName())) %><br />

<strong><liferay-ui:message key="page-variation" />:</strong> <%= LanguageUtil.get(locale, HtmlUtil.escape(layoutBranch.getName())) %><br />

<strong><liferay-ui:message key="revision-id" />:</strong> <%= layoutRevision.getLayoutRevisionId() %>