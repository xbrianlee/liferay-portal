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

<%@ include file="/html/portlet/bookmarks/init.jsp" %>

<%
BookmarksEntry entry = (BookmarksEntry)request.getAttribute(WebKeys.BOOKMARKS_ENTRY);
%>

<aui:a href='<%= themeDisplay.getPathMain() + "/bookmarks/open_entry?entryId=" + entry.getEntryId() + (portletName.equals(PortletKeys.TRASH) ? "&status=" + WorkflowConstants.STATUS_IN_TRASH : StringPool.BLANK) %>' target="_blank"><%= HtmlUtil.escape(entry.getName()) %> (<%= HtmlUtil.escape(entry.getUrl()) %>)</aui:a>

<p class="asset-description"><%= HtmlUtil.escape(entry.getDescription()) %></p>

<liferay-ui:custom-attributes-available className="<%= BookmarksEntry.class.getName() %>">
	<liferay-ui:custom-attribute-list
		className="<%= BookmarksEntry.class.getName() %>"
		classPK="<%= (entry != null) ? entry.getEntryId() : 0 %>"
		editable="<%= false %>"
		label="<%= true %>"
	/>
</liferay-ui:custom-attributes-available>