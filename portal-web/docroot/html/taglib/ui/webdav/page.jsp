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

<%@ include file="/html/taglib/init.jsp" %>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_webdav_page") + StringPool.UNDERLINE;

String path = (String)request.getAttribute("liferay-ui:webdav:path");
%>

<div class="taglib-webdav" id="<%= randomNamespace %>webdav">
	<a class="show-webdav" href="javascript:;"><liferay-ui:message key="access-from-desktop" /></a>

	<table class="lfr-table">
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="webdav-url" />
			</td>
			<td>
				<liferay-ui:input-resource
					url='<%= themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/webdav" + themeDisplay.getScopeGroup().getFriendlyURL() + path %>'
				/>
			</td>
		</tr>
	</table>
</div>

<aui:script use="aui-base">
	var webdavDiv = A.one('#<%= randomNamespace %>webdav');

	if (webdavDiv) {
		var webdavLink = webdavDiv.all('.show-webdav');

		if (webdavLink) {
			webdavLink.on(
				'click',
				function(event) {
					webdavDiv.toggleClass('visible');
				}
			);
		}
	}
</aui:script>