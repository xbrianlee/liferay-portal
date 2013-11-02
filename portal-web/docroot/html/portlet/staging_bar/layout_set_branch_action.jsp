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

<%@ include file="/html/portlet/staging_bar/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

LayoutSetBranch layoutSetBranch = (LayoutSetBranch)row.getObject();

long currentLayoutSetBranchId = GetterUtil.getLong((String)request.getAttribute("view_layout_set_branches.jsp-currentLayoutSetBranchId"));
%>

<liferay-ui:icon-menu>
	<c:if test="<%= LayoutSetBranchPermissionUtil.contains(permissionChecker, layoutSetBranch, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
			<portlet:param name="struts_action" value="/staging_bar/edit_layout_set_branch" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(layoutSetBranch.getGroupId()) %>" />
			<portlet:param name="layoutSetBranchId" value="<%= String.valueOf(layoutSetBranch.getLayoutSetBranchId()) %>" />
		</portlet:renderURL>

		<%
		String taglibURL = "javascript:Liferay.StagingBar.updateBranch({uri: '" + HtmlUtil.escapeJS(editURL) + "', dialogTitle: '" + UnicodeLanguageUtil.get(pageContext, "update-site-pages-variation") + "'});";
		%>

		<liferay-ui:icon
			image="edit"
			url="<%= taglibURL %>"
		/>
	</c:if>

	<c:if test="<%= LayoutSetBranchPermissionUtil.contains(permissionChecker, layoutSetBranch, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= LayoutSetBranch.class.getName() %>"
			modelResourceDescription="<%= layoutSetBranch.getName() %>"
			resourcePrimKey="<%= String.valueOf(layoutSetBranch.getLayoutSetBranchId()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			image="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= !layoutSetBranch.isMaster() && LayoutSetBranchPermissionUtil.contains(permissionChecker, layoutSetBranch, ActionKeys.DELETE) %>">
		<portlet:actionURL var="deleteURL">
			<portlet:param name="struts_action" value="/staging_bar/edit_layout_set_branch" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(layoutSetBranch.getGroupId()) %>" />
			<portlet:param name="layoutSetBranchId" value="<%= String.valueOf(layoutSetBranch.getLayoutSetBranchId()) %>" />
			<portlet:param name="currentLayoutSetBranchId" value="<%= String.valueOf(currentLayoutSetBranchId) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>

	<c:if test="<%= LayoutSetBranchPermissionUtil.contains(permissionChecker, layoutSetBranch, ActionKeys.MERGE) %>">
		<portlet:renderURL var="mergeURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
			<portlet:param name="struts_action" value="/staging_bar/merge_layout_set_branch" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(layoutSetBranch.getGroupId()) %>" />
			<portlet:param name="privateLayout" value="<%= String.valueOf(layoutSetBranch.isPrivateLayout()) %>" />
			<portlet:param name="layoutSetBranchId" value="<%= String.valueOf(layoutSetBranch.getLayoutSetBranchId()) %>" />
		</portlet:renderURL>

		<%
		String taglibURL = "javascript:Liferay.StagingBar.mergeBranch({uri: '" + HtmlUtil.escapeJS(mergeURL) + "', dialogTitle: '" + UnicodeLanguageUtil.get(pageContext, "merge-site-pages-variation") + "'});";
		%>

		<liferay-ui:icon
			image="copy"
			message="merge"
			url="<%= taglibURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>