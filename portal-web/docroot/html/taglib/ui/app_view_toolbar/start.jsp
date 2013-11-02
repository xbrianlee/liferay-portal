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
boolean includeSelectAll = GetterUtil.getBoolean(request.getAttribute("liferay-ui:app_view_toolbar:includeSelectAll"));
String searchJsp = (String)request.getAttribute("liferay-ui:app_view_toolbar:searchJsp");
%>

<div class="app-view-taglib">
	<div class="lfr-header-row-content">
		<c:if test="<%= Validator.isNotNull(searchJsp) %>">
			<liferay-util:include page="<%= searchJsp %>" />
		</c:if>

		<div>
			<c:if test="<%= includeSelectAll %>">
				<c:if test="<%= !user.isDefaultUser() %>">
					<aui:input cssClass="select-all-entries" inline="<%= true %>" label="" name="<%= RowChecker.ALL_ROW_IDS %>" type="checkbox" />
				</c:if>
			</c:if>