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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

JournalFolder folder = null;

if (row != null) {
	folder = (JournalFolder)row.getObject();
}
else {
	folder = (JournalFolder)request.getAttribute("view_entries.jsp-folder");
}

boolean folderSelected = GetterUtil.getBoolean(request.getAttribute("view_entries.jsp-folderSelected"));

String modelResource = null;
String modelResourceDescription = null;
String resourcePrimKey = null;

boolean hasPermissionsPermission = false;

if (folder != null) {
	modelResource= JournalFolder.class.getName();
	modelResourceDescription = folder.getName();
	resourcePrimKey= String.valueOf(folder.getPrimaryKey());

	hasPermissionsPermission = JournalFolderPermission.contains(permissionChecker, folder, ActionKeys.PERMISSIONS);
}
else {
	modelResource= "com.liferay.portlet.journal";
	modelResourceDescription = HtmlUtil.escape(themeDisplay.getScopeGroupName());
	resourcePrimKey= String.valueOf(scopeGroupId);

	hasPermissionsPermission = JournalPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS);
}
%>

<span class="entry-action overlay">
	<liferay-ui:icon-menu direction="down" extended="<%= false %>" icon="<%= StringPool.BLANK %>" message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>" triggerCssClass="btn">
		<c:choose>
			<c:when test="<%= folder != null %>">
				<c:if test="<%= JournalFolderPermission.contains(permissionChecker, folder, ActionKeys.UPDATE) %>">
					<portlet:renderURL var="editURL">
						<portlet:param name="struts_action" value="/journal/edit_folder" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="groupId" value="<%= String.valueOf(folder.getGroupId()) %>" />
						<portlet:param name="folderId" value="<%= String.valueOf(folder.getFolderId()) %>" />
						<portlet:param name="mergeWithParentFolderDisabled" value="<%= String.valueOf(folderSelected) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="edit"
						url="<%= editURL %>"
					/>

					<portlet:renderURL var="moveURL">
						<portlet:param name="struts_action" value="/journal/move_folder" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="folderId" value="<%= String.valueOf(folder.getFolderId()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="submit"
						message="move"
						url="<%= moveURL %>"
					/>
				</c:if>
				<c:if test="<%= JournalFolderPermission.contains(permissionChecker, folder, ActionKeys.DELETE) %>">
					<portlet:actionURL var="deleteURL">
						<portlet:param name="struts_action" value="/journal/edit_folder" />
						<portlet:param name="<%= Constants.CMD %>" value="<%= TrashUtil.isTrashEnabled(scopeGroupId) ? Constants.MOVE_TO_TRASH : Constants.DELETE %>" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="groupId" value="<%= String.valueOf(folder.getGroupId()) %>" />
						<portlet:param name="folderId" value="<%= String.valueOf(folder.getFolderId()) %>" />
					</portlet:actionURL>

					<liferay-ui:icon-delete trash="<%= TrashUtil.isTrashEnabled(scopeGroupId) %>" url="<%= deleteURL %>" />
				</c:if>

				<c:if test="<%= JournalFolderPermission.contains(permissionChecker, folder, ActionKeys.ADD_FOLDER) %>">
					<portlet:renderURL var="addFolderURL">
						<portlet:param name="struts_action" value="/journal/edit_folder" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="groupId" value="<%= String.valueOf(folder.getGroupId()) %>" />
						<portlet:param name="parentFolderId" value="<%= String.valueOf(folder.getFolderId()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="add_folder"
						message='<%= (folder != null) ? "add-subfolder" : "add-folder" %>'
						url="<%= addFolderURL %>"
					/>
				</c:if>
			</c:when>
			<c:otherwise>
				<c:if test="<%= JournalPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_FOLDER) %>">
					<portlet:renderURL var="addFolderURL">
						<portlet:param name="struts_action" value="/journal/edit_folder" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
						<portlet:param name="parentFolderId" value="<%= String.valueOf(JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="add_folder"
						message='<%= (folder != null) ? "add-subfolder" : "add-folder" %>'
						url="<%= addFolderURL %>"
					/>
				</c:if>

				<c:if test="<%= JournalPermission.contains(permissionChecker, scopeGroupId, ActionKeys.SUBSCRIBE) && (JournalUtil.getEmailArticleAddedEnabled(portletPreferences) || JournalUtil.getEmailArticleApprovalDeniedEnabled(portletPreferences) || JournalUtil.getEmailArticleApprovalGrantedEnabled(portletPreferences) || JournalUtil.getEmailArticleApprovalRequestedEnabled(portletPreferences) || JournalUtil.getEmailArticleReviewEnabled(portletPreferences) || JournalUtil.getEmailArticleUpdatedEnabled(portletPreferences)) %>">
					<c:choose>
						<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(company.getCompanyId(), user.getUserId(), JournalArticle.class.getName(), scopeGroupId) %>">
							<portlet:actionURL var="unsubscribeURL">
								<portlet:param name="struts_action" value="/journal/edit_article" />
								<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UNSUBSCRIBE %>" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
							</portlet:actionURL>

							<liferay-ui:icon cssClass="subscribe-link" image="unsubscribe" label="<%= true %>" url="<%= unsubscribeURL %>" />
						</c:when>
						<c:otherwise>
							<portlet:actionURL var="subscribeURL">
								<portlet:param name="struts_action" value="/journal/edit_article" />
								<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.SUBSCRIBE %>" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
							</portlet:actionURL>

							<liferay-ui:icon cssClass="subscribe-link" image="subscribe" label="<%= true %>" url="<%= subscribeURL %>" />
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:otherwise>
		</c:choose>

		<c:if test="<%= hasPermissionsPermission %>">
			<liferay-security:permissionsURL
				modelResource="<%= modelResource %>"
				modelResourceDescription="<%= modelResourceDescription %>"
				resourcePrimKey="<%= resourcePrimKey %>"
				var="permissionsURL"
				windowState="<%= LiferayWindowState.POP_UP.toString() %>"
			/>

			<liferay-ui:icon
				image="permissions"
				method="get"
				url="<%= permissionsURL %>"
				useDialog="<%= true %>"
			/>
		</c:if>
	</liferay-ui:icon-menu>
</span>