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

<%@ include file="/html/portlet/nested_portlets/init.jsp" %>

<c:if test="<%= LayoutPermissionUtil.contains(permissionChecker, layout, ActionKeys.UPDATE) %>">
	<div class="alert alert-info hide" id="<portlet:namespace />nested-portlets-msg">
		<liferay-ui:message key="drag-portlets-below-to-nest-them" />
	</div>

	<aui:script use="aui-base">
		var portletWrapper = A.one('#portlet-wrapper-<%= portletDisplay.getId() %>');

		if (portletWrapper) {
			var message = portletWrapper.one('#<portlet:namespace />nested-portlets-msg');

			var nestedPortlet = portletWrapper.one('.portlet, .portlet-borderless-container');

			if (!nestedPortlet) {
				message.show();
			}
		}
	</aui:script>
</c:if>

<%
try {
	String velocityTemplateId = (String)request.getAttribute(WebKeys.NESTED_PORTLET_VELOCITY_TEMPLATE_ID);
	String velocityTemplateContent = (String)request.getAttribute(WebKeys.NESTED_PORTLET_VELOCITY_TEMPLATE_CONTENT);

	if (Validator.isNotNull(velocityTemplateId) && Validator.isNotNull(velocityTemplateContent)) {
		RuntimePageUtil.processTemplate(pageContext, new StringTemplateResource(velocityTemplateId, velocityTemplateContent));
	}
}
catch (Exception e) {
	_log.error("Cannot render Nested Portlets portlet", e);
}
finally {
	RenderRequestImpl renderRequestImpl = (RenderRequestImpl)renderRequest;

	renderRequestImpl.defineObjects(portletConfig, renderResponse);
}
%>

<%!
private static Log _log = LogFactoryUtil.getLog("portal-web.docroot.html.portlet.nested_portlets.view_jsp");
%>