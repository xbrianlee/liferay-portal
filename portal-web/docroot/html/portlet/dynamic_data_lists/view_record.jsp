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

<%@ include file="/html/portlet/dynamic_data_lists/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

DDLRecord record = (DDLRecord)request.getAttribute(WebKeys.DYNAMIC_DATA_LISTS_RECORD);

long recordId = BeanParamUtil.getLong(record, request, "recordId");

long recordSetId = BeanParamUtil.getLong(record, request, "recordSetId");

DDLRecordSet recordSet = DDLRecordSetServiceUtil.getRecordSet(recordSetId);

long formDDMTemplateId = ParamUtil.getLong(request, "formDDMTemplateId");

DDMStructure ddmStructure = recordSet.getDDMStructure(formDDMTemplateId);

String version = ParamUtil.getString(request, "version", DDLRecordConstants.VERSION_DEFAULT);

DDLRecordVersion recordVersion = record.getRecordVersion(version);

DDLRecordVersion latestRecordVersion = record.getLatestRecordVersion();
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= LanguageUtil.format(pageContext, "view-x", ddmStructure.getName(locale)) %>'
/>

<c:if test="<%= recordVersion != null %>">
	<aui:model-context bean="<%= recordVersion %>" model="<%= DDLRecordVersion.class %>" />

	<aui:workflow-status model="<%= DDLRecord.class %>" status="<%= recordVersion.getStatus() %>" version="<%= recordVersion.getVersion() %>" />
</c:if>

<aui:fieldset>

	<%
	Fields fields = null;

	if (recordVersion != null) {
		fields = StorageEngineUtil.getFields(recordVersion.getDDMStorageId());
	}
	%>

	<liferay-ddm:html
		classNameId="<%= PortalUtil.getClassNameId(DDMStructure.class) %>"
		classPK="<%= ddmStructure.getPrimaryKey() %>"
		fields="<%= fields %>"
		readOnly="<%= true %>"
		requestedLocale="<%= locale %>"
	/>

	<%
	boolean pending = false;

	if (recordVersion != null) {
		pending = recordVersion.isPending();
	}
	%>

	<aui:button-row>
		<c:if test="<%= DDLRecordSetPermission.contains(permissionChecker, record.getRecordSet(), ActionKeys.UPDATE) && version.equals(latestRecordVersion.getVersion()) %>">
			<portlet:renderURL var="editRecordURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="struts_action" value="/dynamic_data_lists/edit_record" />
				<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
				<portlet:param name="redirect" value="<%= redirect %>" />
				<portlet:param name="recordId" value="<%= String.valueOf(record.getRecordId()) %>" />
				<portlet:param name="formDDMTemplateId" value="<%= String.valueOf(formDDMTemplateId) %>" />
			</portlet:renderURL>

			<aui:button href="<%= editRecordURL %>" name="edit" value="edit" />
		</c:if>

		<aui:button href="<%= redirect %>" name="cancelButton" type="cancel" />
	</aui:button-row>
</aui:fieldset>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/dynamic_data_lists/view_record_set");
portletURL.setParameter("recordSetId", String.valueOf(recordSetId));

PortalUtil.addPortletBreadcrumbEntry(request, recordSet.getName(locale), portletURL.toString());
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.format(pageContext, "view-x", ddmStructure.getName(locale)), currentURL);
%>