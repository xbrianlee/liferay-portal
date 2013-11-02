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

<%@ include file="/html/portlet/workflow_definitions/init.jsp" %>

<c:choose>
	<c:when test="<%= WorkflowEngineManagerUtil.isDeployed() %>">

		<%
		String tabs1 = ParamUtil.getString(request, "tabs1", "definitions");

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("tabs1", tabs1);
		%>

		<liferay-ui:tabs
			names="definitions,default-configuration,submissions"
			portletURL="<%= portletURL %>"
		/>

		<c:choose>
			<c:when test='<%= tabs1.equals("default-configuration") %>'>
				<liferay-util:include page="/html/portlet/workflow_definition_links/view.jsp" />
			</c:when>
			<c:when test='<%= tabs1.equals("submissions") %>'>
				<liferay-util:include page="/html/portlet/workflow_instances/view.jsp" />
			</c:when>
			<c:otherwise>
				<%@ include file="/html/portlet/workflow_definitions/view_definitions.jspf" %>
			</c:otherwise>
		</c:choose>

		<%
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, tabs1), currentURL);
		%>

	</c:when>
	<c:otherwise>
		<div class="alert alert-info">
			<liferay-ui:message key="no-workflow-engine-is-deployed" />
		</div>
	</c:otherwise>
</c:choose>