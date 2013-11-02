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
Layout selLayout = (Layout)request.getAttribute("edit_pages.jsp-selLayout");

List<Portlet> embeddedPortlets = (List<Portlet>)request.getAttribute("edit_pages.jsp-embeddedPortlets");

RowChecker rowChecker = new RowChecker(liferayPortletResponse);

rowChecker.setRowIds("removeEmbeddedPortletIds");
%>

<h3><liferay-ui:message key="embedded-portlets" /></h3>

<c:choose>
	<c:when test="<%= selLayout.isLayoutPrototypeLinkActive() %>">

		<%
		rowChecker = null;
		%>

		<div class="alert alert-info">
			<liferay-ui:message key="layout-inherits-from-a-prototype-portlets-cannot-be-manipulated" />
		</div>
	</c:when>
	<c:otherwise>
		<div class="alert alert-block">
			<liferay-ui:message key="warning-preferences-of-selected-portlets-will-be-reset-or-deleted" />
		</div>
	</c:otherwise>
</c:choose>

<liferay-ui:search-container
	deltaConfigurable="<%= false %>"
	rowChecker="<%= rowChecker %>"
>
	<liferay-ui:search-container-results results="<%= embeddedPortlets %>" />

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.Portlet"
		escapedModel="<%= true %>"
		keyProperty="portletId"
		modelVar="portlet"
	>
		<liferay-ui:search-container-column-text
			name="portlet-id"
			property="portletId"
		/>

		<liferay-ui:search-container-column-text
			name="title"
		>
			<%= PortalUtil.getPortletTitle(portlet, application, locale) %>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="status"
		>
			<c:choose>
				<c:when test="<%= !portlet.isActive() %>">
					<liferay-ui:message key="inactive" />
				</c:when>
				<c:when test="<%= !portlet.isReady() %>">
					<liferay-ui:message arguments="portlet" key="is-not-ready" />
				</c:when>
				<c:when test="<%= portlet.isUndeployedPortlet() %>">
					<liferay-ui:message key="undeployed" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="active" />
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator type="none" />
</liferay-ui:search-container>