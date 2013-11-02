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

<%@ include file="/html/portlet/trash/init.jsp" %>

<%
SearchContainer searchContainer = (SearchContainer)request.getAttribute("liferay-ui:search:searchContainer");

String redirect = ParamUtil.getString(request, "redirect");

if (searchContainer != null) {
	redirect = searchContainer.getIteratorURL().toString();
}

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

TrashEntry entry = null;

if (row != null) {
	entry = (TrashEntry)row.getObject();
}
else {
	entry = (TrashEntry)request.getAttribute(WebKeys.TRASH_ENTRY);
}
%>

<liferay-ui:icon-menu showWhenSingleIcon="<%= true %>">
	<%@ include file="/html/portlet/trash/action/restore.jspf" %>
	<%@ include file="/html/portlet/trash/action/delete.jspf" %>
</liferay-ui:icon-menu>