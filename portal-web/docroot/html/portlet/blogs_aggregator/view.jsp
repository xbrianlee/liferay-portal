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

<%@ include file="/html/portlet/blogs_aggregator/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/blogs_aggregator/view");

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 5, portletURL, null, null);

List entries = null;

if (selectionMethod.equals("users")) {
	if (organizationId > 0) {
		entries = BlogsEntryServiceUtil.getOrganizationEntries(organizationId, new Date(), WorkflowConstants.STATUS_APPROVED, max);
	}
	else {
		entries = BlogsEntryServiceUtil.getGroupsEntries(company.getCompanyId(), scopeGroupId, new Date(), WorkflowConstants.STATUS_APPROVED, max);
	}
}
else {
	entries = BlogsEntryServiceUtil.getGroupEntries(scopeGroupId, new Date(), WorkflowConstants.STATUS_APPROVED, max);
}

int total = entries.size();

searchContainer.setTotal(total);

List results = ListUtil.subList(entries, searchContainer.getStart(), searchContainer.getEnd());

searchContainer.setResults(results);
%>

<%@ include file="/html/portlet/blogs_aggregator/view_entries.jspf" %>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<aui:script>
		Liferay.Util.focusFormField(document.<portlet:namespace />fm1.<portlet:namespace />keywords);
	</aui:script>
</c:if>