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
String image = (String)request.getAttribute("liferay-ui:png_image:image");
String height = (String)request.getAttribute("liferay-ui:png_image:height");
String width = (String)request.getAttribute("liferay-ui:png_image:width");
%>

<div style="
		<c:choose>
			<c:when test="<%= BrowserSnifferUtil.isIe(request) && (BrowserSnifferUtil.getMajorVersion(request) >= 5.5) %>">
				filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='<%= image %>', sizingMethod='scale');
			</c:when>
			<c:otherwise>
				background-image: url(<%= image %>);
			</c:otherwise>
		</c:choose>

		height: <%= height %>px; width: <%= width %>px;"></div>