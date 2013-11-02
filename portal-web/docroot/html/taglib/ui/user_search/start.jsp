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

<%@ include file="/html/taglib/init.jsp" %>

<portlet:defineObjects />

<%
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-ui:user-search:portletURL");
RowChecker rowChecker = (RowChecker)request.getAttribute("liferay-ui:user-search:rowChecker");
LinkedHashMap<String, Object> userParams = (LinkedHashMap<String, Object>)request.getAttribute("liferay-ui:user-search:userParams");

UserSearch searchContainer = new UserSearch(renderRequest, portletURL);

request.setAttribute(WebKeys.SEARCH_CONTAINER, searchContainer);

searchContainer.setRowChecker(rowChecker);
%>

<liferay-ui:search-form
	page="/html/portlet/users_admin/user_search.jsp"
	searchContainer="<%= searchContainer %>"
/>

<%
SearchContainer userSearchContainer = searchContainer;

UserSearchTerms searchTerms = (UserSearchTerms)searchContainer.getSearchTerms();

List<User> results = null;
int total = 0;
%>

<%@ include file="/html/portlet/users_admin/user_search_results.jspf" %>

<%
searchContainer.setResults(results);
searchContainer.setTotal(total);
%>