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

<%@ include file="/html/portlet/journal/init.jsp" %>

<%
String navigation = ParamUtil.getString(request, "navigation", "home");

long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));

String structureId = ParamUtil.getString(request, "structureId");

String displayStyle = ParamUtil.getString(request, "displayStyle");

if (Validator.isNull(displayStyle)) {
	displayStyle = portalPreferences.getValue(PortletKeys.JOURNAL, "display-style", PropsValues.JOURNAL_DEFAULT_DISPLAY_VIEW);
}

String keywords = ParamUtil.getString(request, "keywords");

Map<String, String> requestParams = new HashMap<String, String>();

requestParams.put("struts_action", Validator.isNull(keywords) ? "/journal/view" : "/journal/search");
requestParams.put("navigation", HtmlUtil.escapeJS(navigation));
requestParams.put("folderId", String.valueOf(folderId));
requestParams.put("searchType", String.valueOf(JournalSearchConstants.FRAGMENT));
requestParams.put("viewEntriesPage", Boolean.FALSE.toString());
requestParams.put("viewFolders", Boolean.FALSE.toString());

if (Validator.isNull(keywords)) {
	requestParams.put("viewEntries", Boolean.TRUE.toString());
}
else {
	requestParams.put("keywords", HtmlUtil.escapeJS(keywords));
	requestParams.put("searchFolderId", String.valueOf(folderId));
	requestParams.put("viewEntries", Boolean.FALSE.toString());
}

if (!structureId.equals("0")) {
	requestParams.put("structureId", structureId);
}
%>

<liferay-ui:app-view-display-style
	displayStyle="<%= displayStyle %>"
	displayStyles="<%= displayViews %>"
	requestParams="<%= requestParams %>"
/>