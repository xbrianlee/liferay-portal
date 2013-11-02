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
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_portlet_preview_page") + StringPool.UNDERLINE;

String portletResource = (String)request.getAttribute("liferay-portlet:preview:portletName");
String queryString = (String)request.getAttribute("liferay-portlet:preview:queryString");
boolean showBorders = GetterUtil.getBoolean((String)request.getAttribute("liferay-portlet:preview:showBorders"));
String width = (String)request.getAttribute("liferay-portlet:preview:width");

String previewWidth = ParamUtil.getString(request, "previewWidth");

if (Validator.isNull(width)) {
	previewWidth = width;
}
%>

<div class="taglib-portlet-preview <%= showBorders ? "show-borders" : StringPool.BLANK %>">
	<c:if test="<%= showBorders %>">
		<div class="title">
			<liferay-ui:message key="preview" />
		</div>
	</c:if>

	<div class="preview" id="<%= randomNamespace %>">
		<div style="margin: 3px; width: <%= Validator.isNotNull(previewWidth) ? ((GetterUtil.getInteger(previewWidth) + 20) + "px") : "100%" %>;">
			<liferay-portlet:runtime
				portletName="<%= portletResource %>"
				queryString="<%= queryString %>"
			/>
		</div>
	</div>
</div>

<aui:script>
	Liferay.Util.disableElements("#<%= randomNamespace %>");
</aui:script>