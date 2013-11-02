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

JournalFolder folder = (JournalFolder)request.getAttribute(WebKeys.JOURNAL_FOLDER);

long folderId = ParamUtil.getLong(request, "folderId", JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);

if ((folder == null) && (folderId != JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID)) {
	try {
		folder = JournalFolderLocalServiceUtil.getFolder(folderId);
	}
	catch (NoSuchFolderException nsfe) {
		folderId = JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID;
	}
}

String browseBy = ParamUtil.getString(request, "browseBy");
boolean viewEntries = ParamUtil.getBoolean(request, "viewEntries");
boolean viewEntriesPage = ParamUtil.getBoolean(request, "viewEntriesPage");
boolean viewFolders = ParamUtil.getBoolean(request, "viewFolders");

request.setAttribute("view.jsp-folder", folder);

request.setAttribute("view.jsp-folderId", String.valueOf(folderId));
%>

<div>
	<c:if test="<%= viewEntries %>">

		<%
		PortalUtil.addPortletBreadcrumbEntry(request, themeDisplay.getScopeGroup().getDescriptiveName(), null);
		PortalUtil.addPortletBreadcrumbEntry(request, "Web Content", liferayPortletResponse.createRenderURL().toString());
		%>

		<div id="<portlet:namespace />entries">
			<liferay-util:include page="/html/portlet/journal/view_entries.jsp" />
		</div>

		<span id="<portlet:namespace />addButton">
			<liferay-util:include page="/html/portlet/journal/add_button.jsp" />
		</span>

		<span id="<portlet:namespace />displayStyleButtons">
			<liferay-util:include page="/html/portlet/journal/display_style_buttons.jsp" />
		</span>

		<span id="<portlet:namespace />sortButton">
			<liferay-util:include page="/html/portlet/journal/sort_button.jsp" />
		</span>

		<span id="<portlet:namespace />breadcrumb">
			<div class="portlet-breadcrumb">
				<c:if test='<%= !navigation.equals("recent") && !navigation.equals("mine") && Validator.isNull(browseBy) %>'>
					<liferay-util:include page="/html/portlet/journal/breadcrumb.jsp" />
				</c:if>
			</div>
		</span>
	</c:if>

	<c:if test="<%= viewEntriesPage %>">
		<div id="<portlet:namespace />entries">
			<liferay-util:include page="/html/portlet/journal/view_entries.jsp" />
		</div>

		<span id="<portlet:namespace />sortButton">
			<liferay-util:include page="/html/portlet/journal/sort_button.jsp" />
		</span>
	</c:if>

	<c:if test="<%= viewFolders %>">
		<div id="<portlet:namespace />folders">
			<liferay-util:include page="/html/portlet/journal/view_folders.jsp" />
		</div>
	</c:if>
</div>