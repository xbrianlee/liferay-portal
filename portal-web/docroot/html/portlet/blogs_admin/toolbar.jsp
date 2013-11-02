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

<%@ include file="/html/portlet/blogs_admin/init.jsp" %>

<%
String toolbarItem = ParamUtil.getString(request, "toolbarItem");
%>

<aui:nav-bar>
	<aui:nav>
		<portlet:renderURL var="viewEntriesURL">
			<portlet:param name="struts_action" value="/blogs_admin/view" />
		</portlet:renderURL>

		<c:if test="<%= BlogsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ENTRY) %>">
			<portlet:renderURL var="addEntryURL">
				<portlet:param name="struts_action" value="/blogs_admin/edit_entry" />
				<portlet:param name="redirect" value="<%= viewEntriesURL %>" />
				<portlet:param name="backURL" value="<%= viewEntriesURL %>" />
			</portlet:renderURL>

			<aui:nav-item href="<%= addEntryURL %>" iconCssClass="icon-plus" label="add" selected='<%= toolbarItem.equals("add") %>' />
		</c:if>
	</aui:nav>

	<c:if test="<%= showBlogEntriesSearch %>">
		<aui:nav-bar-search cssClass="pull-right">
			<div class="form-search">
				<liferay-ui:input-search autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" id="keywords1" name="keywords" placeholder='<%= LanguageUtil.get(locale, "keywords") %>' />
			</div>
		</aui:nav-bar-search>
	</c:if>
</aui:nav-bar>