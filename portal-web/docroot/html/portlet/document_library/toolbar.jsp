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
String strutsAction = ParamUtil.getString(request, "struts_action");

Folder folder = (Folder)request.getAttribute("view.jsp-folder");

long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));

long repositoryId = GetterUtil.getLong((String)request.getAttribute("view.jsp-repositoryId"));

Group scopeGroup = themeDisplay.getScopeGroup();
%>

<aui:nav-bar>
	<aui:nav collapsible="<%= false %>" cssClass="nav-display-style-buttons pull-right" id="displayStyleButtons">
		<aui:nav-item>
			<span class="pull-left display-style-buttons-container" id="<portlet:namespace />displayStyleButtonsContainer">
				<c:if test='<%= !strutsAction.equals("/document_library/search") %>'>
					<liferay-util:include page="/html/portlet/document_library/display_style_buttons.jsp" />
				</c:if>
			</span>
		</aui:nav-item>
	</aui:nav>

	<aui:nav id="toolbarContainer">
		<aui:nav-item cssClass="hide" dropdown="<%= true %>" id="actionsButtonContainer" label="actions">
			<c:if test="<%= !scopeGroup.isStaged() || scopeGroup.isStagingGroup() || !scopeGroup.isStagedPortlet(PortletKeys.DOCUMENT_LIBRARY) %>">

				<%
				String taglibURL = "javascript:Liferay.fire('" + renderResponse.getNamespace() + "editEntry', {action: '" + Constants.CANCEL_CHECKOUT + "'}); void(0);";
				%>

				<aui:nav-item href="<%= taglibURL %>" label="cancel-checkout[document]" />

				<%
				taglibURL = "javascript:Liferay.fire('" + renderResponse.getNamespace() + "editEntry', {action: '" + Constants.CHECKIN + "'}); void(0);";
				%>

				<aui:nav-item href="<%= taglibURL %>" label="checkin" />

				<%
				taglibURL = "javascript:Liferay.fire('" + renderResponse.getNamespace() + "editEntry', {action: '" + Constants.CHECKOUT + "'}); void(0);";
				%>

				<aui:nav-item href="<%= taglibURL %>" label="checkout[document]" />

				<%
				taglibURL = "javascript:Liferay.fire('" + renderResponse.getNamespace() + "editEntry', {action: '" + Constants.MOVE + "'}); void(0);";
				%>

				<aui:nav-item href="<%= taglibURL %>" label="move" />
			</c:if>

			<%
			String taglibURL = "javascript:Liferay.fire('" + renderResponse.getNamespace() + "editEntry', {action: '" + Constants.MOVE_TO_TRASH + "'}); void(0);";
			%>

			<aui:nav-item href="<%= taglibURL %>" iconCssClass="icon-trash" id="moveToTrashAction" label="move-to-the-recycle-bin" />

			<%
			taglibURL = "javascript:" + renderResponse.getNamespace() + "deleteEntries();";
			%>

			<aui:nav-item href="<%= taglibURL %>" iconCssClass="icon-remove" id="deleteAction" label="delete" />
		</aui:nav-item>

		<liferay-util:include page="/html/portlet/document_library/add_button.jsp" />

		<liferay-util:include page="/html/portlet/document_library/sort_button.jsp" />

		<c:if test="<%= !user.isDefaultUser() %>">
			<aui:nav-item dropdown="<%= true %>" label="manage">

				<%
				String taglibURL = "javascript:" + renderResponse.getNamespace() + "openFileEntryTypeView()";
				%>

				<aui:nav-item href="<%= taglibURL %>" iconCssClass="icon-file" label="document-types" />

				<%
				taglibURL = "javascript:" + renderResponse.getNamespace() + "openDDMStructureView()";
				%>

				<aui:nav-item href="<%= taglibURL %>" iconCssClass="icon-file-text" label="metadata-sets" />
			</aui:nav-item>
		</c:if>
	</aui:nav>

	<c:if test="<%= showFoldersSearch %>">
		<aui:nav-bar-search cssClass="pull-right">
			<div class="form-search">
				<liferay-portlet:resourceURL varImpl="searchURL">
					<portlet:param name="struts_action" value="/document_library/search" />
					<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
					<portlet:param name="searchRepositoryId" value="<%= String.valueOf(folderId) %>" />
					<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
					<portlet:param name="searchFolderId" value="<%= String.valueOf(folderId) %>" />
				</liferay-portlet:resourceURL>

				<aui:form action="<%= searchURL.toString() %>" method="get" name="fm1" onSubmit="event.preventDefault();">
					<liferay-portlet:renderURLParams varImpl="searchURL" />
					<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
					<aui:input name="breadcrumbsFolderId" type="hidden" value="<%= folderId %>" />
					<aui:input name="searchFolderIds" type="hidden" value="<%= folderId %>" />

					<liferay-ui:input-search />
				</aui:form>
			</div>
		</aui:nav-bar-search>
	</c:if>
</aui:nav-bar>

<aui:script>
	function <portlet:namespace />deleteEntries() {
		if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-the-selected-entries") %>')) {
			Liferay.fire(
				'<%= renderResponse.getNamespace() %>editEntry',
				{
					action: '<%= Constants.DELETE %>'
				}
			);
		}
	}

	function <portlet:namespace />openFileEntryTypeView() {
		Liferay.Util.openWindow(
			{
				id: '<portlet:namespace />openFileEntryTypeView',
				title: '<%= UnicodeLanguageUtil.get(pageContext, "document-types") %>',
				uri: '<liferay-portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="struts_action" value="/document_library/view_file_entry_type" /><portlet:param name="redirect" value="<%= currentURL %>" /></liferay-portlet:renderURL>'
			}
		);
	}

	function <portlet:namespace />openDDMStructureView() {
		Liferay.Util.openDDMPortlet(
			{
				basePortletURL: '<%= PortletURLFactoryUtil.create(request, PortletKeys.DYNAMIC_DATA_MAPPING, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>',
				dialog: {
					destroyOnHide: true
				},
				refererPortletName: '<%= PortletKeys.DOCUMENT_LIBRARY %>',
				showGlobalScope: true,
				showManageTemplates: false,
				title: '<%= UnicodeLanguageUtil.get(pageContext, "metadata-sets") %>'
			}
		);
	}
</aui:script>