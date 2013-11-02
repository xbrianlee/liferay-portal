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

<%@ include file="/html/portlet/directory/init.jsp" %>

<%
Organization organization = (Organization)request.getAttribute(WebKeys.ORGANIZATION);

long logoId = organization.getLogoId();
%>

<h2><%= HtmlUtil.escape(organization.getName()) %></h2>

<div class="details">
	<img alt="<%= HtmlUtil.escape(organization.getName()) %>" class="avatar" src="<%= themeDisplay.getPathImage() %>/organization_logo?img_id=<%= logoId %>&t=<%= WebServerServletTokenUtil.getToken(logoId) %>" />

	<dl class="property-list">
		<dt>
			<liferay-ui:message key="type" />
		</dt>
		<dd>
			<%= LanguageUtil.get(pageContext, organization.getType()) %>
		</dd>

		<c:if test="<%= PropsValues.FIELD_ENABLE_COM_LIFERAY_PORTAL_MODEL_ORGANIZATION_STATUS %>">
			<dt>
				<liferay-ui:message key="status" />
			</dt>
			<dd>
				<%= LanguageUtil.get(pageContext, ListTypeServiceUtil.getListType(organization.getStatusId()).getName()) %>
			</dd>
		</c:if>

		<c:if test="<%= organization.getCountryId() > 0 %>">
			<dt>
				<liferay-ui:message key="country" />
			</dt>
			<dd>
				<%= LanguageUtil.get(pageContext, CountryServiceUtil.getCountry(organization.getCountryId()).getName()) %>
			</dd>
		</c:if>

		<c:if test="<%= organization.getRegionId() > 0 %>">
			<dt>
				<liferay-ui:message key="region" />
			</dt>
			<dd>
				<%= LanguageUtil.get(pageContext, RegionServiceUtil.getRegion(organization.getRegionId()).getName()) %>
			</dd>
		</c:if>

		<c:if test="<%= organization.getParentOrganization() != null %>">
			<dt>
				<liferay-ui:message key="parent-organization" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(organization.getParentOrganization().getName()) %>
			</dd>
		</c:if>
	</dl>
</div>