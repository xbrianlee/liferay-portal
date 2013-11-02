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
BookmarksFolder folder = (BookmarksFolder)request.getAttribute(WebKeys.BOOKMARKS_FOLDER);

long folderId = BeanParamUtil.getLong(folder, request, "folderId", BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID);

String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectFolder");

String folderName = LanguageUtil.get(pageContext, "home");

if (folder != null) {
	folderName = folder.getName();

	BookmarksUtil.addPortletBreadcrumbEntries(folder, request, renderResponse);
}
%>

<aui:form method="post" name="selectFolderFm">
	<liferay-ui:header
		title="home"
	/>

	<liferay-ui:breadcrumb showGuestGroup="<%= false %>" showLayout="<%= false %>" showParentGroups="<%= false %>" />

	<%
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("struts_action", "/bookmarks/select_folder");
	portletURL.setParameter("folderId", String.valueOf(folderId));
	portletURL.setParameter("eventName", eventName);
	%>

	<br />

	<liferay-ui:search-container
		iteratorURL="<%= portletURL %>"
		total="<%= BookmarksFolderServiceUtil.getFoldersCount(scopeGroupId, folderId) %>"
	>
		<liferay-ui:search-container-results
			results="<%= BookmarksFolderServiceUtil.getFolders(scopeGroupId, folderId, searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portlet.bookmarks.model.BookmarksFolder"
			modelVar="curFolder"
		>

			<portlet:renderURL var="viewFolderURL">
				<portlet:param name="struts_action" value="/bookmarks/select_folder" />
				<portlet:param name="folderId" value="<%= String.valueOf(curFolder.getFolderId()) %>" />
			</portlet:renderURL>

			<%
			List<Long> subfolderIds = new ArrayList<Long>();

			subfolderIds.add(curFolder.getFolderId());

			BookmarksFolderServiceUtil.getSubfolderIds(subfolderIds, scopeGroupId, curFolder.getFolderId());

			int foldersCount = subfolderIds.size() - 1;
			int entriesCount = BookmarksEntryServiceUtil.getFoldersEntriesCount(scopeGroupId, subfolderIds);
			%>

			<liferay-ui:search-container-column-text
				href="<%= viewFolderURL %>"
				name="folder"
				value="<%= HtmlUtil.escape(curFolder.getName()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= viewFolderURL %>"
				name="num-of-folders"
				value="<%= String.valueOf(foldersCount) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= viewFolderURL %>"
				name="num-of-entries"
				value="<%= String.valueOf(entriesCount) %>"
			/>

			<liferay-ui:search-container-column-text>

				<%
				Map<String, Object> data = new HashMap<String, Object>();

				data.put("folderid", curFolder.getFolderId());
				data.put("name", HtmlUtil.escapeAttribute(curFolder.getName()));
				%>

				<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<aui:button-row>
			<c:if test="<%= BookmarksFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_FOLDER) %>">
				<portlet:renderURL var="editFolderURL">
					<portlet:param name="struts_action" value="/bookmarks/edit_folder" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="parentFolderId" value="<%= String.valueOf(folderId) %>" />
				</portlet:renderURL>

				<aui:button href="<%= editFolderURL %>" value='<%= (folder == null) ? "add-folder" : "add-subfolder" %>' />
			</c:if>

			<%
			Map<String, Object> data = new HashMap<String, Object>();

			data.put("folderid", folderId);
			data.put("name", HtmlUtil.escapeAttribute(folderName));
			%>

			<aui:button cssClass="selector-button" data="<%= data %>" value="choose-this-folder" />
		</aui:button-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script use="aui-base">
	var Util = Liferay.Util;

	A.one('#<portlet:namespace />selectFolderFm').delegate(
		'click',
		function(event) {
			var result = Util.getAttributes(event.currentTarget, 'data-');

			Util.getOpener().Liferay.fire('<%= HtmlUtil.escapeJS(eventName) %>', result);

			Util.getWindow().hide();
		},
		'.selector-button'
	);
</aui:script>