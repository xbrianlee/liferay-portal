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

LayoutRevision rootLayoutRevision = (LayoutRevision)row.getObject();

LayoutBranch layoutBranch = rootLayoutRevision.getLayoutBranch();

long currentLayoutBranchId = GetterUtil.getLong((String)request.getAttribute("view_layout_branches.jsp-currentLayoutBranchId"));
%>

<liferay-ui:icon-menu>
	<c:if test="<%= LayoutBranchPermissionUtil.contains(permissionChecker, layoutBranch, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
			<portlet:param name="struts_action" value="/staging_bar/edit_layout_branch" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(layoutBranch.getGroupId()) %>" />
			<portlet:param name="layoutBranchId" value="<%= String.valueOf(layoutBranch.getLayoutBranchId()) %>" />
		</portlet:renderURL>

		<%
		String taglibURL = "javascript:Liferay.StagingBar.updateBranch({uri: '" + HtmlUtil.escapeJS(editURL) + "', dialogTitle: '" + UnicodeLanguageUtil.get(pageContext, "update-page-variation") + "'});";
		%>

		<liferay-ui:icon
			image="edit"
			url="<%= taglibURL %>"
		/>

		<c:if test="<%= !rootLayoutRevision.isPending() && !layoutBranch.isMaster() && !rootLayoutRevision.isHead() && LayoutBranchPermissionUtil.contains(permissionChecker, layoutBranch, ActionKeys.DELETE) %>">
			<portlet:actionURL var="deleteURL">
				<portlet:param name="struts_action" value="/staging_bar/edit_layout_branch" />
				<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="groupId" value="<%= String.valueOf(layoutBranch.getGroupId()) %>" />
				<portlet:param name="layoutBranchId" value="<%= String.valueOf(layoutBranch.getLayoutBranchId()) %>" />
				<portlet:param name="currentLayoutBranchId" value="<%= String.valueOf(currentLayoutBranchId) %>" />
			</portlet:actionURL>

			<liferay-ui:icon-delete
				url="<%= deleteURL %>"
			/>
		</c:if>
	</c:if>
</liferay-ui:icon-menu>