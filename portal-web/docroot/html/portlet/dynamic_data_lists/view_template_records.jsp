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

<%@ include file="/html/portlet/dynamic_data_list_display/init.jsp" %>

<%
DDLRecordSet recordSet = (DDLRecordSet)request.getAttribute(WebKeys.DYNAMIC_DATA_LISTS_RECORD_SET);

DDMStructure ddmStructure = recordSet.getDDMStructure();

boolean showAddRecordButton = false;

if (DDLUtil.isEditable(request, portletDisplay.getId(), themeDisplay.getScopeGroupId())) {
	showAddRecordButton = DDLRecordSetPermission.contains(permissionChecker, recordSet.getRecordSetId(), ActionKeys.ADD_RECORD);
}
%>

<portlet:actionURL var="editRecordSetURL">
	<portlet:param name="struts_action" value="/dynamic_data_lists/edit_record_set" />
</portlet:actionURL>

<aui:form action="<%= editRecordSetURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveRecordSet();" %>'>
	<c:if test="<%= showAddRecordButton %>">
		<aui:nav-bar>
			<aui:nav>
				<portlet:renderURL var="addRecordURL">
					<portlet:param name="struts_action" value="/dynamic_data_lists/edit_record" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="recordSetId" value="<%= String.valueOf(recordSet.getRecordSetId()) %>" />
					<portlet:param name="formDDMTemplateId" value="<%= String.valueOf(formDDMTemplateId) %>" />
				</portlet:renderURL>

				<aui:nav-item href="<%= addRecordURL %>" iconCssClass="icon-plus" label='<%= LanguageUtil.format(pageContext, "add-x", HtmlUtil.escape(ddmStructure.getName(locale))) %>' />
			</aui:nav>
		</aui:nav-bar>
	</c:if>

	<%= DDLUtil.getTemplateContent(displayDDMTemplateId, recordSet, themeDisplay, renderRequest, renderResponse) %>
</aui:form>