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
String key = (String)request.getAttribute("liferay-ui:success:key");
String message = (String)request.getAttribute("liferay-ui:success:message");
boolean translateMessage = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:success:translateMessage"));
%>

<c:if test="<%= SessionMessages.contains(portletRequest, key) %>">
	<div class="alert alert-success">
		<c:choose>
			<c:when test="<%= translateMessage %>">
				<%= LanguageUtil.get(pageContext, message) %>
			</c:when>
			<c:otherwise>
				<%= message %>
			</c:otherwise>
		</c:choose>
	</div>
</c:if>