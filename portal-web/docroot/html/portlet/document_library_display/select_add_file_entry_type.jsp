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

<%@ include file="/html/portlet/document_library_display/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL");

long repositoryId = ParamUtil.getLong(request, "repositoryId");
long folderId = ParamUtil.getLong(request, "folderId");

List<DLFileEntryType> fileEntryTypes = DLFileEntryTypeServiceUtil.getFolderFileEntryTypes(PortalUtil.getSiteAndCompanyGroupIds(themeDisplay), folderId, true);
%>

<liferay-ui:search-container>
	<liferay-ui:search-container-results
		results="<%= fileEntryTypes %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portlet.documentlibrary.model.DLFileEntryType"
		escapedModel="<%= true %>"
		keyProperty="fileEntryTypeId"
		modelVar="fileEntryType"
	>
		<liferay-ui:search-container-column-text name="name">
			<a class="select-file-entry-type" data-rowId="<%= fileEntryType.getFileEntryTypeId() %>" href="javascript:;"><%= HtmlUtil.escape(fileEntryType.getName(locale)) %></a>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<aui:script use="liferay-portlet-url,liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />dlFileEntryTypesSearchContainer');

	searchContainer.get('contentBox').delegate(
		'click',
		function(event) {
			var link = event.currentTarget;

			var portletURL = Liferay.PortletURL.createURL('<%= PortletURLFactoryUtil.create(request, portletId, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>');

			portletURL.setParameter('<%= Constants.CMD %>', '<%= Constants.ADD %>');
			portletURL.setParameter('backURL', '<%= HtmlUtil.escape(backURL) %>');
			portletURL.setParameter('fileEntryTypeId', link.getAttribute('data-rowId'));
			portletURL.setParameter('folderId', '<%= folderId %>');
			portletURL.setParameter('redirect', '<%= HtmlUtil.escape(redirect) %>');
			portletURL.setParameter('repositoryId', '<%= repositoryId %>');
			portletURL.setParameter('struts_action', '/document_library/edit_file_entry');

			Liferay.Util.getOpener().location.href = portletURL.toString();

			Liferay.fire(
				'closeWindow',
				{
					id: '<portlet:namespace />selectDocumentType'
				}
			);
		},
		'.select-file-entry-type'
	);
</aui:script>