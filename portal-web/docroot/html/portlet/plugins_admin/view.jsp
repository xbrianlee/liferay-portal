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

<%@ include file="/html/portlet/plugins_admin/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/plugins_admin/view");
portletURL.setParameter("tabs2", tabs2);

PortletURL marketplaceURL = null;

boolean showEditPluginHREF = true;
boolean showReindexButton = false;
%>

<%@ include file="/html/portlet/plugins_admin/plugins.jspf" %>