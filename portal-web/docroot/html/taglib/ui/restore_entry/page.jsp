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

<%@ include file="/html/common/init.jsp" %>

<%
String duplicateEntryAction = (String)request.getAttribute("liferay-ui:restore-entry:duplicateEntryAction");
String overrideMessage = (String)request.getAttribute("liferay-ui:restore-entry:overrideMessage");
String renameMessage = (String)request.getAttribute("liferay-ui:restore-entry:renameMessage");
String restoreEntryAction = (String)request.getAttribute("liferay-ui:restore-entry:restoreEntryAction");
%>

<aui:script use="liferay-restore-entry">
	<portlet:actionURL var="restoreEntryURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.CHECK %>" />
		<portlet:param name="struts_action" value="<%= restoreEntryAction %>" />
	</portlet:actionURL>

	<portlet:renderURL var="duplicateEntryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
		<portlet:param name="struts_action" value="<%= duplicateEntryAction %>" />
		<portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(request) %>" />
		<portlet:param name="restoreEntryAction" value="<%= restoreEntryAction %>" />
	</portlet:renderURL>

	new Liferay.RestoreEntry(
		{
			duplicateEntryURL: '<%= duplicateEntryURL %>',
			namespace: '<portlet:namespace />',
			overrideMessage: '<%= overrideMessage %>',
			renameMessage: '<%= renameMessage %>',
			restoreEntryURL: '<%= restoreEntryURL %>'
		}
	);
</aui:script>