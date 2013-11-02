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

<%@ include file="/html/portlet/software_catalog/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

SCProductVersion productVersion = (SCProductVersion)row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= Validator.isNotNull(productVersion.getDownloadPageURL()) %>">
		<liferay-ui:icon image="download" message="download-page" url="<%= productVersion.getDownloadPageURL() %>" />
	</c:if>

	<c:if test="<%= Validator.isNotNull(productVersion.getDirectDownloadURL()) %>">

		<%
		String taglibDirectDownloadURL = "javascript:location.href = '" + productVersion.getDirectDownloadURL() + "';";
		%>

		<liferay-ui:icon image="download" message="direct-download" url="<%= taglibDirectDownloadURL %>" />
	</c:if>

	<c:if test="<%= SCProductEntryPermission.contains(permissionChecker, productVersion.getProductEntryId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="struts_action" value="/software_catalog/edit_product_version" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="productEntryId" value="<%= String.valueOf(productVersion.getProductEntryId()) %>" />
			<portlet:param name="productVersionId" value="<%= String.valueOf(productVersion.getProductVersionId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" url="<%= editURL %>" />

		<portlet:renderURL var="copyURL">
			<portlet:param name="struts_action" value="/software_catalog/edit_product_version" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="productEntryId" value="<%= String.valueOf(productVersion.getProductEntryId()) %>" />
			<portlet:param name="productVersionId" value="0" />
			<portlet:param name="copyProductVersionId" value="<%= String.valueOf(productVersion.getProductVersionId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="copy" url="<%= copyURL %>" />

		<portlet:actionURL var="deleteURL">
			<portlet:param name="struts_action" value="/software_catalog/edit_product_version" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="productEntryId" value="<%= String.valueOf(productVersion.getProductEntryId()) %>" />
			<portlet:param name="productVersionId" value="<%= String.valueOf(productVersion.getProductVersionId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteURL %>" />
	</c:if>
</liferay-ui:icon-menu>