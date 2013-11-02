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
String redirect = ParamUtil.getString(request, "redirect");

LayoutBranch layoutBranch = null;

long layoutBranchId = ParamUtil.getLong(request, "layoutBranchId");

if (layoutBranchId > 0) {
	layoutBranch = LayoutBranchLocalServiceUtil.getLayoutBranch(layoutBranchId);
}

long layoutRevisionId = ParamUtil.getLong(request, "layoutRevisionId");
%>

<div class='<%= (layoutBranch != null) ? StringPool.BLANK : "hide" %>' data-namespace="<portlet:namespace />" id="<portlet:namespace /><%= layoutBranch != null ? "updateBranch" : "addBranch" %>">
	<aui:model-context bean="<%= layoutBranch %>" model="<%= LayoutBranch.class %>" />

	<portlet:actionURL var="editLayoutBranchURL">
		<portlet:param name="struts_action" value="/staging_bar/edit_layout_branch" />
	</portlet:actionURL>

	<aui:form action="<%= editLayoutBranchURL %>" method="post" name="fm3">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= layoutBranch != null ? Constants.UPDATE : Constants.ADD %>" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="groupId" type="hidden"  value="<%= String.valueOf(scopeGroupId) %>" />
		<aui:input name="layoutBranchId" type="hidden" value="<%= layoutBranchId %>" />
		<aui:input name="copyLayoutRevisionId" type="hidden" value="<%= String.valueOf(layoutRevisionId) %>" />
		<aui:input name="workflowAction" type="hidden" value="<%= String.valueOf(WorkflowConstants.ACTION_SAVE_DRAFT) %>" />

		<aui:input name="name" />

		<aui:input name="description" />

		<aui:button-row>
			<aui:button type="submit" value='<%= (layoutBranch != null) ? "update" : "add" %>' />
		</aui:button-row>
	</aui:form>
</div>