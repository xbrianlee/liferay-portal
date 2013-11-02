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
String navigation = ParamUtil.getString(request, "navigation", "home");

long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));

long fileEntryTypeId = ParamUtil.getLong(request, "fileEntryTypeId", -1);

String displayStyle = ParamUtil.getString(request, "displayStyle");

if (Validator.isNull(displayStyle)) {
	displayStyle = portalPreferences.getValue(PortletKeys.DOCUMENT_LIBRARY, "display-style", PropsValues.DL_DEFAULT_DISPLAY_VIEW);
}

String keywords = ParamUtil.getString(request, "keywords");

Map<String, String> requestParams = new HashMap<String, String>();

requestParams.put("struts_action", Validator.isNull(keywords) ? "/document_library/view" : "/document_library/search");
requestParams.put("navigation", HtmlUtil.escapeJS(navigation));
requestParams.put("folderId", String.valueOf(folderId));
requestParams.put("saveDisplayStyle", Boolean.TRUE.toString());
requestParams.put("searchType", String.valueOf(DLSearchConstants.FRAGMENT));
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

if (fileEntryTypeId != -1) {
	requestParams.put("fileEntryTypeId", String.valueOf(fileEntryTypeId));
}
%>

<liferay-ui:app-view-display-style
	displayStyle="<%= displayStyle %>"
	displayStyles="<%= displayViews %>"
	requestParams="<%= requestParams %>"
/>