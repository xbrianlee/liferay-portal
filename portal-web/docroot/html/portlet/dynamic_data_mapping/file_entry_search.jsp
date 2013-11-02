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

<%@ include file="/html/portlet/document_library/init.jsp" %>

<%
FileEntrySearch searchContainer = (FileEntrySearch)request.getAttribute("liferay-ui:search:searchContainer");

FileEntryDisplayTerms displayTerms = (FileEntryDisplayTerms)searchContainer.getDisplayTerms();
%>

<liferay-ui:search-toggle
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_asset_search"
>
	<aui:fieldset>
		<aui:input name="keywords" size="20" type="text" value="" />

		<aui:select label="scope" name="<%= displayTerms.SELECTED_GROUP_ID %>" showEmptyOption="<%= false %>">
			<aui:option label="global" selected="<%= displayTerms.getSelectedGroupId() == themeDisplay.getCompanyGroupId() %>" value="<%= themeDisplay.getCompanyGroupId() %>" />
			<aui:option label="<%= themeDisplay.getScopeGroupName() %>" selected="<%= displayTerms.getSelectedGroupId() == scopeGroupId %>" value="<%= scopeGroupId %>" />
		</aui:select>
	</aui:fieldset>
</liferay-ui:search-toggle>