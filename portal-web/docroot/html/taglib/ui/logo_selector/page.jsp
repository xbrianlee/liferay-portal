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

<%@ include file="/html/taglib/ui/logo_selector/init.jsp" %>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_logo_selector") + StringPool.UNDERLINE;

String defaultLogoURL = (String)request.getAttribute("liferay-ui:logo-selector:defaultLogoURL");
String editLogoURL = (String)request.getAttribute("liferay-ui:logo-selector:editLogoURL");
long imageId = GetterUtil.getLong((String)request.getAttribute("liferay-ui:logo-selector:imageId"));
String logoDisplaySelector = (String)request.getAttribute("liferay-ui:logo-selector:logoDisplaySelector");
boolean showBackground = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:logo-selector:showBackground"));

boolean deleteLogo = ParamUtil.getBoolean(request, "deleteLogo");

String imageSrc = null;

if (deleteLogo || (imageId == 0)) {
	imageSrc = defaultLogoURL;
}
else {
	imageSrc = themeDisplay.getPathImage() + "/logo?img_id=" + imageId + "&t" + WebServerServletTokenUtil.getToken(imageId);
}
%>

<div class="taglib-logo-selector" id="<%= randomNamespace %>taglibLogoSelector">
	<div class="taglib-logo-selector-content" id="<%= randomNamespace %>taglibLogoSelectorContent">
		<a class='lfr-change-logo <%= showBackground ? "show-background" : StringPool.BLANK %>' href="javascript:;">
			<img alt="<liferay-ui:message key="change-logo" />" class="img-polaroid avatar" id="<%= randomNamespace %>avatar" src="<%= imageSrc %>" />
		</a>

		<div class="portrait-icons">
			<div class="btn-group">
				<aui:button cssClass="btn edit-logo" icon="icon-picture" value="change" />
				<aui:button cssClass="btn delete-logo" disabled="<%= (imageId == 0) %>" icon="icon-remove" value="delete" />
			</div>

			<aui:input name="deleteLogo" type="hidden" value="<%= deleteLogo %>" />
		</div>
	</div>
</div>

<aui:script use="liferay-logo-selector">
	new Liferay.LogoSelector(
		{
			boundingBox: '#<%= randomNamespace %>taglibLogoSelector',
			contentBox: '#<%= randomNamespace %>taglibLogoSelectorContent',
			defaultLogoURL: '<%= defaultLogoURL %>',
			editLogoURL: '<%= editLogoURL %>',
			randomNamespace: '<%= randomNamespace %>',
			logoDisplaySelector: '<%= logoDisplaySelector %>',
			portletNamespace: '<portlet:namespace />'
		}
	).render();
</aui:script>