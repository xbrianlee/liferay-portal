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

List<LayoutSetBranch> layoutSetBranches = LayoutSetBranchLocalServiceUtil.getLayoutSetBranches(groupId, privateLayout);

long layoutSetBranchId = ParamUtil.getLong(request, "layoutSetBranchId");

LayoutSetBranch layoutSetBranch = LayoutSetBranchLocalServiceUtil.getLayoutSetBranch(layoutSetBranchId);

if (layoutSetBranches.contains(layoutSetBranch)) {
	layoutSetBranches = ListUtil.copy(layoutSetBranches);

	layoutSetBranches.remove(layoutSetBranch);
}
%>

<div id="<portlet:namespace />mergeLayoutSetBranch">
	<portlet:actionURL var="mergeLayoutSetBranchURL">
		<portlet:param name="struts_action" value="/staging_bar/edit_layout_set_branch" />
	</portlet:actionURL>

	<aui:form action="<%= mergeLayoutSetBranchURL %>" enctype="multipart/form-data" method="post" name="fm4">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="merge_layout_set_branch" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="groupId" type="hidden" value="<%= groupId %>" />
		<aui:input name="layoutSetBranchId" type="hidden" value="<%= layoutSetBranchId %>" />
		<aui:input name="mergeLayoutSetBranchId" type="hidden" />

		<liferay-ui:search-container id="layoutSetBranchesSearchContainer">
			<liferay-ui:search-container-results
				results="<%= layoutSetBranches %>"
				total="<%= layoutSetBranches.size() %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.model.LayoutSetBranch"
				escapedModel="<%= true %>"
				keyProperty="layoutSetBranchId"
				modelVar="curLayoutSetBranch"
			>

				<liferay-ui:search-container-column-text
					name="branch"
					value="<%= LanguageUtil.get(pageContext, curLayoutSetBranch.getName()) %>"
				/>

				<liferay-ui:search-container-column-text
					buffer="buffer"
				>

					<%
					buffer.append("<a class='layout-set-branch' data-layoutSetBranchId='");
					buffer.append(curLayoutSetBranch.getLayoutSetBranchId());
					buffer.append("' data-layoutSetBranchName='");
					buffer.append(HtmlUtil.escapeAttribute(curLayoutSetBranch.getName()));
					buffer.append("' data-layoutSetBranchMessage='");
					buffer.append(HtmlUtil.escapeAttribute(LanguageUtil.format(pageContext, "are-you-sure-you-want-to-merge-changes-from-x", curLayoutSetBranch.getName())));
					buffer.append("' href='#'>");
					buffer.append(LanguageUtil.get(pageContext, "select"));
					buffer.append("</a>");
					%>

				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="<%= false %>" searchContainer="<%= searchContainer %>" />
		</liferay-ui:search-container>
	</aui:form>
</div>