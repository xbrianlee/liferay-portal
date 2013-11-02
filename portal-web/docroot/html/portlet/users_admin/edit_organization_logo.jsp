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

<%@ include file="/html/portlet/users_admin/init.jsp" %>

<%
long groupId = ParamUtil.getLong(request, "groupId");
long publicLayoutSetId = ParamUtil.getLong(request, "publicLayoutSetId");

String logoURL = StringPool.BLANK;

if (publicLayoutSetId != 0) {
	LayoutSet publicLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(publicLayoutSetId);

	logoURL = themeDisplay.getPathImage() + "/organization_logo?img_id=" + publicLayoutSet.getLogoId() + "&t=" + WebServerServletTokenUtil.getToken(publicLayoutSet.getLogoId());
}
%>

<c:choose>
	<c:when test='<%= SessionMessages.contains(renderRequest, "requestProcessed") %>'>
		<aui:script>
			Liferay.Util.getOpener().<portlet:namespace />changeLogo('<%= logoURL %>');

			Liferay.Util.getWindow().hide();
		</aui:script>
	</c:when>
	<c:otherwise>
		<portlet:actionURL var="editOrganizationLogoURL">
			<portlet:param name="struts_action" value="/users_admin/edit_organization_logo" />
		</portlet:actionURL>

		<aui:form action="<%= editOrganizationLogoURL %>" enctype="multipart/form-data" method="post" name="fm">
			<aui:input name="groupId" type="hidden" value="<%= groupId %>" />
			<aui:input name="publicLayoutSetId" type="hidden" value="<%= publicLayoutSetId %>" />
			<aui:input name="cropRegion" type="hidden" />

			<liferay-ui:error exception="<%= NoSuchFileException.class %>" message="an-unexpected-error-occurred-while-uploading-your-file" />
			<liferay-ui:error exception="<%= UploadException.class %>" message="an-unexpected-error-occurred-while-uploading-your-file" />

			<aui:fieldset cssClass="lfr-portrait-editor">
				<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" label="upload-a-logo-for-the-organization-pages-that-will-be-used-instead-of-the-default-enterprise-logo-in-both-public-and-private-pages" name="fileName" size="50" type="file" />

				<div class="lfr-change-logo lfr-portrait-preview" id="<portlet:namespace />portraitPreview">
					<img class="lfr-portrait-preview-img" id="<portlet:namespace />portraitPreviewImg" src="<%= HtmlUtil.escape(logoURL) %>" />
				</div>

				<aui:button-row>
					<aui:button name="submitButton" type="submit" />

					<aui:button onClick="window.close();" type="cancel" value="close" />
				</aui:button-row>
			</aui:fieldset>
		</aui:form>

		<aui:script use="liferay-logo-editor">
			var logoEditor = new Liferay.LogoEditor(
				{
					maxFileSize: '<%= PrefsPropsUtil.getLong(PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE) / 1024 %>',
					namespace: '<portlet:namespace />',
					previewURL: '<portlet:resourceURL><portlet:param name="struts_action" value="/users_admin/edit_organization_logo" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.GET_TEMP %>" /><portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" /></portlet:resourceURL>',
					uploadURL: '<portlet:actionURL><portlet:param name="struts_action" value="/users_admin/edit_organization_logo" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD_TEMP %>" /><portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" /></portlet:actionURL>'
				}
			);

			if (Liferay.Util.getTop() !== A.config.win) {
				var dialog = Liferay.Util.getWindow();

				if (dialog) {
					dialog.on(['resize:end', 'resize:resize', 'resize:start'], logoEditor.resize, logoEditor);
				}
			}
		</aui:script>
	</c:otherwise>
</c:choose>