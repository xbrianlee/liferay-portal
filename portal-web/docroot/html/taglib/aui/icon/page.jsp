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

<%@ include file="/html/taglib/aui/icon/init.jsp" %>

<liferay-util:buffer var="iconContent">
	<i class="icon-<%= image %>"></i>

	<liferay-ui:message key="<%= label %>" />
</liferay-util:buffer>

<c:choose>
	<c:when test="<%= Validator.isNotNull(url) %>">
		<aui:a cssClass="<%= cssClass %>" data="<%= data %>" href="<%= url %>" id="<%= id %>" target="<%= target %>">
			<%= iconContent %>
		</aui:a>
	</c:when>
	<c:otherwise>
		<span class="<%= cssClass %>" <%= AUIUtil.buildData(data) %> id="<%= id %>">
			<%= iconContent %>
		</span>
	</c:otherwise>
</c:choose>