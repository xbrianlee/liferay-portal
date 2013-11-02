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

<%@ include file="/html/portlet/layouts_admin/init.jsp" %>

<%
Group liveGroup = (Group)request.getAttribute("edit_pages.jsp-liveGroup");
boolean privateLayout = ((Boolean)request.getAttribute("edit_pages.jsp-privateLayout")).booleanValue();
UnicodeProperties groupTypeSettings = (UnicodeProperties)request.getAttribute("edit_pages.jsp-groupTypeSettings");
LayoutSet selLayoutSet = ((LayoutSet)request.getAttribute("edit_pages.jsp-selLayoutSet"));
%>

<liferay-ui:error-marker key="errorSection" value="logo" />

<h3><liferay-ui:message key="logo" /></h3>

<aui:fieldset>
	<liferay-ui:error exception="<%= FileSizeException.class %>">

		<%
		long fileMaxSize = PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE);

		if (fileMaxSize == 0) {
			fileMaxSize = PrefsPropsUtil.getLong(PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE);
		}

		fileMaxSize /= 1024;
		%>

		<liferay-ui:message arguments="<%= fileMaxSize %>" key="please-enter-a-file-with-a-valid-file-size-no-larger-than-x" />
	</liferay-ui:error>

	<liferay-ui:error exception="<%= ImageTypeException.class %>" message="please-enter-a-file-with-a-valid-file-type" />
	<liferay-ui:error exception="<%= UploadException.class %>" message="an-unexpected-error-occurred-while-uploading-your-file" />

	<aui:input name="useLogo" type="hidden" value="<%= selLayoutSet.isLogo() %>" />

	<p><%= LanguageUtil.get(pageContext, "upload-a-logo-for-the-" + (privateLayout ? "private" : "public") + "-pages-that-will-be-used-instead-of-the-default-enterprise-logo") %></p>

	<aui:input inlineField="<%= true %>" label="" name="logoFileName" type="file" />

	<c:if test="<%= selLayoutSet.getLogo() %>">
		<liferay-ui:icon
			cssClass="modify-link"
			id="deleteLogoLink"
			image="delete"
			label="<%= true %>"
			url="javascript:;"
		/>

		<%
		long logoId = selLayoutSet.getLogoId();

		if (logoId == 0) {
			logoId = selLayoutSet.getLiveLogoId();
		}
		%>

		<div class="lfr-change-logo" id="<portlet:namespace />logoContainer">
			<img alt="<liferay-ui:message key="logo" />" src="<%= themeDisplay.getPathImage() %>/layout_set_logo?img_id=<%= logoId %>&t=<%= WebServerServletTokenUtil.getToken(selLayoutSet.getLogoId()) %>" />
		</div>
	</c:if>

	<%
	Group guestGroup = GroupLocalServiceUtil.getGroup(company.getCompanyId(), GroupConstants.GUEST);
	%>

	<c:if test="<%= liveGroup.getGroupId() != guestGroup.getGroupId() %>">

		<%
		boolean showSiteNameSupported = GetterUtil.getBoolean(selLayoutSet.getTheme().getSetting("show-site-name-supported"), true);

		boolean showSiteNameDefault = GetterUtil.getBoolean(selLayoutSet.getTheme().getSetting("show-site-name-default"), showSiteNameSupported);

		boolean showSiteName = GetterUtil.getBoolean(selLayoutSet.getSettingsProperty("showSiteName"), showSiteNameDefault);
		%>

		<aui:input checked="<%= showSiteName %>" disabled="<%= !showSiteNameSupported %>" helpMessage='<%= showSiteNameSupported ? StringPool.BLANK : "the-theme-selected-for-the-site-does-not-support-displaying-the-title" %>' label="show-site-name" name="TypeSettingsProperties--showSiteName--" type="checkbox" />
	</c:if>
</aui:fieldset>

<aui:script use="aui-base">
	var deleteLogoLink = A.one('#<portlet:namespace />deleteLogoLink');
	var useLogoInput = A.one('#<portlet:namespace />useLogo');
	var logoContainer = A.one('#<portlet:namespace />logoContainer');
	var logoFileNameInput = A.one('#<portlet:namespace />logoFileName');

	var changeLogo = function(event) {
		var changeLogo = (event.type == 'change');

		if (useLogoInput) {
			useLogoInput.val(changeLogo);
		}

		if (logoContainer) {
			logoContainer.hide();
		}
	};

	if (deleteLogoLink) {
		deleteLogoLink.on('click', changeLogo);
	}

	if (logoFileNameInput) {
		logoFileNameInput.on('change', changeLogo);
	}
</aui:script>