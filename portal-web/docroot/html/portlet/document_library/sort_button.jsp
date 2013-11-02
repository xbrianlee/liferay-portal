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

long fileEntryTypeId = ParamUtil.getLong(request, "fileEntryTypeId", DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_ALL);

String orderByType = ParamUtil.getString(request, "orderByType");

String reverseOrderByType = "asc";

if (orderByType.equals("asc")) {
	reverseOrderByType = "desc";
}
%>

<aui:nav-item dropdown="<%= true %>" id="sortButtonContainer" label="sort-by">

	<%
	String taglibURL = "javascript:" + liferayPortletResponse.getNamespace() + "sortEntries('" + folderId + "', 'title','" + reverseOrderByType + "')";
	%>

	<aui:nav-item href="<%= taglibURL %>" label="title" />

	<%
	taglibURL = "javascript:" + liferayPortletResponse.getNamespace() + "sortEntries('" + folderId + "', 'creationDate','" + reverseOrderByType + "')";
	%>

	<aui:nav-item href="<%= taglibURL %>" label="create-date" />

	<%
	taglibURL = "javascript:" + liferayPortletResponse.getNamespace() + "sortEntries('" + folderId + "', 'modifiedDate','" + reverseOrderByType + "')";
	%>

	<aui:nav-item href="<%= taglibURL %>" label="modified-date" />

	<%
	taglibURL = "javascript:" + liferayPortletResponse.getNamespace() + "sortEntries('" + folderId + "', 'downloads','" + reverseOrderByType + "')";
	%>

	<aui:nav-item href="<%= taglibURL %>" label="downloads" />

	<%
	taglibURL = "javascript:" + liferayPortletResponse.getNamespace() + "sortEntries('" + folderId + "', 'size','" + reverseOrderByType + "')";
	%>

	<aui:nav-item href="<%= taglibURL %>" label="size" />
</aui:nav-item>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />sortEntries',
		function(folderId, orderByCol, reverseOrderByType) {
			Liferay.fire(
				'<portlet:namespace />dataRequest',
				{
					requestParams: {
						'<portlet:namespace />folderId': folderId,
						'<portlet:namespace />navigation': '<%= HtmlUtil.escape(navigation) %>',
						'<portlet:namespace />struts_action': '/document_library/view',
						'<portlet:namespace />fileEntryTypeId': <%= fileEntryTypeId %>,
						'<portlet:namespace />viewEntries': <%= Boolean.FALSE.toString() %>,
						'<portlet:namespace />viewEntriesPage': <%= Boolean.TRUE.toString() %>,
						'<portlet:namespace />viewFolders': <%= Boolean.FALSE.toString() %>,
						'<portlet:namespace />orderByCol': orderByCol,
						'<portlet:namespace />orderByType': reverseOrderByType,
						'<portlet:namespace />saveOrderBy': <%= Boolean.TRUE.toString() %>
					}
				}
			);
		},
		['aui-base']
	);
</aui:script>