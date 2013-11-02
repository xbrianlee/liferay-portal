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
int incompleteBackgroundTaskCount = ParamUtil.getInteger(request, "incompleteBackgroundTaskCount");
%>

<div class="alert alert-info">
	<c:choose>
		<c:when test="<%= incompleteBackgroundTaskCount == 1 %>">
			<liferay-ui:message key="there-is-currently-1-process-in-progress" />
		</c:when>
		<c:when test="<%= incompleteBackgroundTaskCount > 1 %>">
			<liferay-ui:message arguments="<%= incompleteBackgroundTaskCount - 1 %>" key="there-is-currently-1-process-in-progress-and-x-pending" />
		</c:when>
		<c:otherwise>
			<liferay-ui:message key="there-are-no-processes-in-progress-anymore" />
		</c:otherwise>
	</c:choose>
</div>